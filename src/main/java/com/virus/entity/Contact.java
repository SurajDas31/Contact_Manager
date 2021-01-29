package com.virus.entity;

import javax.persistence.*;

@Entity
@Table(name = "contact")
public class Contact {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String name;
	private String mobileNo;
	@ManyToOne
	private User user;

	public Contact(String name, String mobileNo, User user) {
		this.name = name;
		this.mobileNo = mobileNo;
		this.user = user;
	}
}
