package tabuleiro;

/**
 * Classe abstrata que representa uma peça do tabuleiro.
 * Serve como classe base para todas as peças do jogo,
 * definindo seus movimentos e comportamentos básicos.
 */

public abstract class Peca {

	// Atributos
	protected Posicao posicao;
	private Tabuleiro tabuleiro;

	// Construtor
	/**
	 * Constrói uma peça associada a um tabuleiro.
	 *
	 * @param tabuleiro tabuleiro ao qual a peça pertence.
	 */
	public Peca(Tabuleiro tabuleiro) {
		this.tabuleiro = tabuleiro;
		this.posicao = null;
	}

	/**
	 * Retorna o tabuleiro ao qual a peça pertence.
	 *
	 * @return tabuleiro da peça.
	 */
	protected Tabuleiro getTabuleiro() {
		return tabuleiro;
	}

	/**
	 * Retorna uma matriz indicando todos os movimentos
	 * possíveis da peça.
	 *
	 * @return matriz de movimentos possíveis.
	 */
	public abstract boolean[][] movimentosPossiveis();
	

	/**
	 * Verifica se um movimento para uma determinada posição é possível.
	 *
	 * @param posicao posição de destino.
	 * @return true se o movimento for válido, false caso contrário.
	 */
	public boolean movimentoPossivel(Posicao posicao) {
		// Garante que a posição existe no tabuleiro antes de checar a matriz
		if (!tabuleiro.existePosicao(posicao)) {
			return false;
		}
		return movimentosPossiveis()[posicao.getLinha()][posicao.getColuna()];
	}

	/**
	 * Verifica se a peça possui pelo menos um movimento possível.
	 *
	 * @return true se existir algum movimento válido.
	 */
	public boolean existeAlgumMovimentoPossivel() {
		boolean[][] matriz = movimentosPossiveis();

		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz[i].length; j++) {
				if (matriz[i][j]) {
					return true;
				}
			}
		}
		return false;
	}
}