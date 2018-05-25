/**
 * 
 */
package com.webapp;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import com.webapp.bean.UserBean;

/**
 * @author adi
 * @date Mar 30, 2018
 */
@RunWith(SpringJUnit4ClassRunner.class)

@SpringBootTest
@AutoConfigureMockMvc
public class RegisterControllerTests {
	@Autowired
    private MockMvc mockMvc;

	@Autowired
	 private WebApplicationContext wac;
	
	//user.setId(00001);
	
	@Test
    public void testRegisterGetUserSuccess() throws Exception {
		UserBean user = new UserBean();
		user.setUsername("adi");
		user.setPassword("adi");        
        //String email = "";
        this.mockMvc.perform(get("/register"))
                .andExpect(status().isOk());
    }
	
	/*@Test
    public void testRegisterUserSuccess() throws Exception {
		UserBean user = new UserBean();
		user.setUsername("adi");
		user.setPassword("adi");        
        //String email = "";
        this.mockMvc.perform(post("/register").flashAttr("userBean", user))
                .andDo(print())
                .andExpect(status().is3xxRedirection());
    }*/
	
	@Test
    public void testregisterUserFail() throws Exception {
		UserBean user = new UserBean();
		user.setUsername("tanNew2");
		user.setPassword("tanNew2");        
		MvcResult result = this.mockMvc.perform(post("/register").flashAttr("userBean", user))
                .andDo(print())
                .andReturn();
        assertTrue(result.getModelAndView().getViewName().equals("register"));
        assertTrue(result.getModelAndView().getModel().get("message").equals("Username already exists!!"));
    }
	

	@Test
    public void testregisterUserFailure() throws Exception {
		UserBean user = new UserBean();
		this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
		ModelAndView mav;
		user.setUsername("ta");
		user.setPassword("t");        
        //String email = "";
		MvcResult result = this.mockMvc.perform(post("/register").flashAttr("userBean", user))
                .andDo(print())
                .andReturn();
        assertTrue(result.getModelAndView().getViewName().equals("register"));
        assertTrue(result.getModelAndView().getModel().get("message").equals("Username and Password must be 3-15 characters & start with a letter"));
                
    }

}
