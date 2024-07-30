package it.ashyzan.ticket_platform.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.ashyzan.ticket_platform.model.Categoria;

public interface CategoriaRepo extends JpaRepository<Categoria, Integer>{
    
}
