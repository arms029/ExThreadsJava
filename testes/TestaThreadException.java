package br.com.arms.testes;

import br.com.arms.servidor.TratadorDeExcecao;

public class TestaThreadException {

	private volatile boolean estaRodando = false;
	// private boolean estaRodando = false; //não funciona corretamente, pq cada
	// thread possui o seu cache

	public static void main(String[] args) throws InterruptedException {
		TestaThreadException servidor = new TestaThreadException();
		servidor.rodar();
		servidor.alterandoAtributo();
	}

	private void rodar() {
		Thread thread = new Thread(new Runnable() {
			public void run() {
				System.out.println("Servidor começando, estaRodando = " + estaRodando);

				while (!estaRodando) {
				}
				
				//Simulando um erro qualquer na thread
				if (estaRodando) {
					throw new RuntimeException("Deu erro na thread");
				}

				System.out.println("Servidor rodando, estaRodando = " + estaRodando);

				while (estaRodando) {
				}

				System.out.println("Servidor terminando, estaRodando = " + estaRodando);
			}
		});
		//É passado um objeto para tratar as exceções da thread
		thread.setUncaughtExceptionHandler(new TratadorDeExcecao());
		thread.start();
	}

	private void alterandoAtributo() throws InterruptedException {
		//Simulando tempo de execuçao
		Thread.sleep(5000);
		System.out.println("Main alterando estaRodando = true");
		estaRodando = true;

		Thread.sleep(5000);
		System.out.println("Main alterando estaRodando = false");
		estaRodando = false;
	}
}
