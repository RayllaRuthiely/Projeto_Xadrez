package xadrez;

import tabuleiro.Peca;
import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;

public abstract class PecaXadrez extends Peca {

	// Atributos
	private Cor cor;
	private int quantidadeMovimentos;

	// Construtor
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

	
	public void incrementarQuantidadeMovimentos() {
		quantidadeMovimentos++;
	}

	
	public void decrementarQuantidadeMovimentos() {
		quantidadeMovimentos--;
	}

	// Retorna a posição da peça no formato do xadrez
	public PosicaoXadrez getPosicaoXadrez() {
		if (posicao == null) {
			return null;
		}
		return PosicaoXadrez.daPosicao(posicao);
	}

	// Verifica se existe uma peça adversária na posição
	protected boolean existePecaAdversaria(Posicao posicao) {
		PecaXadrez p = (PecaXadrez) getTabuleiro().obterPeca(posicao);
		return p != null && p.getCor() != this.cor;
	}
	
	@Override
    public abstract String toString();
}