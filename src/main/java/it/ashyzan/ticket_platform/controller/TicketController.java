package it.ashyzan.ticket_platform.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
    
 // filtra tickets
//    @PostMapping("dashboard/filtra")
//    public List<Ticket> filtraTicket(Model model, String titoloDaFiltrare ) {
//	model.addAttribute("titoloDaFiltrare", titoloDaFiltrare);
//
//	List<Ticket> ticketListaNew = new ArrayList<Ticket>();
//	List<Ticket> ticketPresenti = ticketrepository.findAll();
//	
//	for( int i=0; i< ticketPresenti.size(); i++) {
//		if(ticketrepository.findBytitoloTicketIgnoreCaseLike(titoloDaFiltrare) != null) {
//		   
//		    System.out.println(titoloDaFiltrare);
//		}
//	}
//	
//	return ticketListaNew;
//    }
//    
    @GetMapping("/create")
	public String create(Model model) {
		model.addAttribute("ticket", new Ticket());
		return "/ticket/create";
	}
    
    @PostMapping("/create")
	public String saveticket(@Valid @ModelAttribute("ticket") Ticket ticket, BindingResult bindingresult, Model model)

	{

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

// 		if () {
//
// 			bindingresult.addError(new ObjectError("Errore di prezzo", "Il prezzo della pizza è obbligatorio"));
//
// 		}

// 		if (bindingresult.hasErrors()) {
// 			return "/pizzeria/edit";
// 		}
 		ticketrepository.save(ticket);

 		return "redirect:/ticket/dashboard";
 	}

}
