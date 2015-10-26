package com.naturalprogrammer.spring.sample.dto;

import java.util.Collection;
import java.util.HashSet;

import javax.validation.constraints.NotNull;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.naturalprogrammer.spring.sample.entities.User;
import com.naturalprogrammer.spring.sample.entities.User.Role;

public class UserDetailsImpl implements UserDetails{

	 /**
	 * 
	 */
	private static final long serialVersionUID = 653043473481599664L;
	
	private User user;
	 
	public UserDetailsImpl(User user){
		
		this.user = user;
		
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> authorities = new HashSet<GrantedAuthority>(
				user.getRoles().size() + 1);

		for (Role role : user.getRoles())
			authorities.add(new SimpleGrantedAuthority("ROLE_" + role.name()));

		authorities.add(new SimpleGrantedAuthority("ROLE_USER"));

		return authorities;
		}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return user.getPassword();
	}

	@Override
	@NotNull
	public String getUsername() {
		// TODO Auto-generated method stub
		return user.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	
	

}
