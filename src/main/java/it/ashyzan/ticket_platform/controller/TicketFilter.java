package it.ashyzan.ticket_platform.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.ashyzan.ticket_platform.model.Ticket;
import it.ashyzan.ticket_platform.repository.TicketRepository;

@Controller
@RequestMapping("")
public class TicketFilter {
    
    @Autowired
    private TicketRepository ticketrepository;
    
    // FILTRO DEI TICKET PER TITOLO //
//  @GetMapping("/search")
//  public String filtraTicket(@ModelAttribute ("ticket")Ticket ticket, 
//	    BindingResult bindingresult, Model model ) {
//	
//	if (ticket.getTitoloTicket() != null) {
//	
//	    List <Ticket> ticketFiltrati = 
//		    ticketrepository.findBytitoloTicketIgnoreCaseLike(ticket.getTitoloTicket());
//	    if(ticketFiltrati != null) {
//		model.addAttribute("ticketTrovati", ticketFiltrati);
//		return "/ticket/ticketfiltrati"; 
//	}
//	
//				}
//	return "/ticket/error";
//  }
 
//metodo di ricerca tramite keyword
  public List<Ticket> getByKeyword(String titoloTicket) {
      return ticketrepository.findByKeyword(titoloTicket);
  }

//  @GetMapping("/search")
//  public String home(Model model, String keyword) {
//      if (keyword != null) {
//          List<Ticket> ticketFiltrati = ticketrepository.findByKeyword(keyword);
//          model.addAttribute("ticketTrovati", ticketFiltrati);
//     
//          return "/ticket/ticketfiltrati";
//      } 
//          
//      return "/ticket/error";
//  }
//  
  // RICERCA TRAMITE AUTORE
  
  @GetMapping("/search")
	public String research(Model model, 
			@RequestParam(name = "titoloTicket", required = false) String titoloTicket) {
		
		List<Ticket> listaTicketPerTitolo = new ArrayList<>();
		
		if(titoloTicket == null || titoloTicket.isBlank()) {
		    listaTicketPerTitolo = ticketrepository.findAll();
		} else {
		    listaTicketPerTitolo = ticketrepository.findBytitoloTicketIgnoreCaseLike(titoloTicket);
//		    listaTicketPerTitolo = ticketrepository.findBytitoloTicketIgnoreCaseContaining(titoloTicket);
//		    listaTicketPerTitolo = ticketrepository.findBytitoloTicketIgnoreCase(titoloTicket);
//		    listaTicketPerTitolo = ticketrepository.findByKeyword(titoloTicket);
		}
		
		model.addAttribute("ticketTrovati", listaTicketPerTitolo);
		
		return "/ticket/ticketfiltrati";
	}
  
  
	

}
