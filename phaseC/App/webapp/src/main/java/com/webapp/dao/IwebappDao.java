package com.webapp.dao;

import java.util.List;

import javax.sql.DataSource;
import org.springframework.stereotype.Component;
import com.webapp.bean.UserBean;

@Component
public interface IwebappDao {
	public abstract void setDataSource(DataSource dataSource);
	public abstract boolean authenticateUser(UserBean userBean);
}
