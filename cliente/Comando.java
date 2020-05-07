package br.com.arms.cliente;
//Exemplo da classe de um comando dado pelo cliente, nesse projeto o comando 
//é representado por uma simples String por questões de simplicidade.
public class Comando implements Comparable<Comando> {

    private String tipo;
    private int prioridade;
    private String params;
    
	public Comando(String tipo, int prioridade, String params) {
		this.tipo = tipo;
		this.prioridade = prioridade;
		this.params = params;
	}
	
	public String getTipo() {
		return tipo;
	}
	
	public int getPrioridade() {
		return prioridade;
	}
	public String getParams() {
		return params;
	}

	@Override
	public int compareTo(Comando outroComando) {
		return outroComando.prioridade - this.prioridade;
	}
    
    //exemplo de comando enviado pelo cliente:
    //ADD-3?curso=threads2&dataCriacao=12/06/2016&nivel=avancado
	//O comando aqui é ADD com a prioridade 3, além de enviar vários parâmetros.

}
