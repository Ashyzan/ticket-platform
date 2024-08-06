package it.ashyzan.ticket_platform.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import it.ashyzan.ticket_platform.model.Ticket;
import it.ashyzan.ticket_platform.model.User;
import it.ashyzan.ticket_platform.repository.TicketRepository;
import it.ashyzan.ticket_platform.repository.UserRepository;

@Controller
@RequestMapping("")
public class TicketFilter {
    
    @Autowired
    private TicketRepository ticketrepository;
    
    @Autowired
    private UserRepository userrepository;
    
    // RICERCA TRAMITE TITOLO TICKET 
 
//metodo di ricerca tramite keyword
  public List<Ticket> getByKeyword(String keyword) {
      return ticketrepository.findByKeyword(keyword);
  }
  

  @GetMapping("/search")
  public String Search(Model model, String keyword, Authentication authentication) {
          List<Ticket> ticketFiltrati = ticketrepository.findByKeyword(keyword);
          
          List<Ticket> ticketNONFiltrati = ticketrepository.findAll();
          
          Optional<User> UserDB = userrepository.findByUsername(authentication.getName());
		
		User userloggato = UserDB.get();
	 
		// recupero i ticket associati all'utente in una variabile
	 	List<Ticket> ticketUser = userloggato.getListaTicket();
	 	// creo una lista ticket vuota
	 	List<Ticket> ticketUtenteLoggato = new ArrayList<>();
	 	
	 	if (keyword != null) {
	 	    
	 	    if (ticketFiltrati.size() == 0) {
	 		
	 		model.addAttribute("ticketTrovati", ticketFiltrati);
 		    
	 	          return "/ticket/ticketfiltrati";
	 		
	 	    }
	          	
	          // ciclo i ticket dell'utente
	          for (Ticket item: ticketFiltrati) {
	              
	              
	              // se l'utente che filtra è OPERATORE
	              if( userloggato.getRole().getId() != 1) {
	        	  
	        	  // scegli i ticket in base all'id dell'operatore
	        	  
	        	  if(item.getUser().getId() == userloggato.getId()) {
	        	      
	        	      ticketUtenteLoggato.add(item);
	        	      model.addAttribute("ticketTrovati", ticketUtenteLoggato);
	        	  }
	        	  
	              }
	              // se l'utente che filtra è ADMIN:
	              else  {
	        	  model.addAttribute("ticketTrovati", ticketFiltrati);
	              }
	              
	          }
	     
	          return "/ticket/ticketfiltrati";
	      } 
      
	 	model.addAttribute("ticketTrovati", ticketFiltrati);
	return "/ticket/ticketfiltrati";

  } 
  
  
  // FUNZIONANTE
//  @GetMapping("/search")
//  public String Search(Model model, String keyword) {
//      if (keyword != null) {
//          List<Ticket> ticketFiltrati = ticketrepository.findByKeyword(keyword);
//          
//          model.addAttribute("ticketTrovati", ticketFiltrati);
//	    
//          return "/ticket/ticketfiltrati";
//      } 
//          
//    return "/ticket/ticketfiltrati";
//  }
  
  // RICERCA TRAMITE TITOLO TICKET : by lezioni
  
//  @GetMapping("/search")
//	public String research(Model model, 
//			@RequestParam(name = "titoloTicket", required = false) String titoloTicket) {
//		
//		List<Ticket> listaTicketPerTitolo = new ArrayList<>();
//		
//		if(titoloTicket == null || titoloTicket.isBlank()) {
//		    listaTicketPerTitolo = ticketrepository.findAll();
//		} else {
////		    listaTicketPerTitolo = ticketrepository.findByTitoloTicketIgnoreCaseLike(titoloTicket);
////		    listaTicketPerTitolo = ticketrepository.findBytitoloTicketIgnoreCaseContaining(titoloTicket);
////		    listaTicketPerTitolo = ticketrepository.findBytitoloTicketIgnoreCase(titoloTicket);
//		    listaTicketPerTitolo = ticketrepository.findByTitoloTicketContainingIgnoreCase(titoloTicket);
//		}
//		
//		model.addAttribute("ticketTrovati", listaTicketPerTitolo);
//		
//		return "/ticket/ticketfiltrati";
//	}
  
  
	

}
