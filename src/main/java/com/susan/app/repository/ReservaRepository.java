package com.susan.app.repository;

import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import com.susan.app.entity.Reserva;

@Transactional
public interface ReservaRepository extends CrudRepository<Reserva, Long> {

	Iterable<Reserva> findByFechareserva(Date fechareserva);
}
