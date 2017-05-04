package com.app.dominio;

import java.sql.Timestamp;

public class Opinion {
	private int Id;
	private int cliente_id;
	private int hotel_id;
    private int habitacion_id;
    private Timestamp fecha;
    private String texto;
	
	public Opinion() {
		
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		this.Id = id;
	}

    public int getHotelId() {
            return hotel_id;
    }

    public void setHotelId(int hotel_id) {
            this.hotel_id = hotel_id;
    }
    public int getHabitacionId() {
            return habitacion_id;
    }

    public void setHabitacionId(int habitacion_id) {
            this.habitacion_id = habitacion_id;
    }

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public Timestamp getFecha() {
		return fecha;
	}

	public void setFecha(Timestamp fecha) {
		this.fecha = fecha;
	}

	public int getCliente_id() {
		return cliente_id;
	}

	public void setCliente_id(int cliente_id) {
		this.cliente_id = cliente_id;
	}
}
