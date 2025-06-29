package com.example.demo.model.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
public class Cloth {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer clothId;
	
	@Column(length=50, nullable = false)
	private String clothName;
	
	@Column(length=200, nullable = false)
	private String clothDescription;
	
	@Column(length=255, nullable = false)
	private String clothImg;
	
	@Column(length=50, nullable = false)
	private String clothKind;
	
	@Column(nullable = false)
	private Integer clothPrice;

	@OneToMany(mappedBy = "cloth")
	private List<Item> item;
}
