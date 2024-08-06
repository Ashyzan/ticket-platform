package it.ashyzan.ticket_platform.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
  
  
  
  public List<Ticket> findByUser(User user){
      
      return ticketrepository.findByUser(user);
  }

//  @GetMapping("/search")
//  public String Search(Model model, String keyword, Authentication authentication) {
////      if (keyword != null) {
//          List<Ticket> ticketFiltrati = ticketrepository.findByKeyword(keyword);
//          
//          Optional<User> UserDB = userrepository.findByUsername(authentication.getName());
//		
//		User userloggato = UserDB.get();
//	 
//		// recupero i ticket associati all'utente in una variabile
//	 	List<Ticket> ticketUser = userloggato.getListaTicket();
//	 	// creo una lista ticket vuota
//	 	List<Ticket> ticketUtenteLoggato = new ArrayList<>();
//	 	// ciclo i ticket dell'utente, se lo stato è diverso da 3 (da fare) aggiungi alla lista vuota
//	 	for (Ticket item: ticketFiltrati) {
//	 	    
//	             
//	 	    // se l'utente che filtra è OPERATORE
//	 	   if( userloggato.getRole().getId() == 2) {
//	 	      
//	 	      ticketUtenteLoggato.add(item);
//	 	     model.addAttribute("ticketTrovati", ticketUtenteLoggato);
//	 	      
//	 	   }
//	 	   // se l'utente che filtra è ADMIN:
//	 	   else if(userloggato.getRole().getId() == 1) {
//	 	      model.addAttribute("ticketTrovati", ticketFiltrati);
//	 	   }
//	 	   
//	         }
//         
//		
//	  
//          return "/ticket/ticketfiltrati";
////      }
//          // se la search è null
//      
//      //return "/ticket/ticketfiltrati";
//  } 
  
  
  // FUNZIONANTE
  @GetMapping("/search")
  public String Search(Model model, String keyword) {
      if (keyword != null) {
          List<Ticket> ticketFiltrati = ticketrepository.findByKeyword(keyword);
          
          model.addAttribute("ticketTrovati", ticketFiltrati);
         // model.addAttribute("username", authentication.getName());
         
          
		
     
          return "/ticket/ticketfiltrati";
      } 
          
    return "/ticket/ticketfiltrati";
  }
  
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
