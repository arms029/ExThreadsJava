package br.com.arms.testes;

import java.util.Set;

public class VerificaThreads {
	public static void main(String[] args) {
		
		Thread novaThread = new Thread("nova thread");
		novaThread.start();
		
		Set<Thread> todasAsThreads = Thread.getAllStackTraces().keySet();

		for (Thread thread : todasAsThreads) {
			System.out.println(thread.getName());
		}
		
		Runtime runtime = Runtime.getRuntime();
		int qtdProcessadores = runtime.availableProcessors();
		System.out.println("Qtd de processadores: " + qtdProcessadores);
		
		
	}
}
