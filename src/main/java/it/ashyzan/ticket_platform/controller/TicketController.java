package it.ashyzan.ticket_platform.controller;

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
import it.ashyzan.ticket_platform.repository.TicketRepository;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/ticket")
public class TicketController {
    
    @Autowired
    private TicketRepository ticketrepository;
    
    @GetMapping("dashboard")
	public String index(Model model) {
		List<Ticket> ticket = ticketrepository.findAll();
		model.addAttribute("ticket", ticket);

		return "/ticket/index";
	}
    
 // FILTRO DEI TICKET PER TITOLO //
//    @GetMapping("/dashboard/filtra")
//    public String filtraTicket(@ModelAttribute ("ticket")Ticket ticket, 
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
//    }
   
// metodo di ricerca tramite keyword
    public List<Ticket> getByKeyword(String keyword) {
        return ticketrepository.findByKeyword(keyword);
    }

    @GetMapping({"/search"})
    public String home(Ticket ticket, Model model, String keyword) {
        if (keyword != null) {
            List<Ticket> ticketFiltrati = ticketrepository.findByKeyword(keyword);
            model.addAttribute("ticketTrovati", ticketFiltrati);
       
            return "/ticket/ticketfiltrati";
        } 
            
        return "/ticket/error";
    }
    
    // CREA TICKET //
    @GetMapping("/create")
	public String create(Model model) {
		model.addAttribute("ticket", new Ticket());
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
    
 // modifica ticket esistenti

 	@GetMapping("/edit/{id}")
 	public String modificaPizza(@PathVariable("id") Integer id, Model model) {
 		model.addAttribute("ticket", ticketrepository.getReferenceById(id));
 		
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
