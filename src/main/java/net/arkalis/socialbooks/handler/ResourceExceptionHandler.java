package net.arkalis.socialbooks.handler;

import javax.servlet.http.HttpServletRequest;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import net.arkalis.socialbooks.domain.DetalhesErro;
import net.arkalis.socialbooks.services.exceptions.AutorExistenteException;
import net.arkalis.socialbooks.services.exceptions.AutorNaoEncontradoException;
import net.arkalis.socialbooks.services.exceptions.LivroNaoEncontradoException;

@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(LivroNaoEncontradoException.class)
	public ResponseEntity<DetalhesErro> handleLivroNaoEncontradoException(LivroNaoEncontradoException e,
			HttpServletRequest request) {

		DetalhesErro erro = new DetalhesErro("livro não pôde ser encontrado!", 404l, System.currentTimeMillis(),
				"http://erros.socialbooks.com/404");

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
	}

	@ExceptionHandler(AutorExistenteException.class)
	public ResponseEntity<DetalhesErro> handleAutorExistenteException(AutorExistenteException e,
			HttpServletRequest request) {

		DetalhesErro erro = new DetalhesErro("Autor já Existente!", 409l, System.currentTimeMillis(),
				"http://erros.socialbooks.com/409");

		return ResponseEntity.status(HttpStatus.CONFLICT).body(erro);
	}

	@ExceptionHandler(AutorNaoEncontradoException.class)
	public ResponseEntity<DetalhesErro> handleAutorNaoEncontradoException(AutorNaoEncontradoException e,
			HttpServletRequest request) {

		DetalhesErro erro = new DetalhesErro("Autor não pôde ser encontrado!", 404l, System.currentTimeMillis(),
				"http://erros.socialbooks.com/404");

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
	}
	
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<DetalhesErro> handleDataIntegrityViolationException(DataIntegrityViolationException e,
			HttpServletRequest request) {

		DetalhesErro erro = new DetalhesErro("Requisição Inválida!", 400l, System.currentTimeMillis(),
				"http://erros.socialbooks.com/400");

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
	}
}
