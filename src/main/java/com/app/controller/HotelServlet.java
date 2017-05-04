package com.app.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.app.dominio.Hotel;
import com.app.service.HotelService;
import com.app.util.Session;
import com.app.util.Util;

@WebServlet(name = "HotelServlet", urlPatterns = { "/hotel" })
public class HotelServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private HotelService hotelService = new HotelService();
	private Hotel hotel;
	private List<Hotel> lista;
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
				break;
			case "agregarnuevo":
				agregarnuevo(request, response);
				break;
			default:
				listartodos(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		switch (action) {
		case "guardar":
			guardar(request, response);
			break;

		case "actualizarporid":
			actualizarporid(request, response);
			break;

		}
	}
	
	protected void recargarlista() {
		lista = hotelService();
	}
	
	protected void listartodos(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
	}

	protected void mostrarporid(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		hotel = new Hotel();
		hotel.setNombres(Util.toUTF8(request.getParameter("nombres")));
		hotel.setApellidos(Util.toUTF8(request.getParameter("apellidos")));
		hotel.setCorreo(Util.toUTF8(request.getParameter("correo")));
		hotel.setUsuario(Util.toUTF8(request.getParameter("usuario")));
		hotel.setContrasenia(Util.toUTF8(request.getParameter("contrasenia")));

		message = hotelService.agregar(hotel);
		request.setAttribute("message", message);
		Util.forward(request, response, "/login.jsp");
	}

	protected void editarporid(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String usuario=Util.toUTF8(request.getParameter("usuario"));
		String contrasenia=Util.toUTF8(request.getParameter("contrasenia"));
		String destino=null;
		
		hotel=hotelService.login(usuario, contrasenia);
		
		if(hotel==null){
			message="Usuario no registrado";
			request.setAttribute("message", message);
			destino="/login.jsp";
			
		}else{
			Session.put(request, "hotel", hotel);
			destino="/panel.jsp";
		}
		
		Util.forward(request, response, destino);

	}

	protected void agregarnuevo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getSession().invalidate();
		Util.forward(request, response, "/login.jsp");
	}
	
	protected void guardar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getSession().invalidate();
		Util.forward(request, response, "/login.jsp");
	}
	
	protected void actualizarporid(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getSession().invalidate();
		Util.forward(request, response, "/login.jsp");
	}
}
