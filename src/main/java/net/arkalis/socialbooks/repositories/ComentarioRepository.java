package net.arkalis.socialbooks.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import net.arkalis.socialbooks.domain.Comentario;

public interface ComentarioRepository extends JpaRepository<Comentario, Long>{

}
