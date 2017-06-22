package com.susan.app.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "userrole")
public class UserRole {
	@Id
	@GeneratedValue
	private Long id;
	private String role;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "iduser")
	private User user;

	public UserRole(String role, User user) {
		super();
		this.role = role;
		this.user = user;
	}

	public UserRole() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
