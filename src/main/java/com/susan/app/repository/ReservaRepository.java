package com.susan.app.repository;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import com.susan.app.entity.Reserva;
import com.susan.app.entity.Usuario;

@Transactional
public interface ReservaRepository extends CrudRepository<Reserva, Long> {

	Iterable<Reserva> findByUsuario(Usuario usuario);
}
