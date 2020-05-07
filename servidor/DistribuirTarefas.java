package br.com.arms.servidor;

import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

public class DistribuirTarefas implements Runnable{

	private Socket socket;
	private ServidorTarefas servidor;
	private ExecutorService threadPool;
	private BlockingQueue<String> filaComandos;

	public DistribuirTarefas(ExecutorService threadPool, BlockingQueue<String> filaComandos, Socket socket, ServidorTarefas servidor) {
		this.filaComandos = filaComandos;
		this.socket = socket;
		this.servidor = servidor;
		this.threadPool = threadPool;
	}

	@Override
	public void run() {
		
		try {
			System.out.println(String.format("Distribuindo tarefas para %s",socket));
			
			Scanner entradaCliente = new Scanner(socket.getInputStream());
			PrintStream saidaCliente = new PrintStream(socket.getOutputStream());
			
			while (entradaCliente.hasNext()) {
				String comando = entradaCliente.nextLine();
				System.out.println("Comando recebido " + comando);
				
				switch(comando) {
					case "c1": {
						saidaCliente.println("Confirmação comando c1");
						ComandoC1 c1 = new ComandoC1(saidaCliente);
						this.threadPool.execute(c1);
						break;                       
					}                                
					case "c2": {                     
						saidaCliente.println("Confirmação comando c2");
						ComandoC2ChamaWS c2WS = new ComandoC2ChamaWS(saidaCliente);
						ComandoC2AcessaBD c2BD = new ComandoC2AcessaBD(saidaCliente);
						Future<String> futureWS = this.threadPool.submit(c2WS);
						Future<String> futureBD = this.threadPool.submit(c2BD);
						this.threadPool.submit(new juntarResultadoFutureWSeBD(futureWS,futureBD, saidaCliente));
						
						break;                       
					}                                
					case "c3": {      
						this.filaComandos.put(comando);//block
						saidaCliente.println("comando c3 add na fila");
						break;
					}
					case "/fim": {
						saidaCliente.println("Desligando o servidor");
						servidor.stop();
						break;
					}
					default: {
						saidaCliente.println("Comando não encontrado");
						break;
					}
				}
				
				//System.out.println(comando);
			}
			saidaCliente.close();
			entradaCliente.close();
		}catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

}
