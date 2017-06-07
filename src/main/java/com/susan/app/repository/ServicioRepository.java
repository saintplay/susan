package com.susan.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.susan.app.entity.Servicio;

@Transactional
public interface ServicioRepository extends CrudRepository<Servicio, Long>{
 
	Servicio findByNombre(String nombre);

	Iterable<Servicio> findByNombreContaining(String nombre);
	
}