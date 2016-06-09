package net.arkalis.socialbooks.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.arkalis.socialbooks.domain.Autor;
import net.arkalis.socialbooks.repositories.AutoresRepository;
import net.arkalis.socialbooks.services.exceptions.AutorExistenteException;
import net.arkalis.socialbooks.services.exceptions.AutorNaoEncontradoException;

@Service
public class AutoresService {

	@Autowired
	private AutoresRepository autoresRepository;

	public List<Autor> listar() {
		return autoresRepository.findAll();
	}

	public Autor salvar(Autor autor) {

		if (autor.getId() != null) {
			Autor autorDuplicado = autoresRepository.findOne(autor.getId());

			if (autorDuplicado != null) {
				throw new AutorExistenteException("O Autor já Existe");
			}
		}

		return autoresRepository.save(autor);
	}
	
	public Autor buscar(Long id) {
		Autor autor = autoresRepository.findOne(id);
		
		if(autor == null) {
			throw new AutorNaoEncontradoException("O autor não pôde ser encontrado.");
		}
		return autor;
	}
}