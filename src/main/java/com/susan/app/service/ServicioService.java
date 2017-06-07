package com.susan.app.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.susan.app.entity.Hotel;
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
  	// TODO Auto-generated method stub
  	servicioRepository.save(servicio);
  }

  @Override
  public Servicio findOne(Long id) {
  	// TODO Auto-generated method stub
  	return servicioRepository.findOne(id);
  }

  @Override
  public void delete(Long id) {
  	// TODO Auto-generated method stub
  	servicioRepository.delete(id);
  }

  @Override
  public Servicio findByNombre(String nombre) {
  	// TODO Auto-generated method stub
  	return servicioRepository.findByNombre(nombre);
  }

  @Override
  public Iterable<Servicio> findByNombreContaining(String nombre) {
  	// TODO Auto-generated method stub
  	return servicioRepository.findByNombreContaining(nombre);
  }
}