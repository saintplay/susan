package com.susan.app.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.susan.app.entity.Servicio;

@Transactional
public interface ServicioRepository extends CrudRepository<Servicio, Long>{
 
	Servicio findByNombre(String nombre);

	Iterable<Servicio> findByNombreContaining(String nombre);
	
}