package net.arkalis.socialbooks.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import net.arkalis.socialbooks.domain.Livro;

public interface LivroRepository extends JpaRepository<Livro, Long>{

}
