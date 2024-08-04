package it.ashyzan.ticket_platform.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.ashyzan.ticket_platform.model.Ticket;
import it.ashyzan.ticket_platform.service.TicketService;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class TicketRestController {
    
    @Autowired
    private TicketService ticketservice;
    
    @GetMapping("/alltickets")
    List<Ticket> findAllTickets(){
	
	List<Ticket> lista = ticketservice.findAllTickets();
	
	return lista;
	
    }
    
    @GetMapping("/bycategory/{id}")
    List<Ticket> filterByCategory(@PathVariable("id") Integer Categoria){
	
	List<Ticket> lista = ticketservice.filterByCategory(Categoria);
	
	return lista;
	
    }
    
    @GetMapping("/bystato/{id}")
    List<Ticket> filterByStato(@PathVariable("id") Integer Stato){
	
	List<Ticket> lista = ticketservice.filterByStato(Stato);
	
	return lista;
	
    }

}
