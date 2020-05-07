package br.com.arms.servidor;

import java.io.PrintStream;

public class ComandoC1 implements Runnable {
	
	private PrintStream saida;

	public ComandoC1(PrintStream saida) {
		this.saida = saida;
	}

	@Override
	public void run() {
		//será impresso no console do servidor
		System.out.println("Servidor recebeu o comando c1");
		try {
			Thread.sleep(20000);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		//essa msg será enviada para o cliente
		saida.println("Comando c1 executado com sucesso!");
	}

}
