package br.com.arms.cliente;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class ClienteTarefas {

	public static void main(String[] args) throws Exception {

		Socket socket = new Socket("localhost", 12345);

		System.out.println("conexão estabelecida");
		System.out.println("digite '/sair' para terminar a conexão ou '/fim' para suspender o servidor");
		
		Thread threadEnviaComando = new Thread(() -> {
			try {
				System.out.println("Pode enviar comandos!");
				PrintStream saida = new PrintStream(socket.getOutputStream());
				Scanner teclado = new Scanner(System.in);

				while (teclado.hasNextLine()) {
					String linha = teclado.nextLine();

					if (linha.trim().equals("/sair")) {
						System.out.println(
								"deseja realmente terminar a conexão com o servidor?\n"
								+ "digite 's' para sair ou qualquer outra tecla para continuar");
						String confirmacao = teclado.nextLine();
						if (confirmacao.trim().equals("s"))
							break;
						else
							continue;
					}
					saida.println(linha);
				}
				
				saida.close();
				teclado.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		},"threadEnviaComando");

		Thread threadRespostaServidor = new Thread(() -> {
			try {
				System.out.println("recebendo dados do servidor");
				Scanner respostaServidor = new Scanner(socket.getInputStream());

				while (respostaServidor.hasNextLine()) {
					String linha = respostaServidor.nextLine();
					System.out.println(linha);
				}
				respostaServidor.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		},"threadRespostaServidor");

		threadEnviaComando.start();
		threadRespostaServidor.start();

		threadEnviaComando.join(); //Espera a thread 'threadEnviaComando' para então poder morrer

		System.out.println("fechando socket do cliente");
		socket.close();
	}

}

//Usando classe an�nima
//Thread threadEnviaComando = new Thread(new Runnable() {
//@Override
//public void run() {
//	try {
//		System.out.println("Pode enviar comandos!");
//		PrintStream saida = new PrintStream(socket.getOutputStream());
//		Scanner teclado = new Scanner(System.in);
//		
//		while (teclado.hasNextLine()) {
//			String linha = teclado.nextLine();
//			
//			if (linha.trim().equals("/sair")) {
//				System.out.println("deseja realmente terminar a conex�o com o servidor?\ndigite 's' para sair ou qualquer outra tecla para continuar");
//				String confirmacao = teclado.nextLine();
//				if (confirmacao.trim().equals("s"))
//					break;
//				else
//					continue;
//			}
//			saida.println(linha);
//		}
//		saida.close();
//		teclado.close();
//	} catch (IOException e) {
//		throw new RuntimeException(e);
//	}
//}
//});