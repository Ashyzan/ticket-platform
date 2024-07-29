package it.ashyzan.ticket_platform.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
    
    @GetMapping("/create")
	public String create(Model model) {
		model.addAttribute("ticket", new Ticket());
//		model.addAttribute("checkIngrediente", new ArrayList<IngredientiModel>());
//		model.addAttribute("ingredientiDB", ingredientirepository.findAll() );
		return "/ticket/create";
	}
    
    @PostMapping("/create")
	public String saveticket(@Valid @ModelAttribute("ticket") Ticket ticket, BindingResult bindingresult, Model model)

	{

		ticketrepository.save(ticket);

		return "redirect:/ticket/dashboard";
	}

}
