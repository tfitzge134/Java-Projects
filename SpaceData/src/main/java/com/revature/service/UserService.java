package com.revature.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.model.User;
import com.revature.repository.UserRepository;

@Service
public class UserService {

	private UserRepository uRepo;

	public UserService() {

	}

	@Autowired
	public UserService(UserRepository repo) {
		this.uRepo = repo;
	}

	public String registerUser(String username, String password) {
		try {
			uRepo.save(new User(username, password));
			return "User created successfully";
		} catch (Exception e) {
			e.printStackTrace();
			return "Error: User was not created";
		}
	}

	public String loginUsername(String username, String password) {
		try {
			User loggedIn = uRepo.findUserByUsernameAndPassword(username, password);
			if (loggedIn == null) {
				return "User login failed";
			} else {
				loggedIn.setLoggedOn(true);
				uRepo.save(loggedIn);
				return "User logged in successfully";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "User login failed";
		}
	}

	public String logoutUsername(String username) {
		try {
			User loggedIn = uRepo.findUserByUsername(username);
			if (loggedIn == null) {
				return "User already logged out.";
			} else {
				loggedIn.setLoggedOn(false);
				uRepo.save(loggedIn);
				return "User logged out successfully.";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "User login failed";
		}
	}

	public User searchUsers(String username) {
		try {
			return uRepo.findUserByUsername(username);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<User> getLoggedOnUsers() {
		try {
			return uRepo.findUserByLoggedOn(true);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
//	public List<User> getLoggedOnUsers(){
//		return uRepo.findAllLoggedOnUsers();
//	}
}
