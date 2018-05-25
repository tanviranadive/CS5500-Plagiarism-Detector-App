package com.webapp.dao;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.webapp.bean.UserBean;

//@Component
@Transactional
@Repository
public class webappDao implements IwebappDao {

private JdbcTemplate jdbcTemplate;
@PersistenceContext	
private EntityManager entityManager;	

	/*@Override
	public UserBean getUserByUsername(String username) {
		return entityManager.find(UserBean.class, username);
	}*/
	
	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	
	@SuppressWarnings("deprecation")
	public boolean authenticateUser(UserBean userBean){
		boolean userExists = false;
		System.out.println(userBean.getUsername());
		int rowcount = jdbcTemplate.queryForObject("select count(*) from user " +
				" where username = ? and password = ?",
				new Object[] {userBean.getUsername(),userBean.getPassword()},Integer.class);
		if(rowcount==1){
			userExists = true;
		}
		
		return userExists;
	}
	
}
