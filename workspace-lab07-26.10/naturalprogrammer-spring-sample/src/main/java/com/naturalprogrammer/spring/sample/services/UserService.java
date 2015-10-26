package com.naturalprogrammer.spring.sample.services;

import org.springframework.validation.BindingResult;

import com.naturalprogrammer.spring.sample.dto.ForgotPasswordForm;
import com.naturalprogrammer.spring.sample.dto.ResetPasswordForm;
import com.naturalprogrammer.spring.sample.dto.SignupForm;

public interface UserService {

	public abstract void signup(SignupForm signupForm);
	
	public abstract void verify(String verificationCode);

	public abstract void forgotPassword(ForgotPasswordForm forgotPasswordForm);

	public abstract void resetPassword(String forgotPasswordCode, ResetPasswordForm resetPasswordForm,
			BindingResult result);
	
	

	
}
