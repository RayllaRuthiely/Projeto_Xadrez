package xadrez;

/**
 * Classe de exceção específica do jogo de xadrez.
 * É utilizada para indicar erros relacionados às regras
 * da partida, como movimentos inválidos ou jogadas ilegais.
 */

import tabuleiro.ExcecaoTabuleiro;

public class ExcecaoXadrez extends ExcecaoTabuleiro {

	private static final long serialVersionUID = 1L;

	/**
	 * Construtor da exceção de xadrez.
	 *
	 * @param mensagem Mensagem que descreve o erro ocorrido.
	 */
	public ExcecaoXadrez(String mensagem) {
		super(mensagem);
	}

}
