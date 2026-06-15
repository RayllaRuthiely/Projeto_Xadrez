package tabuleiro;

public abstract class Peca {

	// Atributos
	protected Posicao posicao;
	private Tabuleiro tabuleiro;

	// Construtor
	public Peca(Tabuleiro tabuleiro) {
		this.tabuleiro = tabuleiro;
		this.posicao = null;
	}

	// Retorna o tabuleiro ao qual a peça pertence
	protected Tabuleiro getTabuleiro() {
		return tabuleiro;
	}

	// Cada peça implementará seus próprios movimentos
	public abstract boolean[][] movimentosPossiveis();

	// Verifica se um movimento específico é possível (Com validação de segurança)
	public boolean movimentoPossivel(Posicao posicao) {
		// Garante que a posição existe no tabuleiro antes de checar a matriz
		if (!tabuleiro.existePosicao(posicao)) {
			return false;
		}
		return movimentosPossiveis()[posicao.getLinha()][posicao.getColuna()];
	}

	// Verifica se existe pelo menos um movimento possível
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