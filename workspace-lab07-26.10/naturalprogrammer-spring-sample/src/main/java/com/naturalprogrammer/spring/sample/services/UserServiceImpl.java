package com.naturalprogrammer.spring.sample.services;


import javax.mail.MessagingException;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationAdapter;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import org.springframework.validation.BindingResult;
import org.springframework.transaction.annotation.Propagation;

import com.naturalprogrammer.spring.sample.dto.ForgotPasswordForm;
import com.naturalprogrammer.spring.sample.dto.ResetPasswordForm;
import com.naturalprogrammer.spring.sample.dto.SignupForm;
import com.naturalprogrammer.spring.sample.dto.UserDetailsImpl;
import com.naturalprogrammer.spring.sample.entities.User;
import com.naturalprogrammer.spring.sample.entities.User.Role;
import com.naturalprogrammer.spring.sample.mail.MailSender;
import com.naturalprogrammer.spring.sample.repositories.UserRepository;
import com.naturalprogrammer.spring.sample.util.MyUtil;

@Service
@Transactional(propagation=Propagation.SUPPORTS,readOnly=true)
public class UserServiceImpl implements UserService,UserDetailsService {
	
	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	private UserRepository userRepository;
	private PasswordEncoder passwordEncoder;
	private MailSender mailSender;
	@Autowired
	public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder,MailSender mailSender){
		
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
		this.mailSender = mailSender;
	}
	
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED,readOnly=false)
	public void signup(SignupForm signupForm){
		
		final User user = new User();
		
		user.setEmail(signupForm.getEmail());
		user.setName(signupForm.getName());
		user.getRoles().add(Role.UNVERIFIED);
		user.setVerificationCode(RandomStringUtils.randomAlphanumeric(16));
		user.setPassword(passwordEncoder.encode(signupForm.getPassword()));
		
		userRepository.save(user);
		
		TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronizationAdapter(){
			@Override
			public void afterCommit(){
				
				String verifyLink = MyUtil.hostUrl()+"/users/"+user.getVerificationCode()+"/verify";
				try {
					mailSender.send(user.getEmail(),MyUtil.getMessage("verifySubject"),MyUtil.getMessage("verifyEmail",verifyLink));
					logger.info("Verification email to"+user.getEmail()+"queued.");

				} catch (MessagingException e) {
		           logger.error(ExceptionUtils.getStackTrace(e));
				}

			}
			
		});
		
	}


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
     
            User user = userRepository.findByEmail(username);
            
            if(user == null){
            	throw new UsernameNotFoundException(username);
            }
            
            return new UserDetailsImpl(user);
	}


	@Override
	@Transactional(propagation=Propagation.REQUIRED,readOnly=false)
	public void verify(String verificationCode) {
    
		long loggedInUserId = MyUtil.getSessionUser().getId();
		User user = userRepository.findOne(loggedInUserId);
		
		MyUtil.validate(user.getRoles().contains(Role.UNVERIFIED),"alreadyVerified");
		MyUtil.validate(user.getVerificationCode().equals(verificationCode),"incorrect","verification code");
		
		user.getRoles().remove(Role.UNVERIFIED);
		user.setVerificationCode(null);
		
		userRepository.save(user);
		
		
	}
	
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	public void forgotPassword(ForgotPasswordForm form) {
		
		final User user = userRepository.findByEmail(form.getEmail());
		final String forgotPasswordCode = RandomStringUtils.randomAlphanumeric(User.RANDOM_CODE_LENGTH);
		
		user.setForgotPasswordCode(forgotPasswordCode);
		final User savedUser = userRepository.save(user);
		
		TransactionSynchronizationManager.registerSynchronization(
			    new TransactionSynchronizationAdapter() {
			        @Override
			        public void afterCommit() {
			        	try {
							mailForgotPasswordLink(savedUser);
						} catch (MessagingException e) {
							logger.error(ExceptionUtils.getStackTrace(e));
						}
			        }

		    });				

	}
	
	private void mailForgotPasswordLink(User user) throws MessagingException {
		
		String forgotPasswordLink = 
				MyUtil.hostUrl() + "/reset-password/" +
				user.getForgotPasswordCode();
		mailSender.send(user.getEmail(),
				MyUtil.getMessage("forgotPasswordSubject"),
				MyUtil.getMessage("forgotPasswordEmail", forgotPasswordLink));

	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	public void resetPassword(String forgotPasswordCode,
			ResetPasswordForm resetPasswordForm,
			BindingResult result) {
		
		User user = userRepository.findByForgotPasswordCode(forgotPasswordCode);
		if (user == null)
			result.reject("invalidForgotPassword");
		
		if (result.hasErrors())
			return;
		
		user.setForgotPasswordCode(null);
		user.setPassword(passwordEncoder.encode(resetPasswordForm.getPassword().trim()));
		user.getRoles().remove(Role.UNVERIFIED);

		userRepository.save(user);
	}


}
