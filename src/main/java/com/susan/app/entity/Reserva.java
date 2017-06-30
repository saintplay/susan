package com.susan.app.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="reserva")
public class Reserva {
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "usuarioid")
	private Usuario usuario;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "habitacionid")
	private Habitacion habitacion;
	
	@Temporal(TemporalType.DATE)
	@Column(name="fecha_desde")
	private Date fechadesde;
	
	@Temporal(TemporalType.DATE)
	@Column(name="fecha_hasta")
	private Date fechahasta;
	
	@Column(name="estado")
	private String estado;
	
	@Temporal(TemporalType.DATE)
	@Column(name="fecha_reserva")
	private Date fechareserva;

	@Column(name="costo_total")
	private String costototal;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Habitacion getHabitacion() {
		return habitacion;
	}

	public void setHabitacion(Habitacion habitacion) {
		this.habitacion = habitacion;
	}

	public Date getFechaDesde() {
		return fechadesde;
	}

	public void setFechaDesde(Date fechadesde) {
		this.fechadesde = fechadesde;
	}

	public Date getFechaHasta() {
		return fechahasta;
	}

	public void setFechaHasta(Date fechahasta) {
		this.fechahasta = fechahasta;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Date getFechaReserva() {
		return fechareserva;
	}

	public void setFechaReserva(Date fechareserva) {
		this.fechareserva = fechareserva;
	}

	public String getCostoTotal() {
		return costototal;
	}

	public void setCostoTotal(String costototal) {
		this.costototal = costototal;
	}
}
