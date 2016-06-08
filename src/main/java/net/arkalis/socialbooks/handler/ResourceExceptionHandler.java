package net.arkalis.socialbooks.handler;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import net.arkalis.socialbooks.domain.DetalhesErro;
import net.arkalis.socialbooks.services.exceptions.LivroNaoEncontradoException;

@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(LivroNaoEncontradoException.class)
	public ResponseEntity<DetalhesErro> handleLivroNaoEncontradoException(LivroNaoEncontradoException e,
			HttpServletRequest request) {

		DetalhesErro erro = new DetalhesErro("livro não pôde ser encontrado!", 
				404l, 
				System.currentTimeMillis(),
				"http://erros.socialbooks.com/404");

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
	}
}
