package com.app.service;

import com.app.dao.HabitacionDAO;
import com.app.dominio.Habitacion;

public class HabitacionService {

	private HabitacionDAO udao=null;

	public HabitacionService() {
		udao = new HabitacionDAO();
	}
	
	public String agregar(Habitacion c){
		return udao.agregar(c);
	}
}
