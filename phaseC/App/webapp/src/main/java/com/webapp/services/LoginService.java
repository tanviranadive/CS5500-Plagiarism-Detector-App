package com.webapp.services;
import java.util.regex.Matcher;


import java.util.regex.Pattern;

import java.util.*;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.webapp.controller.LoginController;
import org.springframework.beans.factory.annotation.Autowired;

import com.webapp.bean.UserBean;
import com.webapp.dao.IwebappDao;
import com.webapp.dao.UserRepository;
//Service for login and register
@Service
public class LoginService implements IwebappService{
	
	  private Pattern pattern;
	  private Pattern pattern1;
	  private Matcher matcher;
	  private Matcher matcher1;

	  @Autowired
		UserRepository repository;
	  
	  private static final String USERNAME_PATTERN = "\\b[a-zA-Z][a-zA-Z0-9\\-._]{3,15}\\b";
	  private static final String PD_PATTERN = "\\b[a-zA-Z][a-zA-Z0-9\\-._]{3,15}\\b";
	  
	  @Autowired
	  private IwebappDao webappDao;
	 
	  /**
	   * Authenticate the login user to check if user exists in database
	   * @param userBean
	   * @return true if user does not exist in the database else false
	   */
		public boolean authenticateUser(UserBean userBean){
			return webappDao.authenticateUser(userBean);
		}
		
		/**
		 * Check if user can register
		 * @param userBean
		 * @return true if user does not already exist else false
		 */
		public boolean validateUserRegister(UserBean userBean) {
			UserBean user = repository.findByUsername(userBean.getUsername());
			return user==null;
		}
		
		/**
		 * Validate the user registration fields
		 * @param username
		 * @param password
		 * @return true if registration fields are valid else false
		 */
		public boolean validateFields(String username, String password){
			 pattern = Pattern.compile(USERNAME_PATTERN);
			  pattern1 = Pattern.compile(PD_PATTERN);
			  matcher = pattern.matcher(username);
			  matcher1 = pattern1.matcher(password);
			  return matcher.matches()&&matcher1.matches();
		}
}

