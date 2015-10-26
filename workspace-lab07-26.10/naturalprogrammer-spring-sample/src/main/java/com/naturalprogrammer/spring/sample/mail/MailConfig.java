package com.naturalprogrammer.spring.sample.mail;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.mail.javamail.JavaMailSender;

@Configuration
public class MailConfig {

	@Bean
	@Profile("dev")
	public MailSender mockMailSender(){
	
		MockMailSender smtp = new MockMailSender();
		smtp.setDemoObject(demoObject());
	
		return smtp;
	}

	@Bean
	@Profile("!dev")
	public MailSender SMTPMailSender(JavaMailSender javaMailSender){
		
		SMTPMailSender smtp = new SMTPMailSender();
		smtp.setJavaMailSender(javaMailSender);
		return smtp;
	}
	
	@Bean
	public DemoObject demoObject(){
		return new DemoObject();
	}
	
}
