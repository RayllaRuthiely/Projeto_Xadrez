package tabuleiro;


/**
 * Classe responsável por representar o tabuleiro do jogo.
 * Nela são armazenadas e gerenciadas todas as peças.
 */

public class Tabuleiro {
	
	//Atributos
	private int linhas;
	private int colunas;
	private Peca[][] pecas;
	
	//Construtor
	/**
	 * Constrói um tabuleiro com a quantidade de linhas e colunas informadas.
	 *
	 * @param linhas quantidade de linhas do tabuleiro
	 * @param colunas quantidade de colunas do tabuleiro
	 */
	public Tabuleiro(int linhas, int colunas) {

		this.linhas = linhas;
		this.colunas = colunas;
		this.pecas = new Peca[linhas][colunas];

	}
	
	//Métodos Especiais
	public int getLinhas() {
		return linhas;
	}

	public int getColunas() {
		return colunas;
	}
	
	/**
	 * Retorna a peça localizada na posição informada.
	 *
	 * @param linha linha da posição
	 * @param coluna coluna da posição
	 * @return peça encontrada ou null
	 */
	public Peca obterPeca(int linha, int coluna) {

		if (!existePosicao(linha, coluna)) {
			throw new ExcecaoTabuleiro("Posição inexistente.");
		}

		return pecas[linha][coluna];
	}

	public Peca obterPeca(Posicao posicao) {

		if (!existePosicao(posicao)) {
			throw new ExcecaoTabuleiro("Posição inexistente.");
		}

		return pecas[posicao.getLinha()][posicao.getColuna()];
	}

	/**
	 * Coloca uma peça em uma posição do tabuleiro.
	 *
	 * @param peca peça a ser colocada
	 * @param posicao posição onde será inserida
	 */
	public void colocarPeca(Peca peca, Posicao posicao) {

		if (existePeca(posicao)) {
			throw new ExcecaoTabuleiro("Já existe uma peça nesta posição.");
		}

		pecas[posicao.getLinha()][posicao.getColuna()] = peca;
		peca.posicao = posicao;
	}

	/**
	 * Remove uma peça do tabuleiro.
	 *
	 * @param posicao posição da peça
	 * @return peça removida
	 */
	public Peca removerPeca(Posicao posicao) {

		if (!existePosicao(posicao)) {
			throw new ExcecaoTabuleiro("Posição inexistente.");
		}

		if (obterPeca(posicao) == null) {
			return null;
		}

		Peca aux = obterPeca(posicao);
		aux.posicao = null;
		pecas[posicao.getLinha()][posicao.getColuna()] = null;

		return aux;
	}

	/**
	 * Verifica se existe uma posição válida no tabuleiro.
	 *
	 * @param posicao posição a ser verificada
	 * @return true se a posição existir
	 */
	private boolean existePosicao(int linha, int coluna) {

		return linha >= 0 &&
			   linha < linhas &&
			   coluna >= 0 &&
			   coluna < colunas;
	}

	public boolean existePosicao(Posicao posicao) {

		return existePosicao(
				posicao.getLinha(),
				posicao.getColuna());
	}

	/**
	 * Verifica se existe uma peça na posição informada.
	 *
	 * @param posicao posição a ser verificada
	 * @return true se houver uma peça
	 */
	public boolean existePeca(Posicao posicao) {

		if (!existePosicao(posicao)) {
			throw new ExcecaoTabuleiro("Posição inexistente.");
		}

		return obterPeca(posicao) != null;
	}
}
