package net.arkalis.socialbooks.services.exceptions;

public class LivroNaoEncontradoException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1137946724027243771L;

	public LivroNaoEncontradoException(String mensagem) {
		super(mensagem);
	}

	public LivroNaoEncontradoException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}
}
