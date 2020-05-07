package br.com.arms.servidor;

import java.lang.Thread.UncaughtExceptionHandler;

public class TratadorDeExcecao implements UncaughtExceptionHandler {

	@Override
	public void uncaughtException(Thread t, Throwable e) {
		//Projeto real, gravar essas msgs em um lock, enviar msg para algum responsável para avisar sobre o erro
		System.out.println("Deu exceção na thread " + t.getName() + ", " + e.getMessage());
	}

}
