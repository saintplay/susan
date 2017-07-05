package com.susan.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.susan.app.entity.Reserva;
import com.susan.app.entity.Usuario;
import com.susan.app.repository.ReservaRepository;

@Service
public class ReservaService implements IReservaService {

	@Autowired
	private ReservaRepository reservaRepository;

	@Override
	public Iterable<Reserva> findAll() {
		return reservaRepository.findAll();
	}

	@Override
	public void save(Reserva reserva) {
		reservaRepository.save(reserva);
	}

	@Override
	public Reserva findOne(Long id) {
		return reservaRepository.findOne(id);
	}

	@Override
	public void delete(Long id) {
		reservaRepository.delete(id);
	}

	@Override
	public Iterable<Reserva> findByUsuario(Usuario usuario) {
		return reservaRepository.findByUsuario(usuario);
	}

}