package com.forum.service;

import java.sql.SQLException;

import org.springframework.http.ResponseEntity;

import com.forum.model.UserDetailsModel;

public interface UserService {

	public ResponseEntity<String> postUser(UserDetailsModel userModel);

	public ResponseEntity<UserDetailsModel> getUser(Integer userId);

	public ResponseEntity<String> editUser(UserDetailsModel userModel);

	public ResponseEntity<String> deleteUser(Integer userId);

	public ResponseEntity<UserDetailsModel> validateUser(UserDetailsModel userModel) throws SQLException;
}
