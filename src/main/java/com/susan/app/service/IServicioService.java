package com.susan.app.service;

import com.susan.app.entity.Servicio;

public interface IServicioService {

	public Iterable<Servicio> findAll();
	
	public void save(Servicio servicio);

	public Servicio findOne(Long id);

	public void delete(Long id);

	public Servicio findByNombre(String nombre);

	public Iterable<Servicio> findByNombreContaining(String nombre);
}