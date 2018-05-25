package com.webapp.bean;

import org.hibernate.validator.constraints.NotEmpty;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="User")
public class UserBean implements Serializable{
 
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
        private int id; 
	
	@NotEmpty(message="UserId cannot be empty")
	@Column(name="username")
	private String username;
	@NotEmpty(message="Password cannot be empty")
	@Column(name="password")
	private String password;
	@Column(name="role")
	private String role;
	
	public String getUsername() {
		return username;
	}
 
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getRole() {
		return role;
	}
 
	public void setRole(String role) {
		this.role = role;
	}
 
	public String getPassword() {
		return password;
	}
 
	public void setPassword(String password) {
		this.password = password;
	}
}
