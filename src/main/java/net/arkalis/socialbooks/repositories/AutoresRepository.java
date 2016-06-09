package net.arkalis.socialbooks.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import net.arkalis.socialbooks.domain.Autor;

public interface AutoresRepository extends JpaRepository<Autor, Long> {

}