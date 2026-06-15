package xadrez_pecas;

import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;
import xadrez.Cor;
import xadrez.PecaXadrez;

public class Bispo extends PecaXadrez {

	// Construtor
	public Bispo(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro, cor);
	}

	@Override
	public String toString() {
		return "B";
	}

	@Override
	public boolean[][] movimentosPossiveis() {

		boolean[][] matriz =
				new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];

		Posicao posicao = new Posicao(0, 0);

		// Noroeste
		posicao.setValores(this.posicao.getLinha() - 1, this.posicao.getColuna() - 1);

		
		while (getTabuleiro().existePosicao(posicao)
				&& !getTabuleiro().existePeca(posicao)) {

			matriz[posicao.getLinha()][posicao.getColuna()] = true;

			posicao.setValores(
					posicao.getLinha() - 1,
					posicao.getColuna() - 1);
		}

		if (getTabuleiro().existePosicao(posicao)
				&& existePecaAdversaria(posicao)) {

			matriz[posicao.getLinha()][posicao.getColuna()] = true;
		}

		// Nordeste
		posicao.setValores(this.posicao.getLinha() - 1, this.posicao.getColuna() + 1);

		
		while (getTabuleiro().existePosicao(posicao)
				&& !getTabuleiro().existePeca(posicao)) {

			matriz[posicao.getLinha()][posicao.getColuna()] = true;

			posicao.setValores(
					posicao.getLinha() - 1,
					posicao.getColuna() + 1);
		}

		if (getTabuleiro().existePosicao(posicao)
				&& existePecaAdversaria(posicao)) {

			matriz[posicao.getLinha()][posicao.getColuna()] = true;
		}

		// Sudeste
		posicao.setValores(this.posicao.getLinha() + 1, this.posicao.getColuna() + 1);

		while (getTabuleiro().existePosicao(posicao)
				&& !getTabuleiro().existePeca(posicao)) {

			matriz[posicao.getLinha()][posicao.getColuna()] = true;

			posicao.setValores(
					posicao.getLinha() + 1,
					posicao.getColuna() + 1);
		}

		if (getTabuleiro().existePosicao(posicao)
				&& existePecaAdversaria(posicao)) {

			matriz[posicao.getLinha()][posicao.getColuna()] = true;
		}

		// Sudoeste
		posicao.setValores(this.posicao.getLinha() + 1, this.posicao.getColuna() - 1);


		while (getTabuleiro().existePosicao(posicao)
				&& !getTabuleiro().existePeca(posicao)) {

			matriz[posicao.getLinha()][posicao.getColuna()] = true;

			posicao.setValores(
					posicao.getLinha() + 1,
					posicao.getColuna() - 1);
		}

		if (getTabuleiro().existePosicao(posicao)
				&& existePecaAdversaria(posicao)) {

			matriz[posicao.getLinha()][posicao.getColuna()] = true;
		}

		return matriz;
	}
}
