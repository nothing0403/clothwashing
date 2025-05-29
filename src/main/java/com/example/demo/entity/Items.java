package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Items {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(nullable = false)
	private Integer quantity;
	
	@Column(nullable = false)
	private Integer itemPrice;
	
	@Column(nullable = false)
	private boolean state = false;
	
	@ManyToOne
	@JoinColumn(name = "order_id")
	private Order order;
	
	@OneToOne
	@JoinColumn(name = "cloth_id")
	private Clothes cloth;
}
