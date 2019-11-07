package com.forum.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.forum.dao.UserDao;
import com.forum.entity.UserDetailsEntity;
import com.forum.model.UserDetailsModel;

@Repository
public class UserDaoImpl implements UserDao {
	@Autowired
	SessionFactory sessionFactory;

	public UserDetailsModel getUserDetails(Integer userId) {
		Session session = sessionFactory.getCurrentSession();
		UserDetailsEntity userDetailsEntity = session.get(UserDetailsEntity.class, userId);
		UserDetailsModel userDetailsModel = new UserDetailsModel();
		userDetailsModel.setUserId(userId);
		userDetailsModel.setFirstName(userDetailsEntity.getFirstName());
		userDetailsModel.setLastName(userDetailsEntity.getLastName());
		userDetailsModel.setEmailId(userDetailsEntity.getEmailId());
		userDetailsModel.setPhoneNumber(userDetailsEntity.getPhoneNumber());
		return userDetailsModel;
	}

	public void postUserDetails(UserDetailsEntity userEntity) {
		Session session = sessionFactory.getCurrentSession();
		session.save(userEntity);
	}

	public void editUserDetails(Integer userId, String firstName, String lastName, String emailId, String phoneNumber) {
		Session session = sessionFactory.getCurrentSession();
		UserDetailsEntity existingUserDetailsEntity = getUserDetailsEntity(userId, session);
		existingUserDetailsEntity.setFirstName(firstName);
		existingUserDetailsEntity.setLastName(lastName);
		existingUserDetailsEntity.setEmailId(emailId);
		existingUserDetailsEntity.setPhoneNumber(phoneNumber);
		existingUserDetailsEntity.setUserModificationDateTime(new Timestamp(System.currentTimeMillis()));
		session.update(existingUserDetailsEntity);
	}

	public void deleteUserDetails(Integer userId) {
		Session session = sessionFactory.getCurrentSession();
		UserDetailsEntity userDetailsEntity = getUserDetailsEntity(userId, session);
		userDetailsEntity.setUserIsActive(false);
		session.update(userDetailsEntity);
	}

	private UserDetailsEntity getUserDetailsEntity(Integer userId, Session session) {
		UserDetailsEntity userDetailsEntity = session.get(UserDetailsEntity.class, userId);
		return userDetailsEntity;
	}
	
	@Override
	public UserDetailsEntity verifyUser(String userName, String password) throws SQLException {
		UserDetailsEntity user = new UserDetailsEntity();
		try {
		Class.forName("org.postgresql.Driver");
		Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/forum", "postgres",
				"postgres");
		
	//******************SAFE*******************
		
		//	String userQuery = "SELECT * FROM user_details WHERE user_name = ? and password = ?";  
		//	PreparedStatement pstmt = con.prepareStatement( userQuery );
		//	pstmt.setString( 1, userName); 
		//	pstmt.setString( 2, password);
		//	ResultSet resultSet = pstmt.executeQuery( );
		
	//*******************UNSAFE*****************
			
			Statement statement = con.createStatement();
			
			ResultSet resultSet =statement.executeQuery("SELECT * FROM user_details WHERE user_name = '"+ userName +"' AND PASSWORD='" + password + "'"); 
			
			if (resultSet.next()){
				user.setUserId(resultSet.getInt(1));
				user.setFirstName(resultSet.getString(2));
				user.setLastName(resultSet.getString(3));
				user.setEmailId(resultSet.getString(4));
				user.setUserCreationDateTime(resultSet.getDate(5));
				user.setUserModificationDateTime(resultSet.getDate(6));
				user.setUserIsActive(resultSet.getBoolean(7));
				user.setPhoneNumber(resultSet.getString(8));
				user.setUserName(resultSet.getString(9));
				user.setPassword(resultSet.getString(10));
				
		}
			
		} catch (Exception e) {
			e.printStackTrace();		
		}
		return user;
	}
}
