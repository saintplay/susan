package com.susan.app.repository;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import com.susan.app.entity.Habitacion;
import com.susan.app.entity.Hotel;

@Transactional
public interface HabitacionRepository extends CrudRepository<Habitacion, Long> {

	Iterable<Habitacion> findByHotel(Hotel hotel);
}
