package com.webapp.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.webapp.bean.UserBean;
import com.webapp.dao.UserRepository;
import com.webapp.services.IwebappService;
import com.webapp.services.LoginService;

@Controller
@SessionAttributes("name")
public class StudentController {
	
	private String userbean = "userBean";
	private String redirect = "redirect:/user/";
	private String studenthome="/student/home";
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	@Autowired
	IwebappService loginService;
	LoginService service;

	@Autowired
	UserRepository repository;
	
	private static String studentfolder = "StudentFolder";
	
	@RequestMapping(value = "/user/{username}/student/home", method = RequestMethod.GET)
	public ModelAndView showStudentPage(ModelMap model, @PathVariable("username") String username) {
		ModelAndView mav = new ModelAndView("studentHome");
		mav.addObject(userbean, new UserBean());
		mav.addObject("username", username);
		mav.addObject("homeUrl", "/user/" + username + "/student/home");
		return mav;

	}
	
	
	@RequestMapping(value = "/user/{username}/student/home/{assignment}", method = RequestMethod.GET)
	public ModelAndView showAssignmentPage(ModelMap model, @PathVariable("username") String username,
			@PathVariable("assignment") String assignmentNo) {
		ModelAndView mav = new ModelAndView("assignment");
		UserBean usr = repository.findByUsername(username);
		mav.addObject(userbean, usr);
		mav.addObject("username", username);
		mav.addObject("homeUrl", "/user/" + username + studenthome);
		mav.addObject("assignment", assignmentNo);
		return mav;
	}

	@RequestMapping(value = "/user/{username}/student/home/{assignment}", method = RequestMethod.POST)
	public ModelAndView assignmentUpload(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("userBean") UserBean user,
			@PathVariable("username") String username,
			@PathVariable("assignment") String assignmentNo,
			@RequestParam("myFile") MultipartFile[] fileset1) {

		ModelAndView mav = new ModelAndView("assignment");
		
		if(fileset1.length!=0 && !fileset1[0].isEmpty()) {
			
		
		FileOutputStream fos = null;
		FileOutputStream fos1 = null;
		BufferedReader reader = null;


		String folderPath1 = studentfolder;
		String folderPath2 = studentfolder + "/" + assignmentNo;
		String StudentfolderPath = folderPath2 + "/" + user.getUsername() + "_" + assignmentNo;
		
		File studentfolderObj = new File(StudentfolderPath);
		createFolder(folderPath1);
		createFolder(folderPath2);
		createFolder(StudentfolderPath);

		HashMap<String, String> studentfolderMap = new HashMap<String, String>();

		for (MultipartFile file : fileset1) {
			
			InputStream in;
			try {
				// saving files from fileset1 in Folder1
				File fileObj = new File(StudentfolderPath + "/" + file.getOriginalFilename());
				fos = new FileOutputStream(fileObj);
				fos.write(file.getBytes());

				in = file.getInputStream();
				reader = new BufferedReader(new InputStreamReader(in));
				StringBuilder out = new StringBuilder();
				String line;
				while ((line = reader.readLine()) != null) {
					out.append(line);
					out.append(System.getProperty("line.separator"));
				}

				studentfolderMap.put(file.getOriginalFilename(), out.toString());

			} catch (Exception e) {
				mav.addObject("message", e.getMessage());
				return mav;
			} finally {
				try {
					reader.close();
				} catch (IOException e1) {
					logger.error("Error closing file reader");
				}
				try {
					fos.close();
				} catch (IOException e) {
					logger.error("Error closing file writer");
				}
			}
		}
		
			return new ModelAndView("redirect:/user/"+user.getUsername()+"/student/home");
		}
		mav.addObject("errormessage","Please upload a file");
		return mav;
	}
	
	
	/**
	 * Creates a folder
	 * @param folderPath
	 */
	public void createFolder(String folderPath){
		File folderObj = new File(folderPath);
		if (!folderObj.exists()) {
			if (folderObj.mkdir()) {
				logger.info("Folder is created!");
			}
		}
	}

}
