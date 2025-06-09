package com.example.demo.model.entity;

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
public class Content {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer contentId;
	
	@Column(nullable = false)
	private String contentBuildDate;
	
	@Column(nullable = false)
	private String contentFinalDate;
	
	@Column(nullable = false, columnDefinition = "BOOLEAN DEFAULT FALSE")
	private boolean contentState;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	@Column(nullable = false)
	private Integer contentPrice;
	
	@OneToMany(mappedBy = "content")
	private List<Item> items;
	
	@OneToOne
	@JoinColumn(name = "sender_id")
	private Sender sender;
	
	@OneToOne
	@JoinColumn(name = "receiver_id")
	private Receiver receiver;
}
