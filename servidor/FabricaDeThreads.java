package br.com.arms.servidor;

import java.util.concurrent.ThreadFactory;

public class FabricaDeThreads implements ThreadFactory {
	
	private static int numero = 1;
	
	@Override
	public Thread newThread(Runnable r) {
		Thread thread = new Thread(r, "Thread Servidor Tarefas " + numero);
		numero++;
		thread.setUncaughtExceptionHandler(new TratadorDeExcecao());
		thread.setDaemon(true);
		return thread;
	}

}
