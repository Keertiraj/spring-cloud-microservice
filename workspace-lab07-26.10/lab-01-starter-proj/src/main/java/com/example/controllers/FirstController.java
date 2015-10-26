package com.example.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
public class FirstController {

	private static final Logger log = LoggerFactory.getLogger(FirstController.class);
	
	//@RequestMapping("/")
	public String hello()
	{
		log.info("Success===");
		return "hello";
	}

}
