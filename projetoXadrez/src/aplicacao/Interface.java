package aplicacao;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import xadrez.PartidaXadrez;
import xadrez.PecaXadrez;
import xadrez.PosicaoXadrez;
import xadrez.Cor;

public class Interface {

	// Limpa a tela (Console)
	public static void limparTela() {
		System.out.print("\033[H\033[2J");
		System.out.flush();
	}

	// Imprime toda a partida (Atualizado para mostrar Xeque e Peças Capturadas)
	public static void imprimirPartida(PartidaXadrez partida, List<PecaXadrez> capturadas) {
		imprimirTabuleiro(partida.getPecas());
		System.out.println();
		imprimirPecasCapturadas(capturadas);
		System.out.println();
		System.out.println("Turno: " + partida.getTurno());
		
		if (!partida.getXequeMate()) {
			System.out.println("Jogador atual: " + partida.getJogadorAtual());
			if (partida.getXeque()) {
				System.out.println("XEQUE!");
			}
		} else {
			System.out.println("XEQUE-MATE!");
			System.out.println("Vencedor: " + partida.getJogadorAtual());
		}
	}

	// Imprime o tabuleiro padrão
	public static void imprimirTabuleiro(PecaXadrez[][] pecas) {
		for (int i = 0; i < pecas.length; i++) {
			System.out.print((8 - i) + " ");
			for (int j = 0; j < pecas[i].length; j++) {
				imprimirPeca(pecas[i][j], false);
			}
			System.out.println();
		}
		System.out.println("  a b c d e f g h");
	}

	// Imprime o tabuleiro destacando movimentos possíveis
	public static void imprimirTabuleiro(PecaXadrez[][] pecas, boolean[][] movimentosPossiveis) {
		for (int i = 0; i < pecas.length; i++) {
			System.out.print((8 - i) + " ");
			for (int j = 0; j < pecas[i].length; j++) {
				imprimirPeca(pecas[i][j], movimentosPossiveis[i][j]);
			}
			System.out.println();
		}
		System.out.println("  a b c d e f g h");
	}

	// Imprime uma peça isolada mantendo espaçamento fixo de 2 caracteres
	private static void imprimirPeca(PecaXadrez peca, boolean fundoAlterado) {
		if (fundoAlterado) {
			if (peca == null) {
				System.out.print("* "); // Indica movimento possível em casa vazia
			} else {
				System.out.print("[" + peca + "]"); // Indica que pode capturar a peça nessa casa
			}
		} else {
			if (peca == null) {
				System.out.print("- ");
			} else {
				System.out.print(peca + " ");
			}
		}
	}

	// Método auxiliar para listar as peças fora do jogo
	private static void imprimirPecasCapturadas(List<PecaXadrez> capturadas) {
		List<PecaXadrez> brancas = capturadas.stream().filter(x -> x.getCor() == Cor.BRANCO).collect(Collectors.toList());
		List<PecaXadrez> pretas = capturadas.stream().filter(x -> x.getCor() == Cor.PRETO).collect(Collectors.toList());
		
		System.out.println("Peças capturadas:");
		System.out.print("Brancas: ");
		System.out.println(Arrays.toString(brancas.toArray()));
		System.out.print("Pretas:  ");
		System.out.println(Arrays.toString(pretas.toArray()));
	}

	// Lê uma posição digitada pelo usuário
	public static PosicaoXadrez lerPosicaoXadrez(Scanner sc) {
		try {
			String s = sc.nextLine();
			char coluna = s.charAt(0);
			int linha = Integer.parseInt(s.substring(1));
			return new PosicaoXadrez(coluna, linha);
		}
		catch (RuntimeException e) {
			throw new IllegalArgumentException("Posição inválida. Utilize valores entre a1 e h8.");
		}
	}

	public static void mostrarTitulo() {
		System.out.println("======================================");
		System.out.println("         SISTEMA DE XADREZ            ");
		System.out.println("======================================");
		System.out.println();
	}
}