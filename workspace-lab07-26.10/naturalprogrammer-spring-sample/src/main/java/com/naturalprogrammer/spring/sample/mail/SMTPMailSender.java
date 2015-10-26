package com.naturalprogrammer.spring.sample.mail;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;


public class SMTPMailSender implements  MailSender {
	
	Log log = LogFactory.getLog(SMTPMailSender.class);
	
	private JavaMailSender javaMailSender;
	
	public void setJavaMailSender(JavaMailSender javaMailSender)
	{
		this.javaMailSender = javaMailSender;
	}
	
	/* (non-Javadoc)
	 * @see com.naturalprogrammer.spring.sample.mail.MailSender#send(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void send(String to, String subject, String body) throws MessagingException
	{
		log.info("SMPT:"+body);
		MimeMessage msg = javaMailSender.createMimeMessage();
		MimeMessageHelper msgHelper;
		
			msgHelper = new MimeMessageHelper(msg,true);
			msgHelper.setSubject(subject);
			msgHelper.setTo(to);
			msgHelper.setText(body);
			javaMailSender.send(msg);
		
	}

}
