package com.example;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClientController {

	  @Value("${config-data1}") String configData;

	  @RequestMapping("/config-data")
	  public String showConfigWord() {
	    return "The config data is: " + configData;
	}

}
