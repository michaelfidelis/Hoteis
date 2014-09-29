package br.com.michael.hotels.view;

import java.io.File;
import java.util.Scanner;

import br.com.michael.hotels.controller.Control;

public class ModoTexto {
	private Control control;
	private Scanner scanner;
	private File arquivo;
	boolean arquivoCarregado = false;
	
	public static void main(String[] args) {
		new ModoTexto();

	}

	public ModoTexto() {
		control = new Control();
		scanner = new Scanner(System.in);
		menu();
	}

	public void menu() {
		boolean escolhaValida = false;		
		int escolha;

		do {
			System.out.println("=-=-=-=-= MENU =-=-=-=-=");
			System.out.println("[0] Carregar arquivo");
			System.out.println("[1] Informações sobre os Hotéis");
			System.out.println("[2] Exibe registros do arquivo");
			System.out.println("[3] Exibe hotel mais barato");

			System.out.print("Escolha: ");
			escolha = solicitaOpcaoMenu();

			switch (escolha) {
			case 0:				//Solicita arquivo
				arquivo = solicitaArquivo(); 
				arquivoCarregado = true;
				System.out.println("Arquivo carregado com sucesso!");
				retornaAoMenu();
				break;
			case 1:				//Informações sobre os Hotéis
				limpaTela();
				System.out.println(control.hoteisString());
				retornaAoMenu();
				break;

			case 2:				//Exibe Registros carregados do arquivo
				if (arquivoCarregado) {
					limpaTela();
					control.exibeReservas();
					retornaAoMenu();
					break;
				} else {
					System.out.println("Arquivo não carregado.");
					retornaAoMenu();
					break;
				}

			case 3:				//Exibe as reservas mais baratas
				if (arquivoCarregado) {
					limpaTela();
					System.out.println(control.reservasMaisBaratasToString());
					retornaAoMenu();
					break;
				} else {
					System.out.println("Arquivo não carregado.");
					retornaAoMenu();
					break;
				}

			default:
				System.out.println("Escolha inválida!");
				escolhaValida = false;
				break;
			}

		} while (!escolhaValida);

	}
	
	/**
	 * Solicita o caminho do arquivo para o usuario
	 * 
	 * @return File com a massa de dados
	 */
	public File solicitaArquivo() {
		boolean arquivoValido = false;
		do {
			System.out
					.println("Insira o caminho do arquivo contendo os dados: ");
			String caminho = scanner.nextLine();

			arquivo = new File(caminho);

			if (!arquivo.isFile()) {
				arquivoValido = false;
				System.out.println("Erro: Arquivo inválido!");
			} else {
				arquivoValido = true;
				control.setArquivo(arquivo);
			}
		} while (!arquivoValido);

		return arquivo;
	}

	public int solicitaOpcaoMenu(){
		int escolha = Integer.MIN_VALUE;
		
		try {
			escolha = Integer.parseInt(scanner.nextLine());
		}catch(NumberFormatException e){
			limpaTela();	//Caso a opção seja inválida, limpa a tela e retorna ao menu.
			menu();
		}
		return escolha;
		
	}


	public void retornaAoMenu() {
		System.out.println("Pressione [ENTER] para retornar ao menu.");
		scanner.nextLine();
		limpaTela();
		menu();
	}
	
	/**
	 *  Limpa tela para melhor visualização
	 */
	public void limpaTela() {
		for (int i = 0; i < 100; i++) {
			System.out.println();

		}
	}



}
