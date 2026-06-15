package xadrez_pecas;

import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;
import xadrez.Cor;
import xadrez.PartidaXadrez;
import xadrez.PecaXadrez;

public class Peao extends PecaXadrez {

	private PartidaXadrez partidaXadrez;

	// Construtor
	public Peao(Tabuleiro tabuleiro, Cor cor, PartidaXadrez partidaXadrez) {
		super(tabuleiro, cor);
		this.partidaXadrez = partidaXadrez;
	}

	@Override
	public String toString() {
		return "P";
	}

	@Override
	public boolean[][] movimentosPossiveis() {

		boolean[][] matriz =
				new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];

		Posicao p = new Posicao(0, 0);

		if (getCor() == Cor.BRANCO) {

			// Uma casa para frente
			p.setValores(posicao.getLinha() - 1, posicao.getColuna());

			if (getTabuleiro().existePosicao(p)
					&& !getTabuleiro().existePeca(p)) {

				matriz[p.getLinha()][p.getColuna()] = true;
			}

			// Duas casas no primeiro movimento
			p.setValores(posicao.getLinha() - 2, posicao.getColuna());

			Posicao p2 =
					new Posicao(posicao.getLinha() - 1, posicao.getColuna());

			if (getTabuleiro().existePosicao(p)
					&& !getTabuleiro().existePeca(p)
					&& getTabuleiro().existePosicao(p2)
					&& !getTabuleiro().existePeca(p2)
					&& getQuantidadeMovimentos() == 0) {

				matriz[p.getLinha()][p.getColuna()] = true;
			}

			// Captura diagonal esquerda
			p.setValores(posicao.getLinha() - 1, posicao.getColuna() - 1);

			if (getTabuleiro().existePosicao(p)
					&& existePecaAdversaria(p)) {

				matriz[p.getLinha()][p.getColuna()] = true;
			}

			// Captura diagonal direita
			p.setValores(posicao.getLinha() - 1, posicao.getColuna() + 1);

			if (getTabuleiro().existePosicao(p)
					&& existePecaAdversaria(p)) {

				matriz[p.getLinha()][p.getColuna()] = true;
			}

			// En Passant (Brancas)
			if (posicao.getLinha() == 3) {

				Posicao esquerda =
						new Posicao(posicao.getLinha(), posicao.getColuna() - 1);

				if (getTabuleiro().existePosicao(esquerda)
						&& existePecaAdversaria(esquerda)
						&& getTabuleiro().obterPeca(esquerda)
						== partidaXadrez.getEnPassantVulneravel()) {

					matriz[esquerda.getLinha() - 1][esquerda.getColuna()] = true;
				}

				Posicao direita =
						new Posicao(posicao.getLinha(), posicao.getColuna() + 1);

				if (getTabuleiro().existePosicao(direita)
						&& existePecaAdversaria(direita)
						&& getTabuleiro().obterPeca(direita)
						== partidaXadrez.getEnPassantVulneravel()) {

					matriz[direita.getLinha() - 1][direita.getColuna()] = true;
				}
			}
		}
		else {

			// Uma casa para frente
			p.setValores(posicao.getLinha() + 1, posicao.getColuna());

			if (getTabuleiro().existePosicao(p)
					&& !getTabuleiro().existePeca(p)) {

				matriz[p.getLinha()][p.getColuna()] = true;
			}

			// Duas casas no primeiro movimento
			p.setValores(posicao.getLinha() + 2, posicao.getColuna());

			Posicao p2 =
					new Posicao(posicao.getLinha() + 1, posicao.getColuna());

			if (getTabuleiro().existePosicao(p)
					&& !getTabuleiro().existePeca(p)
					&& getTabuleiro().existePosicao(p2)
					&& !getTabuleiro().existePeca(p2)
					&& getQuantidadeMovimentos() == 0) {

				matriz[p.getLinha()][p.getColuna()] = true;
			}

			// Captura diagonal esquerda
			p.setValores(posicao.getLinha() + 1, posicao.getColuna() - 1);

			if (getTabuleiro().existePosicao(p)
					&& existePecaAdversaria(p)) {

				matriz[p.getLinha()][p.getColuna()] = true;
			}

			// Captura diagonal direita
			p.setValores(posicao.getLinha() + 1, posicao.getColuna() + 1);

			if (getTabuleiro().existePosicao(p)
					&& existePecaAdversaria(p)) {

				matriz[p.getLinha()][p.getColuna()] = true;
			}

			// En Passant (Pretas)
			if (posicao.getLinha() == 4) {

				Posicao esquerda =
						new Posicao(posicao.getLinha(), posicao.getColuna() - 1);

				if (getTabuleiro().existePosicao(esquerda)
						&& existePecaAdversaria(esquerda)
						&& getTabuleiro().obterPeca(esquerda)
						== partidaXadrez.getEnPassantVulneravel()) {

					matriz[esquerda.getLinha() + 1][esquerda.getColuna()] = true;
				}

				Posicao direita =
						new Posicao(posicao.getLinha(), posicao.getColuna() + 1);

				if (getTabuleiro().existePosicao(direita)
						&& existePecaAdversaria(direita)
						&& getTabuleiro().obterPeca(direita)
						== partidaXadrez.getEnPassantVulneravel()) {

					matriz[direita.getLinha() + 1][direita.getColuna()] = true;
				}
			}
		}

		return matriz;
	}
}