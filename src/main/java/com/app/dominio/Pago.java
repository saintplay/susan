package com.app.dominio;

import java.sql.Timestamp;

public class Pago {
	private int id;
	private int reserva_id;
	private Timestamp fecha;
	private float monto;
	private String estado;
	private String modalidad;

	public Pago() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getReservaId() {
		return reserva_id;
	}

	public void setReservaId(int reserva_id) {
		this.reserva_id = reserva_id;
	}

	public Timestamp getFecha() {
		return fecha;
	}

	public void setFecha(Timestamp fecha) {
		this.fecha = fecha;
	}

	public float getMonto() {
		return monto;
	}
	
	public void setMonto(float monto) {
		this.monto = monto;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getModalidad() {
		return modalidad;
	}

	public void setModalidad(String modalidad) {
		this.modalidad = modalidad;
	}

}
