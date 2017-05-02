package com.app.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.app.dominio.Cliente;
import com.app.service.ClienteService;
import com.app.util.Session;
import com.app.util.Util;

@WebServlet(name = "ClienteServlet", urlPatterns = { "/cliente" })
public class ClienteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ClienteService clienteService = new ClienteService();
	private Cliente cliente;
	private String message;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		switch (action) {
		case "logout":
			logoutUsuario(request, response);
			break;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		switch (action) {
		case "add":
			addUsuario(request, response);
			break;

		case "login":
			loginUsuario(request, response);
			break;

		}
	}

	protected void addUsuario(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		cliente = new Cliente();
		cliente.setNombres(Util.toUTF8(request.getParameter("nombres")));
		cliente.setApellidos(Util.toUTF8(request.getParameter("apellidos")));
		cliente.setCorreo(Util.toUTF8(request.getParameter("correo")));
		cliente.setUsuario(Util.toUTF8(request.getParameter("usuario")));
		cliente.setContrasenia(Util.toUTF8(request.getParameter("contrasenia")));

		message = clienteService.agregar(cliente);
		request.setAttribute("message", message);
		Util.forward(request, response, "/login.jsp");
	}

	protected void loginUsuario(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String usuario=Util.toUTF8(request.getParameter("usuario"));
		String contrasenia=Util.toUTF8(request.getParameter("contrasenia"));
		String destino=null;
		
		cliente=clienteService.login(usuario, contrasenia);
		
		if(cliente==null){
			message="Usuario no registrado";
			request.setAttribute("message", message);
			destino="/login.jsp";
			
		}else{
			Session.put(request, "cliente", cliente);
			destino="/panel.jsp";
		}
		
		Util.forward(request, response, destino);

	}

	protected void logoutUsuario(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getSession().invalidate();
		Util.forward(request, response, "/login.jsp");
	}
}
