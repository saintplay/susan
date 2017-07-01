package com.susan.app.service;

import com.susan.app.entity.Reserva;
import com.susan.app.entity.Usuario;

public interface IReservaService {
	public Iterable<Reserva> findAll();

	public void save(Reserva reserva);

	public Reserva findOne(Long id);

	public void delete(Long id);

	public Iterable<Reserva> findByUsuario(Usuario usuario);
}
