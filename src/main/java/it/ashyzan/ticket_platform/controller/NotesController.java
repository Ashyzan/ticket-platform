package it.ashyzan.ticket_platform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import it.ashyzan.ticket_platform.model.Notes;
import it.ashyzan.ticket_platform.model.Ticket;
import it.ashyzan.ticket_platform.repository.NoteRepository;
import it.ashyzan.ticket_platform.repository.TicketRepository;
import it.ashyzan.ticket_platform.repository.UserRepository;
import jakarta.validation.Valid;
@Controller
@RequestMapping("/notes")
public class NotesController {
    @Autowired
    private UserRepository userrepository;
    
    @Autowired
    private TicketRepository ticketrepository;
    
    @Autowired
    private NoteRepository noterepository;

    // DETTAGLIO TICKET E NOTE AGGIUNGI NOTA
    @GetMapping("/add/{id}")
	public String aggiungiNota(@PathVariable("id") Integer id, Model model) {
		Ticket ticket = ticketrepository.getReferenceById(id);
		Notes nuovaNota = new Notes();

		// associo il ticket alla/e nuova nota
		nuovaNota.setTicketNota(ticket);
		nuovaNota.setUser(ticket.getUser());

		model.addAttribute("ticket", ticket);
		model.addAttribute("note", noterepository.findAll());
		
		
		model.addAttribute("nuovaNota", nuovaNota);
		
		
//		if (bindingresult.hasErrors()) {
//			   bindingresult.addError(new ObjectError
//		("Errore di inserimento", "Il campo è obbligatorio"));
//				return "/detail/{id}";
//			}
		
			return "/notes/addnotes";
	}
    
    @PostMapping("/quickadd")
    public String salvaQuickNota(@Valid @ModelAttribute("nuovaNota") Notes note, 
	    BindingResult bindingresult, Model model) {

//	User user = userrepository.findById(id).get();


//	if (bindingresult.hasErrors()) {
//	   bindingresult.addError(new ObjectError("Errore di inserimento", "Il campo è obbligatorio"));
//		return "/detail/{id}";
//	}

	noterepository.save(note);
	return "redirect:/ticket/detail/" + note.getTicketNota().getId();

//	return "redirect:/ticket/dashboard";
    }
}
