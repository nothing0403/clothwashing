package com.example.demo.model.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Sender {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer senderId;

	@Column(length = 50, nullable = false)
	private String senderName;
	
	@Column(length = 50, nullable = false)
	private String senderPhone;
	
	@Column(length = 50, nullable = false)
	private String senderAddress;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false)
	private Date contentSendTime;
	
	@OneToOne(mappedBy = "sender")
	private Content content;
}
