package com.app.dominio;

import java.sql.Timestamp;

public class Reserva {
	private int Id;
	private int cliente_id;
    private int habitacion_id;
	private Timestamp fecha_desde;
    private Timestamp fecha_hasta;
    private String estado;
    private String tipo;
    private float costo_total;
	
	public Reserva() {
		
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		this.Id = id;
	}

	public int getClienteId() {
		return cliente_id;
	}

	public void setClienteId(int cliente_id) {
		this.cliente_id = cliente_id;
	}

	public Timestamp getFechaDesde() {
		return fecha_desde;
	}
	
	public Timestamp getFechaHasta() {
		return fecha_hasta;
	}

	public void setFechaDesde(Timestamp fecha_desde) {
        this.fecha_desde = fecha_desde;
	}

    public void setFechaHasta(Timestamp fecha_hasta) {
            this.fecha_hasta = fecha_hasta;
    }
   
    public int getHabitacionId() {
            return habitacion_id;
    }

    public void setHabitacionId(int habitacion_id) {
            this.habitacion_id = habitacion_id;
    }

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public float getCosto_total() {
		return costo_total;
	}

	public void setCosto_total(float costo_total) {
		this.costo_total = costo_total;
	}
}
