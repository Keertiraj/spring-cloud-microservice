package com.naturalprogrammer.spring.sample;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.naturalprogrammer.spring.sample.mail.SMTPMailSender;

@SpringBootApplication
@ComponentScan
@EnableAutoConfiguration
@EnableTransactionManagement
public class NaturalprogrammerSpringSampleApplication {

    public static void main(String[] args) {
    	
    	Log logger = LogFactory.getLog(SMTPMailSender.class);

        
    	ApplicationContext ctx = SpringApplication.run(NaturalprogrammerSpringSampleApplication.class, args);
    	
    	String beanNames[] = ctx.getBeanDefinitionNames();
    	
    	for(String bean: beanNames){
    		
    	//	logger.info("Test:::"+bean);
    	}
        
        
    }
}
