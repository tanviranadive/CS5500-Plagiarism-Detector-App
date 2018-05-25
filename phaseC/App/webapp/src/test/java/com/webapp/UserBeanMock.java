/**
 * 
 */
package com.webapp;

import com.webapp.bean.UserBean;

/**
 * @author adi
 * @date Mar 30, 2018
 */
public class UserBeanMock {
	public static UserBean createmock() {
		UserBean user = new UserBean();
		user.setUsername("adi");
		user.setPassword("adi");
		user.setRole("student");
		return user;
		
	}

}
