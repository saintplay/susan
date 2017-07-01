package com.susan.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.susan.app.entity.Habitacion;
import com.susan.app.entity.Hotel;
import com.susan.app.repository.HabitacionRepository;

@Service
public class HabitacionService implements IHabitacionService {

	@Autowired
	private HabitacionRepository habitacionRepository;
	
	@Override
	public Iterable<Habitacion> findAll() {
		return habitacionRepository.findAll();
	}

	@Override
	public void save(Habitacion habitacion) {
		habitacionRepository.save(habitacion);
	}

	@Override
	public Habitacion findOne(Long id) {
		return habitacionRepository.findOne(id);
	}

	@Override
	public void delete(Long id) {
		habitacionRepository.delete(id);
	}

	@Override
	public Iterable<Habitacion> findByHotel(Hotel hotel) {
	  	return habitacionRepository.findByHotel(hotel);
	}

}
