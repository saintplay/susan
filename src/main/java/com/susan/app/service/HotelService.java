package com.susan.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.susan.app.entity.Hotel;
import com.susan.app.repository.HotelRepository;

@Service
public class HotelService implements IHotelService {

	@Autowired
	private HotelRepository hotelRepository;

	@Override
	public Iterable<Hotel> findAll() {
		// TODO Auto-generated method stub
		return hotelRepository.findAll();
	}

	@Override
	public void save(Hotel hotel) {
		// TODO Auto-generated method stub
		hotelRepository.save(hotel);
	}

	@Override
	public Hotel findOne(Long id) {
		// TODO Auto-generated method stub
		return hotelRepository.findOne(id);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		hotelRepository.delete(id);
	}

	@Override
	public Hotel findByNombre(String nombre) {
		// TODO Auto-generated method stub
		return hotelRepository.findByNombre(nombre);
	}

	@Override
	public Iterable<Hotel> findByNombreContaining(String nombre) {
		// TODO Auto-generated method stub
		return hotelRepository.findByNombreContaining(nombre);
	}

}
