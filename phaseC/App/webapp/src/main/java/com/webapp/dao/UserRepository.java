package com.webapp.dao;
import com.webapp.bean.*;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
//import org.springframework.data.repository.CrudRepository;
@Repository
public interface UserRepository extends JpaRepository<UserBean, Integer>{

	List<UserBean> findAll();
	UserBean findByUsername(String username);
}
