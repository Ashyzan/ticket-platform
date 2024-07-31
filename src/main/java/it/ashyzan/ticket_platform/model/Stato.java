package it.ashyzan.ticket_platform.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "stato")
public class Stato {

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, unique = true)
	private Integer id;

	@NotBlank(message = "Lo stato del ticket è obbligatorio")
	@Column(name = "stato", nullable = false)
	private String stato;
	
	@OneToMany(mappedBy = "stato")
	private List<Ticket> tickets;
	
	// GETTER SETTER

	public Integer getId() {
	    return id;
	}

	public void setId(Integer id) {
	    this.id = id;
	}

	public String getStato() {
	    return stato;
	}

	public void setStato(String stato) {
	    this.stato = stato;
	}

	public List<Ticket> getTickets() {
	    return tickets;
	}

	public void setTickets(List<Ticket> tickets) {
	    this.tickets = tickets;
	}
	
	
	
	
}
