package com.webapp.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;


import org.springframework.web.bind.annotation.PathVariable;

import com.webapp.bean.UsageStats;
import com.webapp.bean.UserBean;
import com.webapp.dao.UsageStatsRepository;
import com.webapp.dao.UserRepository;
import com.webapp.services.IwebappService;
import com.webapp.services.LoginService;

import app.Compare;
import app.CompareResult;
import mail.SendMail;

// Controller for login
@Controller
@SessionAttributes("name")
public class LoginController {
	private String userbean = "userBean";
	private String redirect = "redirect:/user/";
	private String studenthome="/student/home";
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	@Autowired
	IwebappService loginService;
	LoginService service;

	@Autowired
	UserRepository repository;
	
	@Autowired
	UsageStatsRepository usageRepository;

	//private static String studentfolder = "/home/ec2-user/StudentFolder";

	/**
	 * Find all users
	 * @return
	 */
	@RequestMapping("/findall")
	public ResponseEntity<List<UserBean>> findAll() {

		List<UserBean> users = repository.findAll();
		return new ResponseEntity<List<UserBean>>(users, HttpStatus.OK);
	}

	/**
	 * Show login page
	 * @param model
	 * @return ModelAndView for login page
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView showLoginPage(ModelMap model) {
		ModelAndView mav = new ModelAndView("login");
		mav.addObject(userbean, new UserBean());
		return mav;
	}

	/**
	 * Retrieve user information for login
	 * @param request
	 * @param response
	 * @param user
	 * @return ModelAndView for login as student or professor home page
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView doLogin(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("userBean") UserBean user) {
		ModelAndView mav = new ModelAndView("login");
		// If not a valid user then add error
		// else proceed to user welcome page
		if (!loginService.authenticateUser(user)) {

			mav.addObject("errorMessage", "Username or Password is wrong!!");
			logger.info("\"errorMessage\", \"Username or Password is wrong!!\"" + user.getUsername());
			return mav;
			
		} else {
			response.setStatus(200);
			UserBean usr = repository.findByUsername(user.getUsername());
			String role = usr.getRole();
			if (role.equals("professor")) {
				return new ModelAndView(redirect + usr.getUsername() + "/professor/home");
			}
			return new ModelAndView(redirect + user.getUsername() + studenthome);	
		}
	}

	/**
	 *	Redirect from any other url to login page 
	 * @param model
	 * @return ModelAndView for login page
	 */
	@RequestMapping(value = "/*", method = RequestMethod.GET)
	public ModelAndView showMainPage(ModelMap model) {
		return new ModelAndView("redirect:login");
	}
	
}