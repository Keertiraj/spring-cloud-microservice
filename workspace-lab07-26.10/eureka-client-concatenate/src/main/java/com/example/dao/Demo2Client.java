package com.example.dao;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.domain.Word;


@FeignClient("eureka-client-demo2")
public interface Demo2Client {

	@RequestMapping(value="/", method=RequestMethod.GET)
	public Word getWord();
	
}