package tabuleiro;

public class Posicao {

	// Atributos
	private int linha;
	private int coluna;

	// Construtor
	public Posicao(int linha, int coluna) {
		this.linha = linha;
		this.coluna = coluna;
	}

	// Getters
	public int getLinha() {
		return linha;
	}

	public int getColuna() {
		return coluna;
	}

	// Setters
	public void setLinha(int linha) {
		this.linha = linha;
	}

	public void setColuna(int coluna) {
		this.coluna = coluna;
	}

	// Altera linha e coluna ao mesmo tempo
	public void setValores(int linha, int coluna) {
		this.linha = linha;
		this.coluna = coluna;
	}

	// Representação em texto da posição
	@Override
	public String toString() {
		return linha + ", " + coluna;
	}

	// Verifica se duas posições são iguais
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

	// Gera um código hash compatível com equals
	@Override
	public int hashCode() {
		final int primo = 31;
		int resultado = 1;
		resultado = primo * resultado + linha;
		resultado = primo * resultado + coluna;
		return resultado;
	}
}
