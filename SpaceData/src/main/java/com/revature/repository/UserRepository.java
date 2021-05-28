package com.revature.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.revature.model.User;

public interface UserRepository extends JpaRepository<User, Integer>{

//	User findUserByUsernameOrEmail(String username, String email);
	
	User findUserByUsernameAndPassword(String username, String password);
	
//	@Query("SELECT u FROM User u WHERE u.loggedOn = true")
//	List<User> findAllLoggedOnUsers();
}
