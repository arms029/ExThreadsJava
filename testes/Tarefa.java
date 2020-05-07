package br.com.arms.testes;

import java.util.concurrent.Callable;

public class Tarefa implements Callable<String> {

	@Override
	public String call() throws Exception {
		System.out.println("olá");
		return "teste";
	}

}
