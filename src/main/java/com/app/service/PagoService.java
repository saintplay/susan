package com.app.service;

import com.app.dao.PagoDAO;
import com.app.dominio.Pago;

public class PagoService {

	private PagoDAO udao=null;

	public PagoService() {
		udao = new PagoDAO();
	}
	
	public String agregar(Pago c){
		return udao.agregar(c);
	}
        public List<Pago> listarTodos() {
                return udao.listarTodos();
        }
}
