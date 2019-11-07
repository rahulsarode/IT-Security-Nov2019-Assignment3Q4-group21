package com.forum.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(Include.NON_NULL)
public class UserDetailsModel {
	private int userId;
	private String firstName;
	private String lastName;
	private String emailId;
	private String phoneNumber;
	private String userName;
	private String password;

}
