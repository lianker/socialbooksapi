package net.arkalis.socialbooks.services.exceptions;

public class AutorNaoEncontradoException extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3163905127200745685L;

	public AutorNaoEncontradoException(String mensagem) {
		super(mensagem);
	}
	
	public AutorNaoEncontradoException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}
}
