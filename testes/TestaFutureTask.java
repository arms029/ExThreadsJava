package br.com.arms.testes;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class TestaFutureTask {
	public static void main(String args[]) throws InterruptedException, ExecutionException {
		//Callable só pode ser chamado através de um pool de threads.
		//Para resolver esse problema pode ser utilizado a classe FutureTask, assim o callable pode ser executado em uma thread.
		//FutureTask é considerado um adapter, Design Pattern que representa uma ponte entre duas interfaces incompatíveis.
		Callable<String> tarefa = new Tarefa();
		FutureTask<String> futureTask = new FutureTask<String>(tarefa);
		new Thread(futureTask).start(); //usando Thread puro!!            
		String resultado = futureTask.get();
		System.out.println(resultado);
	}
}
