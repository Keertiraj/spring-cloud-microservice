package com.naturalprogrammer.spring.sample.core;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
	
	@Value("${app.name}")
	private String appName;
	
	@RequestMapping("/hello")
	@ResponseBody
	public String hello()
	{
		return "Hello from, "+appName;
		
	}
	
/*	@RequestMapping("/")
	public String welcome()
	{
		return "home";
		
	}
*/
}
