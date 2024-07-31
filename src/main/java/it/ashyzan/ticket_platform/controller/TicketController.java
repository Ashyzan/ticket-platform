package it.ashyzan.ticket_platform.controller;

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
import it.ashyzan.ticket_platform.repository.CategoriaRepo;
import it.ashyzan.ticket_platform.repository.StatoRepo;
import it.ashyzan.ticket_platform.repository.TicketRepository;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/ticket")
public class TicketController {
    
    @Autowired
    private TicketRepository ticketrepository;
    
    @Autowired
    private CategoriaRepo categoriarepository;
    
    @Autowired
    private StatoRepo statorepository;
    
    @GetMapping("dashboard")
	public String index(Model model) {
		
		model.addAttribute("ticket", ticketrepository.findAll());

		return "/ticket/index";
	}
    
///////////////////////////// CREA TICKET //
    @GetMapping("/create")
	public String create(Model model) {
		model.addAttribute("ticket", new Ticket());
		model.addAttribute("DB_categorie", categoriarepository.findAll());
		model.addAttribute("DB_stato", statorepository.findAll());
		return "/ticket/create";
	}
    
    @PostMapping("/create")
	public String saveticket(@Valid @ModelAttribute("ticket") Ticket ticket, BindingResult bindingresult, Model model) {

	if (bindingresult.hasErrors()) {
		   bindingresult.addError(new ObjectError("Errore di inserimento", "Il campo è obbligatorio"));
			return "/create";
		}
	
		ticketrepository.save(ticket);
		return "redirect:/ticket/dashboard";
	}
    
    @GetMapping("/detail/{id}")
	public String dettaglioTicket(@PathVariable("id") Integer id, Model model) {
		Ticket ticket = ticketrepository.getReferenceById(id);
		model.addAttribute("ticket", ticket);
		

		return "/ticket/details";
	}
    
///////////////////////////// MODIFICA TICKET ESISTENTI

 	@GetMapping("/edit/{id}")
 	public String modificaPizza(@PathVariable("id") Integer id, Model model) {
 		model.addAttribute("ticket", ticketrepository.findById(id).get());
 		model.addAttribute("DB_categorie", categoriarepository.findAll());
		model.addAttribute("DB_stato", statorepository.findAll());
 		model.addAttribute("editMode", true);
 		return "/ticket/edit";
 	}

 	@PostMapping("/edit/{id}")
 	public String update(@Valid @ModelAttribute("ticket") Ticket ticket, BindingResult bindingresult, Model model) {

 		if (bindingresult.hasErrors()) {
 		   bindingresult.addError(new ObjectError("Errore di inserimento", "Il campo è obbligatorio"));
 			return "/edit/{id}";
 		}
 		ticketrepository.save(ticket);

 		return "redirect:/ticket/dashboard";
 	}

}
