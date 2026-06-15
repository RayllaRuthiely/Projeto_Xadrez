package xadrez;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import tabuleiro.Peca;
import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;

import xadrez_pecas.Bispo;
import xadrez_pecas.Cavalo;
import xadrez_pecas.Peao;
import xadrez_pecas.Rainha;
import xadrez_pecas.Rei;
import xadrez_pecas.Torre;

public class PartidaXadrez {

	// Atributos
	private int turno;
	private Cor jogadorAtual;
	private Tabuleiro tabuleiro;
	private boolean xeque;
	private boolean xequeMate;
	private PecaXadrez enPassantVulneravel;
	private PecaXadrez promovida;

	private List<Peca> pecasNoTabuleiro = new ArrayList<>();
	private List<Peca> pecasCapturadas = new ArrayList<>();

	// Construtor
	public PartidaXadrez() {
		tabuleiro = new Tabuleiro(8, 8);
		turno = 1;
		jogadorAtual = Cor.BRANCO;
		configuracaoInicial();
	}
	
	// Métodos Especiais

	public int getTurno() {
		return turno;
	}

	public Cor getJogadorAtual() {
		return jogadorAtual;
	}

	public boolean getXeque() {
		return xeque;
	}

	public boolean getXequeMate() {
		return xequeMate;
	}

	public PecaXadrez getEnPassantVulneravel() {
		return enPassantVulneravel;
	}

	public PecaXadrez getPromovida() {
		return promovida;
	}

	public PecaXadrez[][] getPecas() {
		PecaXadrez[][] matriz = new PecaXadrez[tabuleiro.getLinhas()][tabuleiro.getColunas()];
		for (int i = 0; i < tabuleiro.getLinhas(); i++) {
			for (int j = 0; j < tabuleiro.getColunas(); j++) {
				matriz[i][j] = (PecaXadrez) tabuleiro.obterPeca(i, j);
			}
		}
		return matriz;
	}

	public boolean[][] movimentosPossiveis(PosicaoXadrez posicaoOrigem) {
		Posicao posicao = posicaoOrigem.toPosicao();
		validarPosicaoOrigem(posicao);
		return tabuleiro.obterPeca(posicao).movimentosPossiveis();
	}

	public PecaXadrez executarJogadaXadrez(PosicaoXadrez posicaoOrigem, PosicaoXadrez posicaoDestino) {
		Posicao origem = posicaoOrigem.toPosicao();
		Posicao destino = posicaoDestino.toPosicao();

		validarPosicaoOrigem(origem);
		validarPosicaoDestino(origem, destino);

		Peca pecaCapturada = executarMovimento(origem, destino);

		if (testeXeque(jogadorAtual)) {
			desfazerMovimento(origem, destino, pecaCapturada);
			throw new ExcecaoXadrez("Você não pode se colocar em xeque.");
		}

		PecaXadrez pecaMovida = (PecaXadrez) tabuleiro.obterPeca(destino);

		// Movimento especial: Promoção
		promovida = null;
		if (pecaMovida instanceof Peao) {
			if ((pecaMovida.getCor() == Cor.BRANCO && destino.getLinha() == 0)
					|| (pecaMovida.getCor() == Cor.PRETO && destino.getLinha() == 7)) {
				promovida = (PecaXadrez) tabuleiro.obterPeca(destino);
				promovida = substituirPecaPromovida("Q");
			}
		}

		xeque = testeXeque(adversario(jogadorAtual));

		if (testeXequeMate(adversario(jogadorAtual))) {
			xequeMate = true;
		} else {
			proximoTurno();
		}

		// Movimento especial: En Passant
		if (pecaMovida instanceof Peao
				&& (destino.getLinha() == origem.getLinha() - 2
				|| destino.getLinha() == origem.getLinha() + 2)) {
			enPassantVulneravel = pecaMovida;
		} else {
			enPassantVulneravel = null;
		}

		return (PecaXadrez) pecaCapturada;
	}
	
	// Substitui a peça promovida
	public PecaXadrez substituirPecaPromovida(String tipo) {
		if (promovida == null) {
			throw new IllegalStateException("Não existe peça para ser promovida.");
		}
		if (!tipo.equals("B") && !tipo.equals("C") && !tipo.equals("T") && !tipo.equals("Q")) {
			return promovida;
		}

		Posicao posicao = promovida.getPosicaoXadrez().toPosicao();
		Peca p = tabuleiro.removerPeca(posicao);
		pecasNoTabuleiro.remove(p);

		PecaXadrez novaPeca = novaPeca(tipo, promovida.getCor());
		tabuleiro.colocarPeca(novaPeca, posicao);
		pecasNoTabuleiro.add(novaPeca);

		return novaPeca;
	}

	// Cria uma nova peça
	private PecaXadrez novaPeca(String tipo, Cor cor) {
		if (tipo.equals("B")) {
			return new Bispo(tabuleiro, cor);
		}
		if (tipo.equals("C")) {
			return new Cavalo(tabuleiro, cor);
		}
		if (tipo.equals("Q")) {
			return new Rainha(tabuleiro, cor);
		}
		return new Torre(tabuleiro, cor);
	}

	// Executa um movimento
	private Peca executarMovimento(Posicao origem, Posicao destino) {
		PecaXadrez p = (PecaXadrez) tabuleiro.removerPeca(origem);
		p.incrementarQuantidadeMovimentos();

		Peca pecaCapturada = tabuleiro.removerPeca(destino);
		tabuleiro.colocarPeca(p, destino);

		if (pecaCapturada != null) {
			pecasNoTabuleiro.remove(pecaCapturada);
			pecasCapturadas.add(pecaCapturada);
		}

		// Movimento especial: Roque pequeno
		if (p instanceof Rei && destino.getColuna() == origem.getColuna() + 2) {
			Posicao origemTorre = new Posicao(origem.getLinha(), origem.getColuna() + 3);
			Posicao destinoTorre = new Posicao(origem.getLinha(), origem.getColuna() + 1);
			PecaXadrez torre = (PecaXadrez) tabuleiro.removerPeca(origemTorre);
			tabuleiro.colocarPeca(torre, destinoTorre);
			torre.incrementarQuantidadeMovimentos();
		}

		// Movimento especial: Roque grande
		if (p instanceof Rei && destino.getColuna() == origem.getColuna() - 2) {
			Posicao origemTorre = new Posicao(origem.getLinha(), origem.getColuna() - 4);
			Posicao destinoTorre = new Posicao(origem.getLinha(), origem.getColuna() - 1);
			PecaXadrez torre = (PecaXadrez) tabuleiro.removerPeca(origemTorre);
			tabuleiro.colocarPeca(torre, destinoTorre);
			torre.incrementarQuantidadeMovimentos();
		}

		// Movimento especial: En Passant
		if (p instanceof Peao) {
			if (origem.getColuna() != destino.getColuna() && pecaCapturada == null) {
				Posicao posicaoPeao;
				if (p.getCor() == Cor.BRANCO) {
					posicaoPeao = new Posicao(destino.getLinha() + 1, destino.getColuna());
				} else {
					posicaoPeao = new Posicao(destino.getLinha() - 1, destino.getColuna());
				}
				pecaCapturada = tabuleiro.removerPeca(posicaoPeao);
				pecasCapturadas.add(pecaCapturada);
				pecasNoTabuleiro.remove(pecaCapturada);
			}
		}

		return pecaCapturada;
	}

	// Desfaz um movimento
	private void desfazerMovimento(Posicao origem, Posicao destino, Peca pecaCapturada) {
	    PecaXadrez p = (PecaXadrez) tabuleiro.removerPeca(destino);
	    p.decrementarQuantidadeMovimentos();
	    tabuleiro.colocarPeca(p, origem);

	    if (pecaCapturada != null) {
	        tabuleiro.colocarPeca(pecaCapturada, destino);
	        pecasCapturadas.remove(pecaCapturada);
	        pecasNoTabuleiro.add(pecaCapturada);
	    }
	    
	    // Movimento especial: Roque pequeno
	    if (p instanceof Rei && destino.getColuna() == origem.getColuna() + 2) {
	        Posicao origemTorre = new Posicao(origem.getLinha(), origem.getColuna() + 3);
	        Posicao destinoTorre = new Posicao(origem.getLinha(), origem.getColuna() + 1);
	        PecaXadrez torre = (PecaXadrez) tabuleiro.removerPeca(destinoTorre);
	        tabuleiro.colocarPeca(torre, origemTorre);
	        torre.decrementarQuantidadeMovimentos();
	    }

	    // Movimento especial: Roque grande
	    if (p instanceof Rei && destino.getColuna() == origem.getColuna() - 2) {
	        Posicao origemTorre = new Posicao(origem.getLinha(), origem.getColuna() - 4);
	        Posicao destinoTorre = new Posicao(origem.getLinha(), origem.getColuna() - 1);
	        PecaXadrez torre = (PecaXadrez) tabuleiro.removerPeca(destinoTorre);
	        tabuleiro.colocarPeca(torre, origemTorre);
	        torre.decrementarQuantidadeMovimentos();
	    }

	    // Movimento especial: En Passant (CORRIGIDO)
	    if (p instanceof Peao) {
	        if (origem.getColuna() != destino.getColuna() && pecaCapturada == enPassantVulneravel) {
	            // Remove a peça capturada do destino comum (onde o if geral a colocou erroneamente)
	            tabuleiro.removerPeca(destino); 
	            
	            Posicao posicaoPeao;
	            if (p.getCor() == Cor.BRANCO) {
	                posicaoPeao = new Posicao(3, destino.getColuna());
	            } else {
	                posicaoPeao = new Posicao(4, destino.getColuna());
	            }
	            // Devolve o peão capturado para a linha correta dele
	            tabuleiro.colocarPeca(pecaCapturada, posicaoPeao); 
	        }
	    }
	}
	
	// Valida a posição de origem
	private void validarPosicaoOrigem(Posicao posicao) {
		if (!tabuleiro.existePeca(posicao)) {
			throw new ExcecaoXadrez("Não existe peça na posição de origem.");
		}
		if (jogadorAtual != ((PecaXadrez) tabuleiro.obterPeca(posicao)).getCor()) {
			throw new ExcecaoXadrez("A peça escolhida não é sua.");
		}
		if (!tabuleiro.obterPeca(posicao).existeAlgumMovimentoPossivel()) {
			throw new ExcecaoXadrez("Não existem movimentos possíveis para a peça escolhida.");
		}
	}

	// Valida a posição de destino
	private void validarPosicaoDestino(Posicao origem, Posicao destino) {
		if (!tabuleiro.obterPeca(origem).movimentoPossivel(destino)) {
			throw new ExcecaoXadrez("A peça escolhida não pode se mover para a posição de destino.");
		}
	}

	// Passa para o próximo turno
	private void proximoTurno() {
		turno++;
		jogadorAtual = (jogadorAtual == Cor.BRANCO) ? Cor.PRETO : Cor.BRANCO;
	}

	// Retorna o adversário
	private Cor adversario(Cor cor) {
		return (cor == Cor.BRANCO) ? Cor.PRETO : Cor.BRANCO;
	}

	// Retorna o rei de uma determinada cor
	private PecaXadrez rei(Cor cor) {
		List<Peca> lista = pecasNoTabuleiro.stream()
				.filter(x -> ((PecaXadrez) x).getCor() == cor)
				.collect(Collectors.toList());

		for (Peca p : lista) {
			if (p instanceof Rei) {
				return (PecaXadrez) p;
			}
		}
		throw new IllegalStateException("Não existe rei " + cor + " no tabuleiro.");
	}
	
	private boolean testeXeque(Cor cor) {
	    Posicao kingPos = rei(cor).getPosicaoXadrez().toPosicao();

	    List<PecaXadrez> pecasOponentes = pecasNoTabuleiro.stream()
	            .map(x -> (PecaXadrez) x)
	            .filter(p -> p.getCor() == adversario(cor)) // Corrigido aqui (de corOponente para adversario)
	            .collect(Collectors.toList());

	    for (PecaXadrez p : pecasOponentes) {
	        boolean[][] mat = p.movimentosPossiveis();

	        if (mat[kingPos.getLinha()][kingPos.getColuna()]) {
	            return true;
	        }
	    }
	    return false;
	}
	
	private boolean testeXequeMate(Cor cor) {
	    if (!testeXeque(cor)) {
	        return false;
	    }

	    List<PecaXadrez> pecas = pecasNoTabuleiro.stream()
	            .map(x -> (PecaXadrez) x)
	            .filter(p -> p.getCor() == cor)
	            .collect(Collectors.toList());

	    for (PecaXadrez peca : pecas) {
	        boolean[][] movimentos = peca.movimentosPossiveis();

	        for (int i = 0; i < tabuleiro.getLinhas(); i++) {
	            for (int j = 0; j < tabuleiro.getColunas(); j++) {
	                if (movimentos[i][j]) {
	                    Posicao origem = peca.getPosicaoXadrez().toPosicao();
	                    Posicao destino = new Posicao(i, j);

	                    PecaXadrez capturada = (PecaXadrez) executarMovimento(origem, destino);
	                    boolean continuaEmXeque = testeXeque(cor);
	                    desfazerMovimento(origem, destino, capturada);

	                    if (!continuaEmXeque) {
	                        return false;
	                    }
	                }
	            }
	        }
	    }
	    return true;
	}
	
	private void colocarNovaPeca(char coluna, int linha, PecaXadrez peca) {
	    tabuleiro.colocarPeca(peca, new PosicaoXadrez(coluna, linha).toPosicao());
	    pecasNoTabuleiro.add(peca);
	}
	
	private void configuracaoInicial() {
	    // Brancas
	    colocarNovaPeca('a', 1, new Torre(tabuleiro, Cor.BRANCO));
	    colocarNovaPeca('b', 1, new Cavalo(tabuleiro, Cor.BRANCO));
	    colocarNovaPeca('c', 1, new Bispo(tabuleiro, Cor.BRANCO));
	    colocarNovaPeca('d', 1, new Rainha(tabuleiro, Cor.BRANCO));
	    colocarNovaPeca('e', 1, new Rei(tabuleiro, Cor.BRANCO, this));
	    colocarNovaPeca('f', 1, new Bispo(tabuleiro, Cor.BRANCO));
	    colocarNovaPeca('g', 1, new Cavalo(tabuleiro, Cor.BRANCO));
	    colocarNovaPeca('h', 1, new Torre(tabuleiro, Cor.BRANCO));

	    colocarNovaPeca('a', 2, new Peao(tabuleiro, Cor.BRANCO, this));
	    colocarNovaPeca('b', 2, new Peao(tabuleiro, Cor.BRANCO, this));
	    colocarNovaPeca('c', 2, new Peao(tabuleiro, Cor.BRANCO, this));
	    colocarNovaPeca('d', 2, new Peao(tabuleiro, Cor.BRANCO, this));
	    colocarNovaPeca('e', 2, new Peao(tabuleiro, Cor.BRANCO, this));
	    colocarNovaPeca('f', 2, new Peao(tabuleiro, Cor.BRANCO, this));
	    colocarNovaPeca('g', 2, new Peao(tabuleiro, Cor.BRANCO, this));
	    colocarNovaPeca('h', 2, new Peao(tabuleiro, Cor.BRANCO, this));

	    // Pretas
	    colocarNovaPeca('a', 8, new Torre(tabuleiro, Cor.PRETO));
	    colocarNovaPeca('b', 8, new Cavalo(tabuleiro, Cor.PRETO));
	    colocarNovaPeca('c', 8, new Bispo(tabuleiro, Cor.PRETO));
	    colocarNovaPeca('d', 8, new Rainha(tabuleiro, Cor.PRETO));
	    colocarNovaPeca('e', 8, new Rei(tabuleiro, Cor.PRETO, this));
	    colocarNovaPeca('f', 8, new Bispo(tabuleiro, Cor.PRETO));
	    colocarNovaPeca('g', 8, new Cavalo(tabuleiro, Cor.PRETO));
	    colocarNovaPeca('h', 8, new Torre(tabuleiro, Cor.PRETO));

	    colocarNovaPeca('a', 7, new Peao(tabuleiro, Cor.PRETO, this));
	    colocarNovaPeca('b', 7, new Peao(tabuleiro, Cor.PRETO, this));
	    colocarNovaPeca('c', 7, new Peao(tabuleiro, Cor.PRETO, this));
	    colocarNovaPeca('d', 7, new Peao(tabuleiro, Cor.PRETO, this));
	    colocarNovaPeca('e', 7, new Peao(tabuleiro, Cor.PRETO, this));
	    colocarNovaPeca('f', 7, new Peao(tabuleiro, Cor.PRETO, this));
	    colocarNovaPeca('g', 7, new Peao(tabuleiro, Cor.PRETO, this));
	    colocarNovaPeca('h', 7, new Peao(tabuleiro, Cor.PRETO, this));
	}
}