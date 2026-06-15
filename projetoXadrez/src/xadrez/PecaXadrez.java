package xadrez;

/**
 * Classe abstrata que representa uma peça de xadrez.
 * Herda da classe Peca e adiciona informações específicas
 * do jogo, como a cor da peça e a quantidade de movimentos
 * realizados durante a partida.
 */

import tabuleiro.Peca;
import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;

public abstract class PecaXadrez extends Peca {

	// Atributos
	private Cor cor;
	private int quantidadeMovimentos;

	/**
	 * Construtor da peça de xadrez.
	 *
	 * @param tabuleiro Tabuleiro onde a peça será posicionada.
	 * @param cor Cor da peça (BRANCO ou PRETO).
	 */
	public PecaXadrez(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro);
		this.cor = cor;
	}

	// Getters
	public Cor getCor() {
		return cor;
	}

	public int getQuantidadeMovimentos() {
		return quantidadeMovimentos;
	}

	/**
	 * Incrementa a quantidade de movimentos realizados pela peça.
	 */
	public void incrementarQuantidadeMovimentos() {
		quantidadeMovimentos++;
	}

	/**
	 * Decrementa a quantidade de movimentos realizados pela peça.
	 * Utilizado quando uma jogada precisa ser desfeita.
	 */
	public void decrementarQuantidadeMovimentos() {
		quantidadeMovimentos--;
	}

	/**
	 * Retorna a posição da peça no formato tradicional do xadrez
	 * (por exemplo: e4, a1, h8).
	 *
	 * @return Posição da peça no tabuleiro de xadrez.
	 */
	public PosicaoXadrez getPosicaoXadrez() {
		if (posicao == null) {
			return null;
		}
		return PosicaoXadrez.daPosicao(posicao);
	}

	/**
	 * Verifica se existe uma peça adversária na posição informada.
	 *
	 * @param posicao Posição a ser verificada.
	 * @return true se existir uma peça do adversário; false caso contrário.
	 */	protected boolean existePecaAdversaria(Posicao posicao) {
		PecaXadrez p = (PecaXadrez) getTabuleiro().obterPeca(posicao);
		return p != null && p.getCor() != this.cor;
	}
	 
		/**
		 * Retorna a representação textual da peça.
		 */
	@Override
    public abstract String toString();
}