package it.ashyzan.ticket_platform.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.ashyzan.ticket_platform.model.Notes;

public interface NoteRepository extends JpaRepository<Notes, Integer>{

}
