package tabuleiro;


/**
 * Classe responsável por representar uma posição no tabuleiro.
 * Armazena os valores de linha e coluna utilizados para localizar
 * uma peça no jogo.
 */

public class Posicao {

	// Atributos
	private int linha;
	private int coluna;

	// Construtor
	/**
	 * Constrói uma posição com a linha e a coluna informadas.
	 *
	 * @param linha linha da posição
	 * @param coluna coluna da posição
	 */
	public Posicao(int linha, int coluna) {
		this.linha = linha;
		this.coluna = coluna;
	}

	/**
	 * Retorna a linha da posição.
	 */
	public int getLinha() {
		return linha;
	}

	/**
	 * Retorna a coluna da posição.
	 */
	public int getColuna() {
		return coluna;
	}

	/**
	 * Altera a linha da posição.
	 */
	public void setLinha(int linha) {
		this.linha = linha;
	}

	/**
	 * Altera a coluna da posição.
	 */
	public void setColuna(int coluna) {
		this.coluna = coluna;
	}


	/**
	 * Altera simultaneamente os valores de linha e coluna.
	 */
	public void setValores(int linha, int coluna) {
		this.linha = linha;
		this.coluna = coluna;
	}


	/**
	 * Retorna a representação textual da posição.
	 */
	@Override
	public String toString() {
		return linha + ", " + coluna;
	}

	/**
	 * Verifica se duas posições são iguais.
	 */
	@Override
	public boolean equals(Object obj) {

		if (this == obj) {
			return true;
		}

		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}

		Posicao outra = (Posicao) obj;

		return linha == outra.linha && coluna == outra.coluna;
	}

	/**
	 * Gera um código hash compatível com o método equals.
	 */
	@Override
	public int hashCode() {
		final int primo = 31;
		int resultado = 1;
		resultado = primo * resultado + linha;
		resultado = primo * resultado + coluna;
		return resultado;
	}
}
