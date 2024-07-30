package it.ashyzan.ticket_platform.model;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "tickets")
public class Ticket {
    
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, unique = true)
	private Integer id;

	// not blank va solo sulle stringhe, permette di cancellare l'input
	// se l'utente inserisce spazi nel field e restituire null.
	@NotBlank(message = "Il titolo del ticket è obbligatorio")
	@Column(name = "titoloTicket", nullable = false)
	private String titoloTicket;
	
	@NotBlank(message = "La descrizione del ticket è obbligatoria")
	@Column(name = "descrizioneTicket", columnDefinition = "TEXT", nullable = false)
	private String descrizioneTicket;
	
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	@Column(name = "dataTicket", nullable = false)
//	private LocalDate dataTicket;
	private LocalDate dataTicket = LocalDate.now();
	
	@ManyToOne
	@JsonIgnore
	@JsonManagedReference
	@JoinColumn(name = "categoria_id", nullable = false)
	private Categoria categoria;
	
	// GETTER SETTER

	public Categoria getCategoria() {
	    return categoria;
	}
	
	public void setCategoria(Categoria categoria) {
	    this.categoria = categoria;
	}
	

	public Integer getId() {
	    return id;
	}


	public void setId(Integer id) {
	    this.id = id;
	}

	public String getTitoloTicket() {
	    return titoloTicket;
	}

	public void setTitoloTicket(String titoloTicket) {
	    this.titoloTicket = titoloTicket;
	}

	public String getDescrizioneTicket() {
	    return descrizioneTicket;
	}

	public void setDescrizioneTicket(String descrizioneTicket) {
	    this.descrizioneTicket = descrizioneTicket;
	}

	public LocalDate getDataTicket() {
	    return dataTicket;
	}

	public void setDataTicket(LocalDate dataTicket) {
	    this.dataTicket = dataTicket;
	}
	
	
	
	
	
	

}
