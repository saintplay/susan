package com.example.demo.entity;

import javax.validation.constraints.Size;

public class Teacher {

	private Long id;
	
	@Size(min=4, max=20, message="nombre debe tener mas de 4 letras y menos de 20")
	private String name;

	public Teacher() {
		// TODO Auto-generated constructor stub
	}

	public Teacher(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
