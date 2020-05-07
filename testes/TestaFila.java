package br.com.arms.testes;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class TestaFila {
	public static void main(String[] args) throws InterruptedException {
		BlockingQueue<String> filaThreadSafe = new ArrayBlockingQueue<String>(3);
		
		//trava thread main até que libere mais espaço para addicionar o objeto atual
		filaThreadSafe.put("c1");
		filaThreadSafe.put("c2");
		filaThreadSafe.put("c3");
		
		//trava thread main até que seja adicionado um novo objeto na lista para ser retirado
		System.out.println(filaThreadSafe.take());
		System.out.println(filaThreadSafe.take());
		System.out.println(filaThreadSafe.take());
		
		System.out.println(filaThreadSafe.size());
		
		//não trava, não add se estiver cheio e retorna um booleano
		filaThreadSafe.offer("c1");
		filaThreadSafe.offer("c2");
		filaThreadSafe.offer("c3");
		
		//não trava, simplesmente imprime null
		//método pool permite um TIMEOUT
		System.out.println(filaThreadSafe.poll());
		System.out.println(filaThreadSafe.poll());
		System.out.println(filaThreadSafe.poll(5,TimeUnit.SECONDS));
		
		System.out.println(filaThreadSafe.size());
		
	}
}
