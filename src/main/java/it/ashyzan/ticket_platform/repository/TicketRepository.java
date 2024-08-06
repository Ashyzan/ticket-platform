package it.ashyzan.ticket_platform.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import it.ashyzan.ticket_platform.model.Ticket;
import it.ashyzan.ticket_platform.model.User;

public interface TicketRepository extends JpaRepository <Ticket, Integer>{
//    // corrispondenza esatta
    public List<Ticket> findByTitoloTicketIgnoreCase(String titoloTicket);
//    
//    //corrispondenza simile
    public List<Ticket> findByTitoloTicketIgnoreCaseLike(String titoloTicket);
//    // corrispondenza contiene
    public  List<Ticket> findByTitoloTicketIgnoreCaseContaining(String titoloTicket);
    
    public List<Ticket> findByTitoloTicketContainingIgnoreCase(String titoloTicket);
    
    public List<Ticket> findByUser(User user);

  //Custom query
    @Query(value = "SELECT * FROM tickets t WHERE t.titolo_ticket LIKE %:keyword%",
            nativeQuery = true)
    public List<Ticket> findByKeyword(@Param("keyword") String keyword);
    
    
//    SELECT *
//    FROM tickets t 
//    WHERE t.titolo_ticket LIKE '%keyword%';


}

