package it.ashyzan.ticket_platform.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.ashyzan.ticket_platform.model.Ticket;
import it.ashyzan.ticket_platform.model.User;
import it.ashyzan.ticket_platform.repository.NoteRepository;
import it.ashyzan.ticket_platform.repository.TicketRepository;
import it.ashyzan.ticket_platform.repository.UserRepository;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/user")
public class UserController {
    
    @Autowired
    private TicketRepository ticketrepository;
    
    @Autowired
    private NoteRepository noterepository;
    
    @Autowired
    private UserRepository userrepository;

    //////////////////  USER LOGGATO PAGE METODO ID //////////////
//    @GetMapping("dashboard/{id}")
//	public String indexPage(@PathVariable("id") Integer id, Model model) {
//	
//	User user = userrepository.findById(id).get();
//	model.addAttribute("utenteloggato", System.getProperty("user.name"));
//        model.addAttribute("nome",  user.getNome());
//        model.addAttribute("email",  user.getEmail());
//        model.addAttribute("ruolo",  user.getRole());
//        model.addAttribute("flagUser",  user.getFlagDisponibile());
//	model.addAttribute("listaTicketuser", user.getListaTicket());
//
//	 	return "/user/userpage";
//	}
    
    //////////////////  USER LOGGATO PAGE OK //////////////
    @GetMapping("dashboard/userlogged/")
	public String indexPageUserLogged(@RequestParam (name = "username", required = false) 
	String username, Model model, User user, Authentication authentication) {
	
	Optional<User> UserDB = userrepository.findByUsername(authentication.getName());
	
	User userloggato = UserDB.get();
 
	model.addAttribute("nome",  userloggato.getNome());
	model.addAttribute("email",  userloggato.getEmail());
	model.addAttribute("ruolo",  userloggato.getRole().getName());
	model.addAttribute("flagUser",  userloggato.getFlagDisponibile());
	model.addAttribute("listaTicketuser", userloggato.getListaTicket());

	 	return "/user/userloggedpage";
	}
    
///////////////////////////// MODIFICA DATI USER 2 OK /////////////////////////////
    @GetMapping("/edit/userlogged")
    public String editLoggedUser(@RequestParam (name = "username", required = false) 
	String username, Model model, User user, Authentication authentication) {
	
	
		Optional<User> UserDB = userrepository.findByUsername(authentication.getName());
		
		User userloggato = UserDB.get();
		model.addAttribute("userutente", userloggato);
	 
		// recupero i ticket associati all'utente in una variabile
	 	List<Ticket> ticketUser = userloggato.getListaTicket();
	 	// creo una lista ticket vuota
	 	List<Ticket> ticketDaFare = new ArrayList<>();
	 	// ciclo i ticket dell'utente, se lo stato è diverso da 3 (da fare) aggiungi alla lista vuota
	 	for (Ticket item: ticketUser) {
	             
	 	   if( item.getStato().getId() != 3) {
	 	       
	 	       ticketDaFare.add(item);
	 	   }
	 	   
	         }
	 	// se la nuova lista is empty stato "non attivo" is visible
	 	if(ticketDaFare.isEmpty()) {
	 	    
	 	    model.addAttribute("flag", true);    
	 	}
	 	
	 	else {
	 	    model.addAttribute("flag", false); 
	 	    
	 	}
	    return "/user/edituserlogged";
	    }
	
    
    
    
///////////////////////////// MODIFICA DATI USER 1/////////////////////////////

//    @GetMapping("/edit/{id}")
//    public String modificaUser(@PathVariable("id") Integer id, Model model, User user) {
//	User userutente = userrepository.findById(id).get();
//    model.addAttribute("userutente", userutente);
//    model.addAttribute("editMode", true);
// // recupero i ticket associati all'utente in una variabile
// 	List<Ticket> ticketUser = userutente.getListaTicket();
// 	// creo una lista ticket vuota
// 	List<Ticket> ticketDaFare = new ArrayList<>();
// 	// ciclo i ticket dell'utente, se lo stato è diverso da 3 (da fare) aggiungi alla lista vuota
// 	for (Ticket item: ticketUser) {
//             
// 	   if( item.getStato().getId() != 3) {
// 	       
// 	       ticketDaFare.add(item);
// 	   }
// 	   
//         }
// 	// se la nuova lista is empty stato "non attivo" is visible
// 	if(ticketDaFare.isEmpty()) {
// 	    
// 	    model.addAttribute("flag", true);    
// 	}
// 	
// 	else {
// 	    model.addAttribute("flag", false); 
// 	    
// 	}
//    return "/user/edituser";
//    }
    
//    @PostMapping("/edit/{id}")
//    public String update(@PathVariable("id") Integer id, @Valid @ModelAttribute("user") User user, 
//	    BindingResult bindingresult, Model model) {
//
//    if (bindingresult.hasErrors()) {
//	
//	User userutente = userrepository.findById(id).get();
//	    model.addAttribute("userutente", userutente);
//	  
//	 // recupero i ticket associati all'utente in una variabile
//	 	List<Ticket> ticketUser = userutente.getListaTicket();
//	 	// creo una lista ticket vuota
//	 	List<Ticket> ticketDaFare = new ArrayList<>();
//	 	// ciclo i ticket dell'utente, se lo stato è diverso da 3 (da fare) 
//	 	//aggiungi alla lista vuota
//	 	for (Ticket item: ticketUser) {
//	             
//	 	   if( item.getStato().getId() != 3) {
//	 	       
//	 	       ticketDaFare.add(item);
//	 	   }
//	 	   
//	         }
//	 	// se la nuova lista is empty stato "non attivo" is visible
//	 	if(ticketDaFare.isEmpty()) {
//	 	    
//	 	    model.addAttribute("flag", true);    
//	 	}
//	 	
//	 	else {
//	 	    model.addAttribute("flag", false); 
//	 	    
//	 	}
//   
//    	return "/user/edituser";
//    }
//    userrepository.save(user);
//    
//    return "redirect:/ticket/dashboard";
//    }
    
    @PostMapping("/edit/userlogged/{id}")
    public String update( @Valid @ModelAttribute("user") User user, @PathVariable("id") Integer id,
	    BindingResult bindingresult, Model model, Authentication authentication) {

	Optional<User> UserDB = userrepository.findByUsername(authentication.getName());
	Integer iduserloggato = UserDB.get().getId();
	
	User userdasalvare = userrepository.findById(iduserloggato).get();
	
	model.addAttribute("userutente", userdasalvare);
   
	if (bindingresult.hasErrors()) {
  
	 // recupero i ticket associati all'utente in una variabile
	 	List<Ticket> ticketUser = userdasalvare.getListaTicket();
	 	// creo una lista ticket vuota
	 	List<Ticket> ticketDaFare = new ArrayList<>();
	 	// ciclo i ticket dell'utente, se lo stato è diverso da 3 (da fare) 
	 	//aggiungi alla lista vuota
	 	for (Ticket item: ticketUser) {
	             
	 	   if( item.getStato().getId() != 3) {
	 	       
	 	       ticketDaFare.add(item);
	 	   }
	 	   
	         }
	 	// se la nuova lista is empty stato "non attivo" is visible
	 	if(ticketDaFare.isEmpty()) {
	 	    
	 	    model.addAttribute("flag", true);    
	 	}
	 	
	 	else {
	 	    model.addAttribute("flag", false); 
	 	    
	 	}
   
    	return "/user/edituser";
    }
	
    userrepository.save(user);
    
    return "redirect:/user/dashboard/userlogged/";
    }

}
