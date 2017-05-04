package com.app.service;

import java.util.List;

import com.app.dao.ReservaDAO;
import com.app.dominio.Reserva;

public class ReservaService {

	private ReservaDAO udao=null;

	public ReservaService() {
		udao = new ReservaDAO();
	}
	
	public String agregar(Reserva c){
		return udao.agregar(c);
	}
        public List<Reserva> listarTodos() {
                return udao.listarTodos();
        }
}
