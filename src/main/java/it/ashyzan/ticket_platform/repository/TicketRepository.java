package it.ashyzan.ticket_platform.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import it.ashyzan.ticket_platform.model.Ticket;

public interface TicketRepository extends JpaRepository <Ticket, Integer>{
    // corrispondenza esatta
    public Ticket findBytitoloTicketIgnoreCase(String titoloTicket);
    //corrispondenza simile
    public List<Ticket> findBytitoloTicketIgnoreCaseLike(String titoloTicket);
    // corrispondenza contiene
    public  List<Ticket> findBytitoloTicketIgnoreCaseContaining(String titoloTicket);

}

