package com.webapp.services;

import java.util.List;

import com.webapp.bean.UserBean;

public interface IwebappService {
	public abstract boolean authenticateUser(UserBean userBean);
	public abstract boolean validateUserRegister(UserBean user);
	public abstract boolean validateFields(String username, String password);
}