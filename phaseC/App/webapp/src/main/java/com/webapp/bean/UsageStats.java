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
@Table(name="usagestats")
public class UsageStats  implements Serializable{

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
        private int id; 
	
	@Column(name="user")
	private String user;
	
	@Column(name="time")
	private String time;
	
	@Column(name="score")
	private String score;
	
	public String getUser() {
		return user;
	}
 
	public void setUser(String user) {
		this.user = user;
	}
	
	public String getTime() {
		return time;
	}
 
	public void setTime(String time) {
		this.time = time;
	}
 
 
	public void setScore(String score) {
		this.score = score;
	}
}
