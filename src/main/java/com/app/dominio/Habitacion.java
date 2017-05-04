package com.app.dominio;

public class Habitacion {
	private int Id;
	private String nombre;
	private int piso;
        private int tipo;
	private String descripcion;
	private String foto;
        private int hotel_id;
        private double precio;

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

	public String getPiso() {
		return piso;
	}

	public void setPiso(String piso) {
		this.piso = piso;
	}

        public String getHotelId() {
                return hotel_id;
        }

        public void setHotelId(int hotel_id) {
                this.hotel_id = hotel_id;
        } 
}
