package br.com.arms.servidor;

import java.io.PrintStream;
import java.util.Random;
import java.util.concurrent.Callable;

public class ComandoC2AcessaBD implements Callable<String> {
	
	private PrintStream saida;

	public ComandoC2AcessaBD(PrintStream saida) {
		this.saida = saida;
	}

	@Override
	public String call() throws Exception {
		System.out.println("Servidor recebeu o comando c2 - BD");
		
		saida.println("processando comando c2 - BD");
		
		Thread.sleep(15000);
		
		int numero = new Random().nextInt(100) + 1;
		
		System.out.println("Servidor finalizou comando c2 - BD");
		return Integer.toString(numero);
	}

}
