package br.com.arms.servidor;

import java.io.PrintStream;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class juntarResultadoFutureWSeBD implements Callable<Void> {

	private Future<String> futureWS;
	private Future<String> futureBD;
	private PrintStream saidaCliente;

	public juntarResultadoFutureWSeBD(Future<String> futureWS, Future<String> futureBD, PrintStream saidaCliente) {
		this.futureWS = futureWS;
		this.futureBD = futureBD;
		this.saidaCliente = saidaCliente;
	}

	@Override
	public Void call(){
		System.out.println("Aguardando resultados do future WS e BD");
		
		try {
			String numeroMagico = this.futureWS.get(30,TimeUnit.SECONDS);
			String numeroMagico2 = this.futureBD.get(30,TimeUnit.SECONDS);
			
			this.saidaCliente.println(String.format("Resultado comando c2: %s e %s", numeroMagico, numeroMagico2));
			
		} catch (InterruptedException | ExecutionException | TimeoutException e) {
			System.out.println("TIMEOUT --- cancelando execução do comando c2");
			this.saidaCliente.println("TIMEOUT --- Execução do comando c2");
			this.futureWS.cancel(true);
			this.futureBD.cancel(true);
		}
		
		System.out.println("Finalizando juntarResultadoFutureWSeBD");
		
		return null;
	}


}
