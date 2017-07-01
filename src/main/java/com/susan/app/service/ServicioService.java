package com.susan.app.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.susan.app.entity.Servicio;
import com.susan.app.repository.ServicioRepository;

@Service
public class ServicioService implements IServicioService {
  
  @Autowired
  private ServicioRepository servicioRepository;
  
  @Override
  public Iterable<Servicio> findAll() {
	return servicioRepository.findAll();
  }
  
  @Override
  public void save(Servicio servicio) {
  	servicioRepository.save(servicio);
  }

  @Override
  public Servicio findOne(Long id) {
  	return servicioRepository.findOne(id);
  }

  @Override
  public void delete(Long id) {
  	servicioRepository.delete(id);
  }

  @Override
  public Servicio findByNombre(String nombre) {
  	return servicioRepository.findByNombre(nombre);
  }

  @Override
  public Iterable<Servicio> findByNombreContaining(String nombre) {
  	return servicioRepository.findByNombreContaining(nombre);
  }
}