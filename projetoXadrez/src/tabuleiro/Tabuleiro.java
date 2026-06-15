package tabuleiro;

public class Tabuleiro {
	
	//Atributos
	private int linhas;
	private int colunas;
	private Peca[][] pecas;
	
	//Construtor
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

	public void colocarPeca(Peca peca, Posicao posicao) {

		if (existePeca(posicao)) {
			throw new ExcecaoTabuleiro("Já existe uma peça nesta posição.");
		}

		pecas[posicao.getLinha()][posicao.getColuna()] = peca;
		peca.posicao = posicao;
	}

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

	public boolean existePeca(Posicao posicao) {

		if (!existePosicao(posicao)) {
			throw new ExcecaoTabuleiro("Posição inexistente.");
		}

		return obterPeca(posicao) != null;
	}
}
