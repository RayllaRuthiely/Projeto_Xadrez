package xadrez_pecas;

import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;
import xadrez.Cor;
import xadrez.PecaXadrez;

public class Cavalo extends PecaXadrez {

	// Construtor
	public Cavalo(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro, cor);
	}

	@Override
	public String toString() {
		return "C";
	}

	// Verifica se o cavalo pode mover para a posição
	private boolean podeMover(Posicao posicao) {

		PecaXadrez p =
				(PecaXadrez) getTabuleiro().obterPeca(posicao);

		return p == null || p.getCor() != getCor();
	}

	@Override
	public boolean[][] movimentosPossiveis() {

		boolean[][] matriz =
				new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];

		Posicao p = new Posicao(0, 0);

		// 2 para cima e 1 para esquerda
		p.setValores(posicao.getLinha() - 2, posicao.getColuna() - 1);
		if (getTabuleiro().existePosicao(p) && podeMover(p)) {
			matriz[p.getLinha()][p.getColuna()] = true;
		}

		// 2 para cima e 1 para direita
		p.setValores(posicao.getLinha() - 2, posicao.getColuna() + 1);
		if (getTabuleiro().existePosicao(p) && podeMover(p)) {
			matriz[p.getLinha()][p.getColuna()] = true;
		}

		// 1 para cima e 2 para esquerda
		p.setValores(posicao.getLinha() - 1, posicao.getColuna() - 2);
		if (getTabuleiro().existePosicao(p) && podeMover(p)) {
			matriz[p.getLinha()][p.getColuna()] = true;
		}

		// 1 para cima e 2 para direita
		p.setValores(posicao.getLinha() - 1, posicao.getColuna() + 2);
		if (getTabuleiro().existePosicao(p) && podeMover(p)) {
			matriz[p.getLinha()][p.getColuna()] = true;
		}

		// 1 para baixo e 2 para direita
		p.setValores(posicao.getLinha() + 1, posicao.getColuna() + 2);
		if (getTabuleiro().existePosicao(p) && podeMover(p)) {
			matriz[p.getLinha()][p.getColuna()] = true;
		}

		// 2 para baixo e 1 para direita
		p.setValores(posicao.getLinha() + 2, posicao.getColuna() + 1);
		if (getTabuleiro().existePosicao(p) && podeMover(p)) {
			matriz[p.getLinha()][p.getColuna()] = true;
		}

		// 2 para baixo e 1 para esquerda
		p.setValores(posicao.getLinha() + 2, posicao.getColuna() - 1);
		if (getTabuleiro().existePosicao(p) && podeMover(p)) {
			matriz[p.getLinha()][p.getColuna()] = true;
		}

		// 1 para baixo e 2 para esquerda
		p.setValores(posicao.getLinha() + 1, posicao.getColuna() - 2);
		if (getTabuleiro().existePosicao(p) && podeMover(p)) {
			matriz[p.getLinha()][p.getColuna()] = true;
		}

		return matriz;
	}
}