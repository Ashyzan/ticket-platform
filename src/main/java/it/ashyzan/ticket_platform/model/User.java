package it.ashyzan.ticket_platform.model;

import java.util.List;

import org.hibernate.annotations.ColumnDefault;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;

@Entity
public class User {

    @Id
  	private Integer id;

    	@NotBlank(message = "Il nome utente è obbligatorio")
    	@Column(name = "nome", nullable = false)
  	private String nome;

    	@NotBlank(message = "L'email utente è obbligatoria")
    	@Column(name = "email", nullable = false)
  	private String email;
  	
    	@NotBlank(message = "L'username è obbligatorio")
    	@Column(name = "username", nullable = false)
  	private String username;
  	
    	@NotBlank(message = "La password obbligatoria")
	@Column(name = "password", nullable = false)
  	private String password;
  	
  	@ColumnDefault("false")
  	@Column( nullable = true, name = "flagDisponibile")
  	private Boolean flagDisponibile;
  	
  	@ManyToOne
  	@JoinColumn(name = "role_id", nullable = false)
  	private Role role;
  	
  	@OneToMany(mappedBy = "user")
	private List<Ticket> listaTicket;
  	
//  	@OneToMany(mappedBy = "user")
//	private List<Notes> listaNote;
  	
  	// GETTER SETTER
  	
  	public String getUsername() {
	    return username;
	}

	public void setUsername(String username) {
	    this.username = username;
	}

	public Integer getId() {
	    return id;
	}

	public void setId(Integer id) {
	    this.id = id;
	}

	public String getNome() {
	    return nome;
	}

	public void setNome(String nome) {
	    this.nome = nome;
	}

	public String getEmail() {
	    return email;
	}

	public void setEmail(String email) {
	    this.email = email;
	}

	public String getPassword() {
	    return password;
	}

	public void setPassword(String password) {
	    this.password = password;
	}

	public Boolean getFlagDisponibile() {
	    return flagDisponibile;
	}

	public void setFlagDisponibile(Boolean flagDisponibile) {
	    this.flagDisponibile = flagDisponibile;
	}

	public Role getRole() {
	    return role;
	}

	public void setRole(Role role) {
	    this.role = role;
	}

	public List<Ticket> getListaTicket() {
	    return listaTicket;
	}

	public void setListaTicket(List<Ticket> listaTicket) {
	    this.listaTicket = listaTicket;
	}

//	public List<Notes> getListaNote() {
//	    return listaNote;
//	}
//
//	public void setListaNote(List<Notes> listaNote) {
//	    this.listaNote = listaNote;
//	}

	


}
