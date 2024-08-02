package it.ashyzan.ticket_platform.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
    
    @GetMapping("dashboard/{id}")
	public String indexPage(@PathVariable("id") Integer id, Model model) {
	
	User user = userrepository.findById(id).get();
    
        model.addAttribute("nome",  user.getNome());
        model.addAttribute("email",  user.getEmail());
        model.addAttribute("ruolo",  user.getRole());
        model.addAttribute("flagUser",  user.getFlagDisponibile());
	model.addAttribute("listaTicketuser", user.getListaTicket());

		return "/user/userpage";
	}
    
    @PostMapping("/{id}/saveflag")
	public String saveflag(@ModelAttribute("user") @PathVariable("id") Integer id, 
	User user, Model model) {
	User user1 = userrepository.findById(id).get();
	model.addAttribute("user",  user1);
		userrepository.save(user1);
		return "redirect:/ticket/dashboard";
	}
    
///////////////////////////// MODIFICA DATI USER /////////////////////////////

    @GetMapping("/edit/{id}")
    public String modificaUser(@PathVariable("id") Integer id, Model model, User user) {
	User userutente = userrepository.findById(id).get();
    model.addAttribute("userutente", userutente);
    model.addAttribute("editMode", true);
 // recupero i ticket associati all'utente in una variabile
 	List<Ticket> ticketUser = userutente.getListaTicket();
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
    return "/user/edituser";
    }
    
    @PostMapping("/edit/{id}")
    public String update(@Valid @ModelAttribute("user") User user, BindingResult bindingresult, Model model) {
    
    if (bindingresult.hasErrors()) {
    bindingresult.addError(new ObjectError("Errore di inserimento", "Il campo è obbligatorio"));
    	return "/edit/{id}";
    }
    userrepository.save(user);
    
    return "redirect:/ticket/dashboard";
    }

}
