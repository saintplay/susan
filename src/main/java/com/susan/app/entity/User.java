package com.susan.app.entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class User {
	@Id
	private String username;
	private String password;
	private boolean enable;
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
	private List<UserRole> userRole = new ArrayList<UserRole>();

	public User(String username, String password, boolean enable) {
		super();
		this.username = username;
		this.password = password;
		this.enable = enable;
	}

	public User(String username, String password, boolean enable, List<UserRole> userRole) {
		super();
		this.username = username;
		this.password = password;
		this.enable = enable;
		this.userRole = userRole;
	}

	public User() {
		super();
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean enabled) {
		this.enable = enable;
	}

	public List<UserRole> getUserRole() {
		return userRole;
	}

	public void setUserRole(List<UserRole> userRole) {
		this.userRole = userRole;
	}
}