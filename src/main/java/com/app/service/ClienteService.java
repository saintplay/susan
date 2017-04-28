package com.app.service;

import com.app.dao.ClienteDAO;
import com.app.dominio.Cliente;

public class ClienteService {

	private ClienteDAO udao=null;

	public ClienteService() {
		udao = new ClienteDAO();
	}
	
	public String agregar(Cliente c){
		return udao.agregar(c);
	}
	
	
	public Cliente login(String user, String pass){
		return udao.login(user, pass);
	}
}
