package it.ashyzan.ticket_platform.service;

import java.util.List;

import it.ashyzan.ticket_platform.model.Ticket;



public interface TicketService {

//    Restituisco la lista dei ticket
    public List<Ticket> findAllTickets();
    
//    filtro i ticket per categoria
    public List<Ticket> filterByCategory(Integer id);
    
//    filtro i ticket per stato
    
    public List<Ticket> filterByStato(Integer id);
}
