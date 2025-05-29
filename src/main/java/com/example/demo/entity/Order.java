package com.example.demo.entity;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(nullable = false)
	private LocalDateTime sendTime;
	
	@Column(nullable = false)
	private LocalDateTime receiveTime;
	
	@Column(nullable = false)
	private boolean state = false;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	@Column(nullable = false)
	private Integer totalPrice;
	
	@OneToMany(mappedBy = "order")
	private List<Items> item;
	
	@ManyToOne
	@JoinColumn(name = "sender_id")
	private Sender sender;
	
	@ManyToOne
	@JoinColumn(name = "receiver_id")
	private Receiver receiver;
}
