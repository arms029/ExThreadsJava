package br.com.arms.testes;

public class TarefaParaServidorDeTeste implements Runnable {

    private ServidorDeTeste servidor;

    //recebendo o servidor como parametro
    public TarefaParaServidorDeTeste(ServidorDeTeste servidor) {
        this.servidor = servidor;
    }

    public void run() {
        //chamando o método estaRodando()
        System.out.println("Servidor comecando, estaRodando=" + this.servidor.estaRodando());
        while (!this.servidor.estaRodando()) {
        }

        System.out.println("Servidor rodando, estaRodando=" + this.servidor.estaRodando());

        while (this.servidor.estaRodando()) {
        }

        System.out.println("Servidor terminando, estaRodando=" + this.servidor.estaRodando());
    }
}