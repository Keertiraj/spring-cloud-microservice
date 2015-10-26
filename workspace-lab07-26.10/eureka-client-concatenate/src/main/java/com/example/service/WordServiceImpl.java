package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.Demo2Client;
import com.example.dao.Demo3Client;
import com.example.dao.Demo4Client;
import com.example.dao.Demo5Client;
import com.example.dao.DemoClient;
import com.example.domain.Word;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class WordServiceImpl implements WordService {

	@Autowired DemoClient demoClient;
	@Autowired Demo2Client demo2Client;
	@Autowired Demo3Client demo3Client;
	@Autowired Demo4Client demo4Client;
	@Autowired Demo5Client demo5Client;
	
	
	@Override
	@HystrixCommand(fallbackMethod="getFallbackDemo")
	public Word getDemo() {
		return demoClient.getWord();
	}
	
	@Override
	public Word getDemo2() {
		return demo2Client.getWord();
	}
	
	@Override
	public Word getDemo3() {
		return demo3Client.getWord();
	}
	
	@Override
	@HystrixCommand(fallbackMethod="getFallbackDemo4")
	public Word getDemo4() {
		return demo4Client.getWord();
	}
	
	@Override
	@HystrixCommand(fallbackMethod="getFallbackDemo5")
	public Word getDemo5() {
		return demo5Client.getWord();
	}	
	
	public Word getFallbackDemo() {
		return new Word("Someone");
	}
	
	public Word getFallbackDemo4() {
		return new Word("");
	}
	
	public Word getFallbackDemo5() {
		return new Word("something");
	}

}
