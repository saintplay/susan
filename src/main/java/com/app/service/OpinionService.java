package com.app.service;

import com.app.dao.OpinionDAO;
import com.app.dominio.Opinion;

public class OpinionService {

	private OpinionDAO udao=null;

	public OpinionService() {
		udao = new OpinionDAO();
	}
	
	public String agregar(Opinion c){
		return udao.agregar(c);
	}
        public List<Opinion> listarTodos() {
                return udao.listarTodos();
        }
}
