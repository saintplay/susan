package com.susan.app.service;

import java.util.Date;

import com.susan.app.entity.Reserva;

public interface IReservaService {
	public Iterable<Reserva> findAll();

	public void save(Reserva reserva);

	public Reserva findOne(Long id);

	public void delete(Long id);

	public Iterable<Reserva> findByFechareserva(Date fechareserva);
}
