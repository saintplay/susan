package com.app.service;

import java.util.List;

import com.app.dao.ServicioDAO;
import com.app.dominio.Servicio;

public class ServicioService {

	private ServicioDAO udao=null;

	public ServicioService() {
		udao = new ServicioDAO();
	}
	
	public String agregar(Servicio c){
		return udao.agregar(c);
	}
        public List<Servicio> listarTodos() {
                return udao.listarTodos();
        }
}
