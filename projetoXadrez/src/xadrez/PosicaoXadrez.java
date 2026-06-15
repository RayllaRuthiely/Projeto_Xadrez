package xadrez;

/**
 * Classe responsável por representar uma posição
 * no padrão do jogo de xadrez (coluna e linha),
 * realizando a conversão para a posição utilizada
 * internamente pelo tabuleiro.
 */

import tabuleiro.Posicao;

public class PosicaoXadrez {

    private char coluna;
    private int linha;

    
    /**
     * Constrói uma posição de xadrez.
     *
     * @param coluna Coluna da posição (de 'a' até 'h').
     * @param linha Linha da posição (de 1 até 8).
     */
    public PosicaoXadrez(char coluna, int linha) {
        if (coluna < 'a' || coluna > 'h' || linha < 1 || linha > 8) {
            throw new ExcecaoXadrez( "Erro ao instanciar PosicaoXadrez. Valores válidos são de a1 até h8.");
        }
        this.coluna = coluna;
        this.linha = linha;
    }

    /**
     * Retorna a coluna da posição.
     */
    public char getColuna() {
        return coluna;
    }

    /**
     * Retorna a linha da posição.
     */
    public int getLinha() {
        return linha;
    }

    /**
     * Converte uma posição de xadrez para a posição
     * utilizada internamente pelo tabuleiro.
     *
     * @return Objeto Posicao correspondente.
     */
    public Posicao toPosicao() {
        return new Posicao(8 - linha, coluna - 'a');
    }

    /**
     * Converte uma posição do tabuleiro para o formato
     * tradicional do xadrez.
     *
     * @param posicao Posição interna do tabuleiro.
     * @return Posição no formato do xadrez.
     */
    public static PosicaoXadrez daPosicao(Posicao posicao) {
        return new PosicaoXadrez(
                (char) ('a' + posicao.getColuna()),
                8 - posicao.getLinha()
        );
    }

    /**
     * Retorna a posição no formato textual
     * (exemplo: a1, e4, h8).
     */
    @Override
    public String toString() {
        return "" + coluna + linha;
    }
}