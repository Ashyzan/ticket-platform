package it.ashyzan.ticket_platform.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
@Entity
public class Role {
    @Id
	private Integer id;

	@NotNull
	private String name;
	
	@OneToMany(mappedBy = "role")
	private List<User> user;

	// GETTER SETTER

	public List<User> getUser() {
	    return user;
	}

	public void setUser(List<User> user) {
	    this.user = user;
	}

	public Integer getId() {
	    return id;
	}

	public void setId(Integer id) {
	    this.id = id;
	}

	public String getName() {
	    return name;
	}

	public void setName(String name) {
	    this.name = name;
	}



	

}
