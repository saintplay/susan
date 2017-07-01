package com.susan.app.service;

import com.susan.app.entity.Habitacion;
import com.susan.app.entity.Hotel;

public interface IHabitacionService {
	public Iterable<Habitacion> findAll();

	public void save(Habitacion habitacion);

	public Habitacion findOne(Long id);

	public void delete(Long id);

	public Iterable<Habitacion> findByHotel(Hotel hotel);
}
