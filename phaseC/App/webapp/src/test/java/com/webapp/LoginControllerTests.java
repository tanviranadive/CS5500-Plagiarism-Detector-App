package com.webapp;


import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.NestedServletException;

import com.webapp.bean.UserBean;
import com.webapp.controller.StudentController;
import com.webapp.services.IwebappService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.File;
import java.nio.file.Paths;

/**
 * @author adi
 * @date Mar 29, 2018
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class LoginControllerTests {
	@Autowired
	private MockMvc mockMvc;
	
	 @Autowired
	 private WebApplicationContext webApplicationContext;

   /**
	@Test(expected=NestedServletException.class)
	public void testLoginUserFail() throws Exception {
		UserBean user = new UserBean();
		//user.setId(00001);
		user.setUsername("#tan");
		user.setPassword("tan");
		user.setRole("student");
		//String email = "";
		this.mockMvc.perform(post("/login").flashAttr("userBean", user))
		.andDo(print())
		.andExpect(status().is4xxClientError());
	}
	*/

	@Test
	public void testLoginUserSuccess() throws Exception {
		UserBean user = new UserBean();
		//user.setId(00001);
		user.setUsername("tan");
		user.setPassword("tan");
		user.setRole("student");
		//String email = "";
		this.mockMvc.perform(post("/login").flashAttr("userBean", user))
		.andDo(print())
		.andExpect(status().is3xxRedirection());
	}
	
	
	
	
	
	@Test
	public void testLoginProfSuccess() throws Exception {
		UserBean user = new UserBean();
		//user.setId(00001);
		user.setUsername("jose");
		user.setPassword("jose");
		user.setRole("professor");
		//String email = "";
		this.mockMvc.perform(post("/login").flashAttr("userBean", user))
		.andDo(print())
		.andExpect(status().is3xxRedirection());
	}


	@Test
	public void testdirectStudentSuccess() throws Exception {
		UserBean user = new UserBean();
		//user.setId(00001);
		user.setUsername("tan");
		user.setPassword("tan");
		user.setRole("student");
		//String email = "";
		this.mockMvc.perform(get("/user/"+user.getUsername()+"/student/home", user))
		.andDo(print())
		.andExpect(status().isOk());
	}



	@Test
	public void testdirectProfSuccess() throws Exception {
		UserBean user = new UserBean();
		//user.setId(00001);
		user.setUsername("tan");
		user.setPassword("tan");
		user.setRole("student");
		//String email = "";
		this.mockMvc.perform(get("/user/"+user.getUsername()+"/professor/home", user))
		.andDo(print())
		.andExpect(status().isOk());
	}



	@Test
	public void testAssignmentSuccess() throws Exception {
		UserBean user = new UserBean();
		//user.setId(00001);
		user.setUsername("tan");
		user.setPassword("tan");
		user.setRole("student");
		//String email = "";
		this.mockMvc.perform(get("/user/tan/student/home/assignment1", user))
		.andDo(print())
		.andExpect(status().isOk());
	}


/*	@Test
	public void testAssignmentPostSuccess() throws Exception {
		UserBean user = new UserBean();
		//user.setId(00001);
		user.setUsername("tan");
		user.setPassword("tan");
		user.setRole("student");
		//String email = "";
		MultipartFile[] ml = null; 
		this.mockMvc.perform(post("/user/tan/student/home/assignment1").flashAttr("userBean", user)
				.param("assignment", "assignment1")
				.param("myFile",""))
		.andDo(print())
		.andExpect(status().is4xxClientError());
	}*/

	@Test
	public void testAssignmentProfGetSuccess() throws Exception {
		UserBean user = new UserBean();
		//user.setId(00001);
		user.setUsername("tan");
		user.setPassword("tan");
		user.setRole("student");
		this.mockMvc.perform(get("/user/tan/professor/home/assignment1", user, "assignment1"))
		.andDo(print())
		.andExpect(status().isOk());
	}

	@Test
	public void testAssignmentListSuccess() throws Exception {
		UserBean user = new UserBean();
		//user.setId(00001);
		user.setUsername("tan");
		user.setPassword("tan");
		user.setRole("student");
		this.mockMvc.perform(post("/user/tan/professor/home/assignment1", user, "tan", "assignment1",
				"file1","file2","xyz@gmail.com", "assignment1"))
		.andDo(print())
		.andExpect(status().is4xxClientError());
	}

	@Test
	public void testAssignmentListSuccess2() throws Exception {
		UserBean user = new UserBean();
		//user.setId(00001);
		user.setUsername("tan");
		user.setPassword("tan");
		user.setRole("student");
		this.mockMvc.perform(post("/user/jose/professor/home/assignment1").flashAttr("userBean", user).param("username","tan")
				.param("assignment", "assignment1").param("CompFile1","tan_assignment1").param("CompFile2","tanvi_assignment1")
				.param("CompStrategy","SubSequence")
				.param("email","xyz@gmail.com"))
		.andDo(print())
		.andExpect(status().is2xxSuccessful());
	}
	
	
	/**
	 * 
	 * @throws Exception
	 */
	@Test
	public void testLoginUserSuccessget() throws Exception {
		UserBean user = new UserBean();
		user.setUsername("tan");
		user.setPassword("tan");
		user.setRole("student");
		this.mockMvc.perform(get("/login").flashAttr("userBean", user))
		.andDo(print())
		.andExpect(status().is2xxSuccessful());
	}
	

	/**
	 * 
	 * @throws Exception
	 */
	@Test
	public void testLoginUserfail() throws Exception {
		this.mockMvc.perform(post("/login"))
		.andDo(print())
		.andExpect(status().is2xxSuccessful());
	}
	
	
	/**
	 * 
	 * @throws Exception
	 */
	@Test
	public void testLoginUserfindall() throws Exception {
		UserBean user = new UserBean();
		user.setUsername("tan");
		user.setPassword("tan");
		user.setRole("student");
		
		user.setUsername("rohan");
		user.setPassword("rohan");
		user.setRole("student");
		
		user.setUsername("robert");
		user.setPassword("robert");
		user.setRole("student");
		
		this.mockMvc.perform(get("/findall").flashAttr("userBean", user))
		.andDo(print())
		.andExpect(status().is2xxSuccessful());
	}
	
	/**
	 * Test for any other url to be redirected to login page
	 * @throws Exception
	 */
	@Test
	public void testAnyOtherUrlRedirect() throws Exception {
		
		this.mockMvc.perform(get("/"))
		.andDo(print())
		.andExpect(status().is3xxRedirection());
	}
	
	/**
	 * Test for file upload of multipart file
	 * @throws Exception
	 */
	@Test
	public void testFileUpload() throws Exception{
		
		MockMvc mockMvc1 = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		UserBean user = new UserBean();
		//user.setId(00001);
		user.setUsername("tan");
		user.setPassword("tan");
		user.setRole("student");
		String uploadPath = "StudentFolder/assignment1";
		File folderobj = new File(uploadPath);
		//MultipartFile file = "StudentFolder/assignment1/tan_assignment1/"
		MockMultipartFile multipartFile = new MockMultipartFile("myFile", "testFile.py",
                "text/plain", "print(\"Hello world\")".getBytes());
        mockMvc1.perform(MockMvcRequestBuilders.fileUpload("/user/tan/student/home/assignment1").file(multipartFile)
        		.flashAttr("userBean", user).param("username","tan")
				.param("assignment", "assignment1")
        		)
                .andExpect(status().isFound());
        
        File file = new File(uploadPath+"/tan_assignment1/testFile.py");
        file.delete();
	}

	/**
	 * Test for file upload with no file given
	 * @throws Exception
	 */
	@Test
	public void testFileUploadFailure() throws Exception{
		
		MockMvc mockMvc1 = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		UserBean user = new UserBean();
		//user.setId(00001);
		user.setUsername("tan");
		user.setPassword("tan");
		user.setRole("student");
		String uploadPath = "StudentFolder/assignment1";
		File folderobj = new File(uploadPath);
        MvcResult result = mockMvc1.perform(MockMvcRequestBuilders.fileUpload("/user/tan/student/home/assignment1")
        		.flashAttr("userBean", user).param("username","tan")
				.param("assignment", "assignment1"))
		        .andDo(print())
		        .andReturn();
        assertTrue(result.getModelAndView().getModel().get("errormessage").equals("Please upload a file"));
	}
	
	@Test
	public void checkFolderCreation() throws Exception{
		StudentController sc = new StudentController();
		String path = "TestSample";
		sc.createFolder(path);
		File f = new File(path);
		f.delete();
	}
	
	/*@Test
	public void testFileUploadFailure1() throws Exception{
		
		MockMvc mockMvc1 = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		UserBean user = new UserBean();
		//user.setId(00001);
		user.setUsername("tan");
		user.setPassword("tan");
		user.setRole("student");
		String uploadPath = null;
		File folderobj = new File(uploadPath);
        MvcResult result = mockMvc1.perform(MockMvcRequestBuilders.fileUpload("/user/tan/student/home/assignment1")
        		.flashAttr("userBean", user).param("username","tan")
				.param("assignment", "assignment1"))
		        .andDo(print())
		        .andReturn();
        
	}*/


}


