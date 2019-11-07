package com.forum.controller;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.forum.model.UserDetailsModel;
import com.forum.service.UserService;

@RestController
@RequestMapping("/")
public class UserController {
	@Autowired
	UserService userService;

	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/users")
	public ResponseEntity<String> postuserDetails(@RequestBody UserDetailsModel userModel) {
		return userService.postUser(userModel);
	}
	

	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/users/{userId}")
	public ResponseEntity<UserDetailsModel> getuserDetails(@PathVariable int userId) {
		return userService.getUser(userId);
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@PutMapping("/users/{userId}")
	public ResponseEntity<String> edituserDetails(@PathVariable int userId, @RequestBody UserDetailsModel userModel) {
		userModel.setUserId(userId);
		return userService.editUser(userModel);
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@DeleteMapping("/users/{userId}")
	public ResponseEntity<String> deleteuserDetails(@PathVariable int userId) {
		return userService.deleteUser(userId);
	}
	
	
	/*//*****************UNSAFE*****************
	@CrossOrigin(origins = "*")
	@PostMapping("/login")
	public ResponseEntity<UserDetailsModel> validateLogin(@RequestBody UserDetailsModel userModel) throws SQLException {
		return userService.validateUser(userModel);
	}*/
	//*****************SAFE*****************
	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/login")
	public ResponseEntity<UserDetailsModel> validateLogin(@RequestBody UserDetailsModel userModel) throws SQLException {
		return userService.validateUser(userModel);
	}
}

