package com.app.dominio;

public class Servicio {
	private int Id;
	private String nombre;
	private String descripcion;
    private float costo;
	
	public Servicio() {
		
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		this.Id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

    public void setDescripcion(String descripcion) {
            this.descripcion = descripcion;
    }
    public float getCosto() {
            return costo;
    }

    public void setCosto(float costo) {
            this.costo = costo;
    }
}
