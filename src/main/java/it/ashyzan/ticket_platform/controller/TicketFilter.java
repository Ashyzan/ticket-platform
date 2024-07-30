package it.ashyzan.ticket_platform.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import it.ashyzan.ticket_platform.model.Ticket;
import it.ashyzan.ticket_platform.repository.TicketRepository;

@Controller
@RequestMapping("")
public class TicketFilter {
    
    @Autowired
    private TicketRepository ticketrepository;
    
    // FILTRO DEI TICKET PER TITOLO //
//  @GetMapping("/dashboard/filtra")
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
  public List<Ticket> getByKeyword(String keyword) {
      return ticketrepository.findByKeyword(keyword);
  }

  @GetMapping({"/search"})
  public String home(Model model, String keyword) {
      if (keyword != null) {
          List<Ticket> ticketFiltrati = ticketrepository.findByKeyword(keyword);
          model.addAttribute("ticketTrovati", ticketFiltrati);
     
          return "/ticket/ticketfiltrati";
      } 
          
      return "/ticket/error";
  }

}
