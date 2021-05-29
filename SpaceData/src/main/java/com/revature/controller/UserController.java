package com.revature.controller;

import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.model.User;
import com.revature.service.UserService;

@RestController
@RequestMapping(value = "/user")
public class UserController {

	private UserService uServ;

	@Autowired
	public UserController(UserService serv) {
		this.uServ = serv;
	}

	@PostMapping(value = "/register")
	public ResponseEntity<String> registerUser(@RequestBody LinkedHashMap<String, String> uMap) {
		String message = uServ.registerUser(uMap.get("username"), uMap.get("password"));
		return new ResponseEntity<String>(message, HttpStatus.OK);
	}

	@PostMapping(value = "/login")
	public ResponseEntity<String> loginUser(@RequestBody LinkedHashMap<String, String> uMap) {
		String message = uServ.loginUsername(uMap.get("username"), uMap.get("password"));
		return new ResponseEntity<String>(message, HttpStatus.OK);
	}

	@PostMapping(value = "/logout")
	public ResponseEntity<String> logoutUser(@RequestBody LinkedHashMap<String, String> uMap) {
		String message = uServ.logoutUsername(uMap.get("username"));
		return new ResponseEntity<String>(message, HttpStatus.OK);
	}

	@PostMapping(value = "/searchUser")
	public ResponseEntity<User> searchUser(@RequestBody LinkedHashMap<String, String> uMap) {
		User retrieved = uServ.searchUsers(uMap.get("username"));
		return new ResponseEntity<User>(retrieved, HttpStatus.OK);
	}
	
	@GetMapping(value = "/loggedOn")
	public ResponseEntity<List<User>> loggedOn() {
		List<User> uList = uServ.getLoggedOnUsers();
		return new ResponseEntity<List<User>>(uList, HttpStatus.OK);
	}

	@GetMapping(value = "/search/{username}")
	public ResponseEntity<User> searchUsers(@PathVariable("username") String username) {
		User retrieved = uServ.searchUsers(username);
		return new ResponseEntity<User>(retrieved, HttpStatus.OK);
	}

	@GetMapping(value = "/usernameExists/{username}")
	public ResponseEntity<String> usernameExists(@PathVariable("username") String username) {
		User retrieved = uServ.searchUsers(username);
		String message = (retrieved == null) ? "NO such user" : "User FOUND";
		return new ResponseEntity<String>(message, HttpStatus.OK);
	}

//	@GetMapping(value = "/loggedOn")
//	public ResponseEntity<List<User>> getLoggedOn() {
//		List<User> uList = uServ.getLoggedOnUsers();
//		return new ResponseEntity<List<User>>(uList, HttpStatus.OK);
//	}

}
