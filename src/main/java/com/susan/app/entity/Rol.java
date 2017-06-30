package com.susan.app.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "rol")
public class Rol {
	@Id
	@GeneratedValue
	private Long id;
	private String role;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "iduser")
	private Usuario usuario;

	public Rol() {
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

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUser(Usuario usuario) {
		this.usuario = usuario;
	}
}
