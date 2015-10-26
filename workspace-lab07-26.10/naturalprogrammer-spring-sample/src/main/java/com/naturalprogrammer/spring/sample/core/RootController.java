package com.naturalprogrammer.spring.sample.core;

import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.naturalprogrammer.spring.sample.dto.ForgotPasswordForm;
import com.naturalprogrammer.spring.sample.dto.ResetPasswordForm;
import com.naturalprogrammer.spring.sample.dto.SignupForm;
import com.naturalprogrammer.spring.sample.mail.MailSender;
import com.naturalprogrammer.spring.sample.mail.SMTPMailSender;
import com.naturalprogrammer.spring.sample.services.UserService;
import com.naturalprogrammer.spring.sample.util.MyUtil;
import com.naturalprogrammer.spring.sample.validators.ForgotPasswordFormValidator;
import com.naturalprogrammer.spring.sample.validators.ResetPasswordFormValidator;
import com.naturalprogrammer.spring.sample.validators.SignupFormValidator;

@Controller
public class RootController {
	
	Log logger = LogFactory.getLog(RootController.class);
	
	private UserService userService;
	private MailSender mailSender;
	private SignupFormValidator signupFormValidator;
	private ForgotPasswordFormValidator forgotPasswordFormValidator;
	private ResetPasswordFormValidator resetPasswordFormValidator;

	
	@Autowired
	public RootController(MailSender mailSender,UserService userService, SignupFormValidator signupFormValidator){
		
		this.userService = userService;
		this.mailSender = mailSender;
		this.signupFormValidator = signupFormValidator;
		this.forgotPasswordFormValidator = forgotPasswordFormValidator;
		this.resetPasswordFormValidator = resetPasswordFormValidator;
		
	}
	
	@InitBinder("signupForm")
	protected void initSignupBinder(WebDataBinder binder){
		binder.setValidator(signupFormValidator);
	}
	
	@InitBinder("forgotPasswordForm")
	protected void initForgotPasswordBinder(WebDataBinder binder) {
		binder.setValidator(forgotPasswordFormValidator);
	}
	
	@InitBinder("resetPasswordForm")
	protected void initResetPasswordBinder(WebDataBinder binder) {
		binder.setValidator(resetPasswordFormValidator);
	}

	
 @RequestMapping(value="/signup",method=RequestMethod.GET)
 public String signup(Model model){
	 
	 model.addAttribute("signupForm",new SignupForm());
	 return "signup";
	 
 }
	
 @RequestMapping(value="/signup",method=RequestMethod.POST)
 public String signup(@ModelAttribute("signupForm") @Valid SignupForm signupForm, BindingResult result,RedirectAttributes redirectAttributes){
	 
	 if(result.hasErrors())
	 {
		 return "signup";
	 }
  //  logger.info(signupForm.toString());	
	 userService.signup(signupForm);
	 
	 MyUtil.flash(redirectAttributes,"success","signupSuccess");
    
    return "redirect:/";
 }
 
 @RequestMapping(value="/forgot-password",method=RequestMethod.GET)
 public String forgotPassword(Model model){
	 
	 model.addAttribute("forgotPasswordForm",new ForgotPasswordForm());
	 return "forgot-password";
	 
 }
 
	/**
	 * Forgot password
	 */
	@RequestMapping(value = "/forgot-password", method = RequestMethod.POST)
	public String forgotPassword(
			@ModelAttribute("forgotPasswordForm") @Valid ForgotPasswordForm forgotPasswordForm,
			BindingResult result, RedirectAttributes redirectAttributes) {

		if (result.hasErrors())
			return "forgot-password";

		userService.forgotPassword(forgotPasswordForm);
		MyUtil.flash(redirectAttributes, "info", "checkMailResetPassword");

		return "redirect:/";
	}
	
	  @RequestMapping(value = "/reset-password/{forgotPasswordCode}")
	    public String resetPassword(@PathVariable("forgotPasswordCode") String forgotPasswordCode, Model model) {
	    	
	     	model.addAttribute(new ResetPasswordForm());
	    	return "reset-password";
	    	
	    }
	  
	
	@RequestMapping(value = "/reset-password/{forgotPasswordCode}",
			method = RequestMethod.POST)
	public String resetPassword(
			@PathVariable("forgotPasswordCode") String forgotPasswordCode,
			@ModelAttribute("resetPasswordForm")
				@Valid ResetPasswordForm resetPasswordForm,
			BindingResult result,
			RedirectAttributes redirectAttributes) {

		userService.resetPassword(forgotPasswordCode, resetPasswordForm, result);
		
		if (result.hasErrors())
			return "reset-password";

		MyUtil.flash(redirectAttributes, "success", "passwordChanged");

		return "redirect:/login";
	}



 
}
