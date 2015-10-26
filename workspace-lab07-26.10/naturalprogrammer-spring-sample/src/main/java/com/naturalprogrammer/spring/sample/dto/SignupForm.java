package com.naturalprogrammer.spring.sample.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.naturalprogrammer.spring.sample.entities.User;

public class SignupForm {
	
	@NotNull
	@Size(min=10,max=100)
	@Pattern(regexp=User.EMAIL_PATTERN, message="{emailPatternError}")
	private String email;
	@NotNull
	@Size(min=3,max=100)
	private String name;
	@NotNull
	@Size(min=6,max=30,message="{passwordLengthError}")
	private String password;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {

		return this.email;
	}
}
