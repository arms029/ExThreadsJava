package br.com.arms.testes;

public class ServidorDeTeste {
	
	//Ao utilizar métodos synchronized, não é necessario colocar o modificador volatile
	private boolean estaRodando = false;

	public static void main(String[] args) throws InterruptedException {
		ServidorDeTeste servidor = new ServidorDeTeste();
		servidor.rodar();
		servidor.alterandoAtributo();
	}

	private void rodar() {
        Thread thread = new Thread(new TarefaParaServidorDeTeste(this));
        thread.start();
	}

	public synchronized boolean estaRodando() {
		return this.estaRodando;
	}

	public synchronized void parar() {
		this.estaRodando = false;
	}

	public synchronized void ligar() {
		this.estaRodando = true;
	}

	private void alterandoAtributo() throws InterruptedException {
		Thread.sleep(5000);
		System.out.println("Main alterando estaRodando = true");
		estaRodando = true;

		Thread.sleep(5000);
		System.out.println("Main alterando estaRodando = false");
		estaRodando = false;
	}
}
