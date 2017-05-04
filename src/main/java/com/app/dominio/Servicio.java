package com.app.dominio;

public class Servicio {
	private int Id;
	private String nombre;
	private String descripcion;
        private int hotel_id;
	
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
        public String getHotelId() {
                return hotel_id;
        }

        public void setHotelId(int hotel_id) {
                this.hotel_id = hotel_id;
        }
}
