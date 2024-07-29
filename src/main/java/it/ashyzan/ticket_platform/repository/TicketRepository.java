package it.ashyzan.ticket_platform.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.ashyzan.ticket_platform.model.Ticket;

public interface TicketRepository extends JpaRepository <Ticket, Integer>{
    
    public Ticket findBytitoloTicketIgnoreCase(String titoloTicket);

}

