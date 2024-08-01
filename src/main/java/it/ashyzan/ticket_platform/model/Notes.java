package it.ashyzan.ticket_platform.model;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

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
@Table(name = "note")
public class Notes {
    
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, unique = true)
	private Integer id;
	
        @NotBlank(message = "La descrizione della nota è obbligatoria")
	@Column(name = "descrizioneNota", columnDefinition = "TEXT", nullable = false)
	private String descrizioneNota;
	
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	@Column(name = "dataNota", nullable = false)
	private LocalDate dataNota = LocalDate.now();
	
	@ManyToOne
	@JoinColumn(name = "ticketNota", nullable = false)
	private Ticket ticketNota;
	
	@ManyToOne
  	@JoinColumn(name = "user_id", nullable = false)
  	private User user;
	
	// GETTER SETTER

	public Ticket getTicketNota() {
	    return ticketNota;
	}

	public void setTicketNota(Ticket ticketNota) {
	    this.ticketNota = ticketNota;
	}

	public Integer getId() {
	    return id;
	}

	public void setId(Integer id) {
	    this.id = id;
	}


	public String getDescrizioneNota() {
	    return descrizioneNota;
	}

	public void setDescrizioneNota(String descrizioneNota) {
	    this.descrizioneNota = descrizioneNota;
	}

	public LocalDate getDataNota() {
	    return dataNota;
	}

	public void setDataNota(LocalDate dataNota) {
	    this.dataNota = dataNota;
	}


}
