package com.example.demo.model.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer userId;
	
	@Column(length = 50, nullable = false)
	private String userName;
	
	@Column(length = 50, nullable = false)
	private String userAccount;
	
	@Column(length = 50, nullable = false)
	private String userPassword;
	
	@Column(length = 50, nullable = false)
	private String userSalt;
	
	@Column(length = 50, nullable = false)
	private String userPhone;
	
	@Column(length = 50, nullable = false)
	private String userAddress;
	
	@Column(length = 50, nullable = false)
	private String userRole;
	
	@Column(nullable = false, columnDefinition = "BOOLEAN DEFAULT FALSE")
	private boolean userActive;

	@OneToMany(mappedBy = "user")
	private List<Content> contents;
}
