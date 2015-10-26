package com.naturalprogrammer.spring.sample.mail;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;


public class MockMailSender implements  MailSender {
	
	Log log = LogFactory.getLog(MockMailSender.class);
	
    private DemoObject demoObject;
	
	public void setDemoObject(DemoObject demoObject)
	{
		this.demoObject = demoObject;
	}
	
	/* (non-Javadoc)
	 * @see com.naturalprogrammer.spring.sample.mail.MailSender#send(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void send(String to, String subject, String body)
	{
		log.info("body"+body);
		
	}

}
