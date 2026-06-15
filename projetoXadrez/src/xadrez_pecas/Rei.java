package xadrez_pecas;

import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;
import xadrez.Cor;
import xadrez.PartidaXadrez;
import xadrez.PecaXadrez;

public class Rei extends PecaXadrez {

	private PartidaXadrez partidaXadrez;

	// Construtor
	public Rei(Tabuleiro tabuleiro, Cor cor, PartidaXadrez partidaXadrez) {
		super(tabuleiro, cor);
		this.partidaXadrez = partidaXadrez;
	}

	@Override
	public String toString() {
		return "R";
	}

	// Verifica se o rei pode mover para uma posição
	private boolean podeMover(Posicao posicao) {

		PecaXadrez p =
				(PecaXadrez) getTabuleiro().obterPeca(posicao);

		return p == null || p.getCor() != getCor();
	}

	// Verifica se a torre pode participar do roque
	private boolean testeRoqueTorre(Posicao posicao) {

		PecaXadrez p =
				(PecaXadrez) getTabuleiro().obterPeca(posicao);

		return p != null
				&& p instanceof Torre
				&& p.getCor() == getCor()
				&& p.getQuantidadeMovimentos() == 0;
	}

	@Override
	public boolean[][] movimentosPossiveis() {

		boolean[][] matriz =
				new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];

		Posicao p = new Posicao(0, 0);

		// Acima
		p.setValores(posicao.getLinha() - 1, posicao.getColuna());
		if (getTabuleiro().existePosicao(p) && podeMover(p)) {
			matriz[p.getLinha()][p.getColuna()] = true;
		}

		// Abaixo
		p.setValores(posicao.getLinha() + 1, posicao.getColuna());
		if (getTabuleiro().existePosicao(p) && podeMover(p)) {
			matriz[p.getLinha()][p.getColuna()] = true;
		}

		// Esquerda
		p.setValores(posicao.getLinha(), posicao.getColuna() - 1);
		if (getTabuleiro().existePosicao(p) && podeMover(p)) {
			matriz[p.getLinha()][p.getColuna()] = true;
		}

		// Direita
		p.setValores(posicao.getLinha(), posicao.getColuna() + 1);
		if (getTabuleiro().existePosicao(p) && podeMover(p)) {
			matriz[p.getLinha()][p.getColuna()] = true;
		}

		// Noroeste
		p.setValores(posicao.getLinha() - 1, posicao.getColuna() - 1);
		if (getTabuleiro().existePosicao(p) && podeMover(p)) {
			matriz[p.getLinha()][p.getColuna()] = true;
		}

		// Nordeste
		p.setValores(posicao.getLinha() - 1, posicao.getColuna() + 1);
		if (getTabuleiro().existePosicao(p) && podeMover(p)) {
			matriz[p.getLinha()][p.getColuna()] = true;
		}

		// Sudoeste
		p.setValores(posicao.getLinha() + 1, posicao.getColuna() - 1);
		if (getTabuleiro().existePosicao(p) && podeMover(p)) {
			matriz[p.getLinha()][p.getColuna()] = true;
		}

		// Sudeste
		p.setValores(posicao.getLinha() + 1, posicao.getColuna() + 1);
		if (getTabuleiro().existePosicao(p) && podeMover(p)) {
			matriz[p.getLinha()][p.getColuna()] = true;
		}

		// Movimento especial: Roque
		if (getQuantidadeMovimentos() == 0 && !partidaXadrez.getXeque()) {

			// Roque pequeno
			Posicao posTorre1 =
					new Posicao(posicao.getLinha(), posicao.getColuna() + 3);

			if (testeRoqueTorre(posTorre1)) {

				Posicao p1 =
						new Posicao(posicao.getLinha(), posicao.getColuna() + 1);

				Posicao p2 =
						new Posicao(posicao.getLinha(), posicao.getColuna() + 2);

				if (getTabuleiro().obterPeca(p1) == null
						&& getTabuleiro().obterPeca(p2) == null) {

					matriz[posicao.getLinha()][posicao.getColuna() + 2] = true;
				}
			}

			// Roque grande
			Posicao posTorre2 =
					new Posicao(posicao.getLinha(), posicao.getColuna() - 4);

			if (testeRoqueTorre(posTorre2)) {

				Posicao p1 =
						new Posicao(posicao.getLinha(), posicao.getColuna() - 1);

				Posicao p2 =
						new Posicao(posicao.getLinha(), posicao.getColuna() - 2);

				Posicao p3 =
						new Posicao(posicao.getLinha(), posicao.getColuna() - 3);

				if (getTabuleiro().obterPeca(p1) == null
						&& getTabuleiro().obterPeca(p2) == null
						&& getTabuleiro().obterPeca(p3) == null) {

					matriz[posicao.getLinha()][posicao.getColuna() - 2] = true;
				}
			}
		}

		return matriz;
	}
}
