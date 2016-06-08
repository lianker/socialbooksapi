package net.arkalis.socialbooks.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import net.arkalis.socialbooks.domain.Livro;
import net.arkalis.socialbooks.repositories.LivroRepository;
import net.arkalis.socialbooks.services.exceptions.LivroNaoEncontradoException;

@Service
public class LivrosService {
	@Autowired
	private LivroRepository livroRepository;

	public List<Livro> listar() {
		return livroRepository.findAll();
	}

	public Livro buscar(Long id) {
		Livro livro = livroRepository.findOne(id);

		if (livro == null) {
			throw new LivroNaoEncontradoException("O livro não pôde ser encontrado!");
		}

		return livro;
	}

	public Livro salvar(Livro livro) {
		livro.setId(null);
		return livroRepository.save(livro);
	}

	public void excluir(Long id) {

		try {
			livroRepository.delete(id);
		} catch (EmptyResultDataAccessException e) {
			throw new LivroNaoEncontradoException("O livro não pôde ser encontrado!");
		}

	}

	public void atualizar(Livro livro) {
		verificaExistencia(livro);

		livroRepository.save(livro);
	}

	private void verificaExistencia(Livro livro) {
		buscar(livro.getId());
	}
}
