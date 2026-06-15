package tabuleiro;

/**
 * Exceção personalizada para erros relacionados ao tabuleiro.
 * É lançada quando ocorre uma operação inválida, como acessar
 * uma posição inexistente ou tentar colocar uma peça em uma
 * posição já ocupada.
 */

public class ExcecaoTabuleiro extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	/**
	 * Cria uma exceção do tabuleiro com a mensagem informada.
	 *
	 * @param mensagem descrição do erro ocorrido
	 */

	public ExcecaoTabuleiro(String mensagem) {
		super(mensagem);
	}
	
}
