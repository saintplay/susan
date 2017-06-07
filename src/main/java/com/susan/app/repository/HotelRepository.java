package com.susan.app.repository;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import com.susan.app.entity.Hotel;

@Transactional
public interface HotelRepository extends CrudRepository<Hotel, Long> {

	Hotel findByNombre(String nombre);

	Iterable<Hotel> findByNombreContaining(String nombre);
}
