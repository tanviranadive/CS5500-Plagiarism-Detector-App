package com.webapp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;
 


import com.webapp.bean.UserBean;
import com.webapp.dao.UserRepository;
import com.webapp.services.IwebappService;
import com.webapp.services.LoginService;

/**
 * Controller for register
 * @author Tanvi
 *
 */
@Controller
public class RegisterController {
	
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
	IwebappService loginService;
	LoginService service;
	
	@Autowired
	UserRepository repository;
	
	/**
	 * Display registration page
	 * @param model
	 * @return ModelAndView for register
	 */
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView showRegisterPage(ModelMap model) {
		ModelAndView mav = new ModelAndView("register");
		mav.addObject("userBean", new UserBean());
		return mav;
	}
	
	/**
	 * Retrieve user fields for registration
	 * @param request
	 * @param response
	 * @param user
	 * @return ModelAndView for user home
	 */
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ModelAndView doLogin(HttpServletRequest request, HttpServletResponse response,
			  @ModelAttribute("userBean") UserBean user) {
		
		if(loginService.validateFields(user.getUsername(), user.getPassword())){
		logger.info("register" + user.getUsername());
		ModelAndView mav = null;
			// If not a valid user then add error
			// else proceed to user home page
			if(!loginService.validateUserRegister(user)) {
				
				 mav = new ModelAndView("register");
				 mav.addObject("message", "Username already exists!!");
				 return mav;
				 
			} else {
				repository.save(user);
				repository.flush();
				logger.info("user registered");
				return new ModelAndView("redirect:/user/" + user.getUsername() + "/student/home");	
			}
		}
		else{
			// Username and password not valid then show error message
			ModelAndView mav = new ModelAndView("register");
			mav.addObject("message","Username and Password must be 3-15 characters & start with a letter");
			return mav;
		}
			
	}

}
