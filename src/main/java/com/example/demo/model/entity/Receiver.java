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
public class Receiver {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer receiverId;

	@Column(length = 50, nullable = false)
	private String receiverName;
	
	@Column(length = 50, nullable = false)
	private String receiverPhone;
	
	@Column(length = 50, nullable = false)
	private String receiverAddress;
	
	@Column(length = 50, nullable = false)
	private String receiverDate;
	
	@OneToMany(mappedBy = "receiver")
	private List<Content> contents;
}
