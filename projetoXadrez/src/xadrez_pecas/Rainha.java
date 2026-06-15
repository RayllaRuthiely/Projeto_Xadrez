package xadrez_pecas;

import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;
import xadrez.Cor;
import xadrez.PecaXadrez;

public class Rainha extends PecaXadrez {

	// Construtor
	public Rainha(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro, cor);
	}

	@Override
	public String toString() {
		return "Q";
	}

	@Override
	public boolean[][] movimentosPossiveis() {

		boolean[][] matriz =
				new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];

		Posicao p = new Posicao(0, 0);

		// Acima
		p.setValores(posicao.getLinha() - 1, posicao.getColuna());

		while (getTabuleiro().existePosicao(p)
				&& !getTabuleiro().existePeca(p)) {

			matriz[p.getLinha()][p.getColuna()] = true;
			p.setLinha(p.getLinha() - 1);
		}

		if (getTabuleiro().existePosicao(p)
				&& existePecaAdversaria(p)) {

			matriz[p.getLinha()][p.getColuna()] = true;
		}

		// Esquerda
		p.setValores(posicao.getLinha(), posicao.getColuna() - 1);

		while (getTabuleiro().existePosicao(p)
				&& !getTabuleiro().existePeca(p)) {

			matriz[p.getLinha()][p.getColuna()] = true;
			p.setColuna(p.getColuna() - 1);
		}

		if (getTabuleiro().existePosicao(p)
				&& existePecaAdversaria(p)) {

			matriz[p.getLinha()][p.getColuna()] = true;
		}

		// Direita
		p.setValores(posicao.getLinha(), posicao.getColuna() + 1);

		while (getTabuleiro().existePosicao(p)
				&& !getTabuleiro().existePeca(p)) {

			matriz[p.getLinha()][p.getColuna()] = true;
			p.setColuna(p.getColuna() + 1);
		}

		if (getTabuleiro().existePosicao(p)
				&& existePecaAdversaria(p)) {

			matriz[p.getLinha()][p.getColuna()] = true;
		}

		// Abaixo
		p.setValores(posicao.getLinha() + 1, posicao.getColuna());

		while (getTabuleiro().existePosicao(p)
				&& !getTabuleiro().existePeca(p)) {

			matriz[p.getLinha()][p.getColuna()] = true;
			p.setLinha(p.getLinha() + 1);
		}

		if (getTabuleiro().existePosicao(p)
				&& existePecaAdversaria(p)) {

			matriz[p.getLinha()][p.getColuna()] = true;
		}

		// Noroeste
		p.setValores(posicao.getLinha() - 1, posicao.getColuna() - 1);

		while (getTabuleiro().existePosicao(p)
				&& !getTabuleiro().existePeca(p)) {

			matriz[p.getLinha()][p.getColuna()] = true;
			p.setValores(p.getLinha() - 1, p.getColuna() - 1);
		}

		if (getTabuleiro().existePosicao(p)
				&& existePecaAdversaria(p)) {

			matriz[p.getLinha()][p.getColuna()] = true;
		}

		// Nordeste
		p.setValores(posicao.getLinha() - 1, posicao.getColuna() + 1);

		while (getTabuleiro().existePosicao(p)
				&& !getTabuleiro().existePeca(p)) {

			matriz[p.getLinha()][p.getColuna()] = true;
			p.setValores(p.getLinha() - 1, p.getColuna() + 1);
		}

		if (getTabuleiro().existePosicao(p)
				&& existePecaAdversaria(p)) {

			matriz[p.getLinha()][p.getColuna()] = true;
		}

		// Sudeste
		p.setValores(posicao.getLinha() + 1, posicao.getColuna() + 1);

		while (getTabuleiro().existePosicao(p)
				&& !getTabuleiro().existePeca(p)) {

			matriz[p.getLinha()][p.getColuna()] = true;
			p.setValores(p.getLinha() + 1, p.getColuna() + 1);
		}

		if (getTabuleiro().existePosicao(p)
				&& existePecaAdversaria(p)) {

			matriz[p.getLinha()][p.getColuna()] = true;
		}

		// Sudoeste
		p.setValores(posicao.getLinha() + 1, posicao.getColuna() - 1);

		while (getTabuleiro().existePosicao(p)
				&& !getTabuleiro().existePeca(p)) {

			matriz[p.getLinha()][p.getColuna()] = true;
			p.setValores(p.getLinha() + 1, p.getColuna() - 1);
		}

		if (getTabuleiro().existePosicao(p)
				&& existePecaAdversaria(p)) {

			matriz[p.getLinha()][p.getColuna()] = true;
		}

		return matriz;
	}
}