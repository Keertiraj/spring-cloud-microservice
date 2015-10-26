package com.naturalprogrammer.spring.sample.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.naturalprogrammer.spring.sample.entities.User;

public interface UserRepository extends JpaRepository<User,Long>{

	User findByEmail(String user);

	User findByForgotPasswordCode(String forgotPasswordCode);
	
}
