package net.arkalis.socialbooks.resources;


import java.net.URI;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import net.arkalis.socialbooks.domain.Comentario;
import net.arkalis.socialbooks.domain.Livro;
import net.arkalis.socialbooks.services.LivrosService;

@RestController
@RequestMapping(value = "/livros")
public class LivrosResource {

	@Autowired
	private LivrosService livroService;

	@RequestMapping(method = RequestMethod.GET, produces={MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<List<Livro>> listar() {
		// maneira 1 de retornar OK com um Body
		return ResponseEntity.status(HttpStatus.OK).body(livroService.listar());
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> buscar(@PathVariable("id") Long id) {
		
		CacheControl cache = CacheControl.maxAge(20, TimeUnit.SECONDS);
		
		Livro livro = livroService.buscar(id);
		return ResponseEntity.status(HttpStatus.OK).cacheControl(cache).body(livro);				
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> salvar(@Valid @RequestBody Livro livro) {
		livro = livroService.salvar(livro);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(livro.getId()).toUri();

		return ResponseEntity.created(uri).build();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> excluir(@PathVariable("id") Long id) {
		livroService.excluir(id);
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> atualizar(@RequestBody Livro livro, @PathVariable("id") Long id) {
		livro.setId(id);
		livroService.atualizar(livro);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/{id}/comentarios", method=RequestMethod.POST)
	public ResponseEntity<Void> addComentario(@PathVariable("id") Long livroId, @RequestBody Comentario comentario) {
		
		//pegando usuario da requisição
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();		
		comentario.setUsuario(auth.getName());
		
		livroService.salvaComentario(livroId, comentario);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value="/{id}/comentarios", method=RequestMethod.GET)
	public ResponseEntity<List<Comentario>> listaComentarios(@PathVariable("id") Long id){
		List<Comentario> comentarios = livroService.listaComentario(id);
		
		return ResponseEntity.ok(comentarios);
	}
}





































