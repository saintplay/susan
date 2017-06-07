package com.susan.app.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

@Entity
public class Hotel {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Size(min = 4, max = 30, message = "nombre debe tener mas de 4 letras y menos de 30")
	private String nombre;

	@NotNull
	private int calificacion;

	@NotBlank
	@Max(value = 200)
	private String direccion;

	public Hotel() {
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getCalificacion() {
		return calificacion;
	}

	public void setCalificacion(int calificacion) {
		this.calificacion = calificacion;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	// esto es para probrar en consola
	@Override
	public String toString() {
		return "Hotel [id=" + id + ", nombre=" + nombre + ", calificacion=" + calificacion + ", direccion=" + direccion
				+ "]";
	}

}
