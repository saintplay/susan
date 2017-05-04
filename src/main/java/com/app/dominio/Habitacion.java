package com.app.dominio;

public class Habitacion {
	private int Id;
	private String nombre;
	private int piso;
    private int tipo;
	private String descripcion;
	private String foto;
    private int hotel_id;
    private float precio;

	public Habitacion() {
		
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

	public int getPiso() {
		return piso;
	}

	public void setPiso(int piso) {
		this.piso = piso;
	}

    public int getHotelId() {
        return hotel_id;
    }

    public void setHotelId(int hotel_id) {
        this.hotel_id = hotel_id;
    }

	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	} 
}
