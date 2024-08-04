package it.ashyzan.ticket_platform.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.ashyzan.ticket_platform.model.Ticket;
import it.ashyzan.ticket_platform.repository.TicketRepository;
@Service
public class TicketServiceImplementation implements TicketService {

    @Autowired
    private TicketRepository ticketrepository;
    
    @Override
    public List<Ticket> findAllTickets() {
	
	List<Ticket> lista = new ArrayList<>();
	
	lista.addAll(ticketrepository.findAll());
	
	return lista;
	
    }

    @Override
    public List<Ticket> filterByCategory(Integer CategoryId) {
	
	//Optional<Categoria> categoria = ticketrepository.findById(CategoryId);
	List<Ticket> listaR = ticketrepository.findAll();
	List<Ticket> listaN = new ArrayList<>();
	
	for (Ticket ticket: listaR) {
            
	    if(ticket.getCategoria().getId() == CategoryId) {
		
		listaN.add(ticket);
	    }
        }
	
	return listaN;
    }
    
    
    

    @Override
    public List<Ticket> filterByStato(Integer StatoId) {
	
  	//Optional<Categoria> categoria = ticketrepository.findById(CategoryId);
  	List<Ticket> listaR = ticketrepository.findAll();
  	List<Ticket> listaN = new ArrayList<>();
  	
  	for (Ticket ticket: listaR) {
              
  	    if(ticket.getStato().getId() == StatoId) {
  		
  		listaN.add(ticket);
  	    }
          }
  	
  	return listaN;
      }
}
