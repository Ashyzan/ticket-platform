package it.ashyzan.ticket_platform.security;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import it.ashyzan.ticket_platform.model.User;

public class DatabaseUserDetails implements UserDetails {

	private final Integer id;
	private final String username;
	private final String password;
	private final Set<GrantedAuthority> authorities;

	public DatabaseUserDetails(User user) {

		this.id = user.getId();
		this.username = user.getUsername();
		this.password = user.getPassword();
		this.authorities = new HashSet<>();
		this.authorities.add(new SimpleGrantedAuthority(user.getRole().getName()));
//		for (Role ruolo : user.getRole() ) {
//			this.authorities.add(new SimpleGrantedAuthority(ruolo.getName()));
//		}
		
		
		
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {

		return this.authorities;
	}

	
	@Override
	public String getPassword() {

		return this.password;
	}

	@Override
	public String getUsername() {
		return this.username;
	}
	
	

}
