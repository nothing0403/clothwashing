package com.example.demo.entity;

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
	private Integer id;
	
	@Column(length = 50, nullable = false)
	private String name;
	
	@Column(length = 50, nullable = false)
	private String account;
	
	@Column(length = 50, nullable = false)
	private String password;
	
	@Column(length = 50, nullable = false)
	private String salt;
	
	@Column(length = 50, nullable = false)
	private String phone;
	
	@Column(length = 50, nullable = false)
	private String address;
	
	@Column(length = 50, nullable = false)
	private String role;
	
	@Column(nullable = false)
	private boolean active = false;

	@OneToMany(mappedBy = "user")
	private List<Order> orders;
}
