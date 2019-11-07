package com.forum.dao;

import java.sql.SQLException;

import com.forum.entity.UserDetailsEntity;
import com.forum.model.UserDetailsModel;

public interface UserDao {

	public UserDetailsModel getUserDetails(Integer userId);

	public void postUserDetails(UserDetailsEntity userEntity);

	public void editUserDetails(Integer userId, String firstName, String lastName, String emailId, String phoneNumber);

	public void deleteUserDetails(Integer userId);

	public UserDetailsEntity verifyUser(String userName, String password) throws SQLException;

}
