package com.forum.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFilter;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@JsonFilter("UserDetailsEntityFilter")
@Table(name = "user_details")

public class UserDetailsEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_id")
	private int userId;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "email_id")
	private String emailId;

	@Column(name = "user_creation_date_time")
	private Date userCreationDateTime;

	@Column(name = "user_modification_date_time")
	private Date userModificationDateTime;

	@Column(name = "user_is_active")
	private boolean userIsActive;

	@Column(name = "phone_number")
	private String phoneNumber;

	@Column(name = "user_name")
	private String userName;

	@Column(name = "password")
	private String password;

}
