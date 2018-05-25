package com.webapp.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
import org.springframework.web.servlet.ModelAndView;
import com.webapp.bean.UsageStats;
import com.webapp.bean.UserBean;
import com.webapp.dao.UsageStatsRepository;
import com.webapp.dao.UserRepository;

import app.Compare;
import app.CompareResult;
import mail.SendMail;

/**
 * 
 * @author Tanvi
 * Controller for Professor home page and student submission comparison
 *
 */
@Controller
@SessionAttributes("name")
public class ProfessorController {

	private String userbean = "userBean";
	private String studenthome="/student/home";
	private static String studentfolder = "StudentFolder";
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	@Autowired
	UserRepository repository;
	
	@Autowired
	UsageStatsRepository usageRepository;
	
	/**
	 * Mapping to display the home page for Professor with assignment list
	 * @param model
	 * @param username
	 * @return ModelAndView for professor home page
	 */
	@RequestMapping(value = "/user/{username}/professor/home", method = RequestMethod.GET)
	public ModelAndView showProfessorPage(ModelMap model, @PathVariable("username") String username) {
		ModelAndView mav = new ModelAndView("professorHome");
		mav.addObject(userbean, new UserBean());
		mav.addObject("username", username);
		mav.addObject("homeUrl", "/user/" + username + "/professor/home");
		return mav;
	}
	
	/**
	 * Mapping to display student submissions in the selected assignment and choose submissions to compare
	 * @param model
	 * @param username
	 * @param assignmentNo
	 * @param request
	 * @return ModelAndView for the student submissions
	 */
	@RequestMapping(value = "/user/{username}/professor/home/{assignment}", method = RequestMethod.GET)
	public ModelAndView showAssignmentProfessor(ModelMap model, @PathVariable("username") String username,
			@PathVariable("assignment") String assignmentNo, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("assignmentList");
		mav.addObject(userbean, new UserBean());
		mav.addObject("username", username);
		mav.addObject("homeUrl", "/user/" + username + studenthome);
		mav.addObject("assignment", assignmentNo);

		List<String> files = new ArrayList<String>();
		String basePath = studentfolder + "/" + assignmentNo;
		mav.addObject("basePath", basePath);
		createFolder(studentfolder);
		createFolder(basePath);
		for (File f : new File(studentfolder + "/" + assignmentNo).listFiles()) {
			// Iterate here to create download links for each file
			files.add(f.getName());
		}
		mav.addObject("files", files);
		request.setAttribute("files", files);
		return mav;
	}

	
	/**
	 * Mapping to retrieve options chosen to compare student submissions and show plagiarism result
	 * @param request,response,user,username,assignmentNo,filename1,filename2,compareStrategy,email
	 * @return ModelAndView with chosen options
	 */
	@RequestMapping(value = "/user/{username}/professor/home/{assignment}", method = RequestMethod.POST)
	public ModelAndView assignmentList(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("userBean") UserBean user, 
			@PathVariable("username") String username,
			@PathVariable("assignment") String assignmentNo,
			@RequestParam("CompFile1") String filename1,
			@RequestParam("CompFile2") String filename2, 
			@RequestParam("CompStrategy") String compareStrategy,
			@RequestParam("email") String email) {

		ModelAndView mav = new ModelAndView("assignmentList");
		String folderPath1 = studentfolder + "/" + assignmentNo + "/" + filename1;
		String folderPath2 = studentfolder + "/" + assignmentNo + "/" + filename2;

		Compare compareObj = new Compare();
		String compStrategy = compareStrategy;
		Double score = 0.5;
		List<CompareResult> resultList = compareObj.compare(compStrategy, folderPath1, folderPath2);
		
		StringBuffer plagiarismResults = new StringBuffer();
		for(CompareResult compareResult : resultList){
			String line = compareResult.getFileName1() + " \t " + compareResult.getFileName2() + " \t " + String.format("%.2f", compareResult.getScore())+"%";
			plagiarismResults.append("\n");
			plagiarismResults.append(line);
		}
		mav.addObject("score", String.format("%.2f", score));
		if (!email.equals("")) {
			String emailAddress = email;
			String body = "Plagiarism results files and percent match found: \n "+plagiarismResults.toString();
			SendMail.sendFromGMail(emailAddress, "Plagiarism status", body);
		}
		
		// Save usage statistics for user into database
		UsageStats newStats = new UsageStats();
		newStats.setUser(username);
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		newStats.setTime(timeStamp);
		newStats.setScore(score.toString());
		
		usageRepository.save(newStats);
		usageRepository.flush();
		logger.info("Saved usage stats");
		
		// Show plagiarism results page
		ModelAndView mavt = new ModelAndView("uploadStatus");
		mavt.addObject("score", String.format("%.2f", score));
		mavt.addObject("results", resultList);
		mavt.addObject(mav);

		return mavt;
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
			} else {
				logger.error("Failed to create directory");
			}
		}
	}
	
}
