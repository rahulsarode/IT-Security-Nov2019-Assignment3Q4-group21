package com.forum.service.impl;

import java.sql.SQLException;
import java.sql.Timestamp;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.forum.dao.UserDao;
import com.forum.entity.UserDetailsEntity;
import com.forum.model.UserDetailsModel;
import com.forum.service.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	UserDao userDao;

	public ResponseEntity<String> postUser(UserDetailsModel userModel) {
		UserDetailsEntity userEntity = new UserDetailsEntity();
		userEntity.setFirstName(userModel.getFirstName());
		userEntity.setLastName(userModel.getLastName());
		userEntity.setEmailId(userModel.getEmailId());
		userEntity.setPhoneNumber(userModel.getPhoneNumber());
		userEntity.setUserName(userModel.getUserName());
		userEntity.setPassword(userModel.getPassword());
		userEntity.setUserCreationDateTime(new Timestamp(System.currentTimeMillis()));
		userEntity.setUserModificationDateTime(new Timestamp(System.currentTimeMillis()));
		userEntity.setUserIsActive(true);
		userDao.postUserDetails(userEntity);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	public ResponseEntity<UserDetailsModel> getUser(Integer userId) {
		UserDetailsModel userDetails = userDao.getUserDetails(userId);
		return new ResponseEntity<>(userDetails, HttpStatus.OK);
	}

	public ResponseEntity<String> editUser(UserDetailsModel userModel) {
		int userId = userModel.getUserId();
		String firstName = userModel.getFirstName();
		String lastName = userModel.getLastName();
		String emailId = userModel.getEmailId();
		String phoneNumber = userModel.getPhoneNumber();
		userDao.editUserDetails(userId, firstName, lastName, emailId, phoneNumber);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	public ResponseEntity<String> deleteUser(Integer userId) {
		userDao.deleteUserDetails(userId);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@Override
	public ResponseEntity<UserDetailsModel> validateUser(UserDetailsModel userModel) throws SQLException {
		UserDetailsEntity userDetailsEntity = userDao.verifyUser(userModel.getUserName(), userModel.getPassword());
		if (userDetailsEntity.getUserId()==0) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		else
		{
			UserDetailsModel userDetailsModel = new UserDetailsModel();
			userDetailsModel.setUserId(userDetailsEntity.getUserId());
			return new ResponseEntity<>(userDetailsModel, HttpStatus.OK);
		}
	}
}
