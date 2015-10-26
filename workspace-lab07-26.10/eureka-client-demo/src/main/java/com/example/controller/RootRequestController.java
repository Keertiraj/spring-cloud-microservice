package com.example.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RootRequestController {
	
	@Value("${words}") 
	String words;
	//private String words;
//  String words = "icicle,refrigerator,blizzard,snowball";
	
	@RequestMapping("/")
	public @ResponseBody Word getWord() {
		String[] wordArray = words.split(",");
		int i = (int)Math.round(Math.random() * (wordArray.length - 1));
		return new Word(wordArray[i]);
	}

}
