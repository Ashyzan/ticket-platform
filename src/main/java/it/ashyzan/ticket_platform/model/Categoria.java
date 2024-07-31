package it.ashyzan.ticket_platform.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "categoria")
public class Categoria {
    
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, unique = true)
	private Integer id;

	@NotBlank(message = "Il nome della categoria è obbligatorio")
	@Column(name = "categoria", nullable = false)
	private String categoria;
	
//	@OneToMany(mappedBy = "categoria")
//	private List<Ticket> tickets;
	
	// GETTER SETTER

//	public List<Ticket> getTickets() {
//	    return tickets;
//	}
//
//	public void setTickets(List<Ticket> tickets) {
//	    this.tickets = tickets;
//	}

	public Integer getId() {
	    return id;
	}

	public void setId(Integer id) {
	    this.id = id;
	}

	public String getCategoria() {
	    return categoria;
	}

	public void setCategoria(String categoria) {
	    this.categoria = categoria;
	}


    
	
	
}
