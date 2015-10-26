package com.naturalprogrammer.spring.sample.core;

import javax.annotation.Resource;
import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.naturalprogrammer.spring.sample.mail.MailSender;
import com.naturalprogrammer.spring.sample.mail.MockMailSender;

@RestController
public class MailController {
	
	private MailSender ms ;
	
	@Autowired
	public void mailSend(MailSender ms){
		this.ms = ms;
	}
	
	
	@RequestMapping("/mail")
	public String hello()
	{
		try {
			ms.send("kp", "test", "good");
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "mail sent";
		
	}

}
