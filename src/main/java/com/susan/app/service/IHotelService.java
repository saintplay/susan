package com.susan.app.service;

import com.susan.app.entity.Hotel;

public interface IHotelService {

	public Iterable<Hotel> findAll();

	public void save(Hotel hotel);

	public Hotel findOne(Long id);

	public void delete(Long id);

	public Hotel findByNombre(String nombre);

	public Iterable<Hotel> findByNombreContaining(String nombre);
}
