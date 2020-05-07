package br.com.arms.servidor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

public class ServidorTarefas {
	
	private ExecutorService threadPool;
	private ServerSocket servidor;
	//private volatile boolean isRunning; //não cria caches separados para cada thread que a utilizar
	private AtomicBoolean isRunning; //tipo wrapper em cima do primitivo volatile
	private BlockingQueue<String> filaComandos;

	public ServidorTarefas() throws IOException {
		System.out.println("Iniciando sevidor");
		this.servidor = new ServerSocket(12345);
		this.threadPool = Executors.newCachedThreadPool(new FabricaDeThreads()); //dinâmico
//		this.threadPool = Executors.newFixedThreadPool(4, new FabricaDeThreads()); //fixo
		this.isRunning = new AtomicBoolean(true);
		this.filaComandos = new ArrayBlockingQueue<>(2);
		iniciarConsumidores();
	}

	private void iniciarConsumidores() {
		int qtdeConsumidores = 2;
		for (int i = 0; i < qtdeConsumidores; i++) {
			TarefaConsumir tarefa = new TarefaConsumir(filaComandos);
			this.threadPool.execute(tarefa);
		}
	}

	public void run() throws IOException {
		while(this.isRunning.get()) {
			try {
				Socket socket = servidor.accept();
				System.out.println(String.format("Aceitando novo cliente %d",socket.getPort()));
				DistribuirTarefas distribuirTarefas = new DistribuirTarefas(threadPool, filaComandos , socket, this);
				this.threadPool.execute(distribuirTarefas);
			} catch (SocketException e) {
				System.out.println("SocketException, está rodando? " + this.isRunning);
//				System.exit(0); //forçar parada do servidor
			}
		}
	}
	
	public void stop() throws IOException {
		System.out.println("Parando servidor");
		this.isRunning.set(false);
		this.threadPool.shutdown();
		this.servidor.close();
	}
	
	public static void main(String[] args) throws Exception {
		ServidorTarefas servidor = new ServidorTarefas();
		servidor.run();
		servidor.stop();
	}
}
