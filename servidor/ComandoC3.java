package br.com.arms.servidor;

import java.io.PrintStream;

public class ComandoC3 implements Runnable {
	
	private PrintStream saida;

	public ComandoC3(PrintStream saida) {
		this.saida = saida;
	}

	@Override
	public void run() {
		System.out.println("Servidor recebeu o comando c3");
		try {
			Thread.sleep(20000);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		saida.println("Comando c3 executado com sucesso!");
	}

}
