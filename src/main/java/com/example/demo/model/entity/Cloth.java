package com.example.demo.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Cloth {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer clothId;
	
	@Column(length=50, nullable = false)
	private String clothName;
	
	@Column(length=255, nullable = false)
	private String imgUrl;
	
	@Column(length=50, nullable = false)
	private String clothSize;
	
	@Column(nullable = false)
	private Integer clothPrice;

	@OneToOne(mappedBy = "cloth")
	private Item item;
}
