package com.app.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.app.dominio.Habitacion;
import com.app.service.HabitacionService;
import com.app.util.Session;
import com.app.util.Util;

@WebServlet(name = "HabitacionServlet", urlPatterns = { "/habitacion" })
public class HabitacionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private HabitacionService habitacionService = new HabitacionService();
	private Habitacion habitacion;
	private String message;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		switch (action) {
		case "mostrarporid":
			mostrarporid(request, response);
			break;
		case "editarporid":
			editarporid(request, response);
		case "agregarnuevo":
			agregarnuevo(request, response);
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

	protected void mostrarporid(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		habitacion = new Habitacion();
		habitacion.setNombres(Util.toUTF8(request.getParameter("nombres")));
		habitacion.setApellidos(Util.toUTF8(request.getParameter("apellidos")));
		habitacion.setCorreo(Util.toUTF8(request.getParameter("correo")));
		habitacion.setUsuario(Util.toUTF8(request.getParameter("usuario")));
		habitacion.setContrasenia(Util.toUTF8(request.getParameter("contrasenia")));

		message = habitacionService.agregar(habitacion);
		request.setAttribute("message", message);
		Util.forward(request, response, "/login.jsp");
	}

	protected void editarporid(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String usuario=Util.toUTF8(request.getParameter("usuario"));
		String contrasenia=Util.toUTF8(request.getParameter("contrasenia"));
		String destino=null;
		
		habitacion=habitacionService.login(usuario, contrasenia);
		
		if(habitacion==null){
			message="Usuario no registrado";
			request.setAttribute("message", message);
			destino="/login.jsp";
			
		}else{
			Session.put(request, "habitacion", habitacion);
			destino="/panel.jsp";
		}
		
		Util.forward(request, response, destino);

	}

	protected void agregarnuevo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getSession().invalidate();
		Util.forward(request, response, "/login.jsp");
	}
}
