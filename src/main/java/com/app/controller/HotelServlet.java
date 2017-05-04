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
		
		if (action != null)
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
					
			} 
		else {
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
		lista = hotelService.listarTodos();
	}
	
	protected void listartodos(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		recargarlista();

		request.setAttribute("list", lista);
		Util.forward(request, response, "/Hotel/listar.jsp");
	}

	protected void mostrarporid(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		recargarlista();
		hotel = new Hotel();

		int hotel_id = Integer.parseInt(request.getParameter("hotel_id"));
		
		for(Hotel h : lista) {
	        if(h.getId() == hotel_id) {
	            hotel =  h;
	            break;
	        }
	    }		

		request.setAttribute("i", hotel);
		Util.forward(request, response, "/Hotel/verporid.jsp");
	}

	protected void editarporid(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		recargarlista();
		
		int hotel_id = Integer.parseInt(request.getParameter("hotel_id"));
		hotel = new Hotel();
		
		hotel.setId(hotel_id);
		hotel.setNombre(Util.toUTF8(request.getParameter("nombre")));
		hotel.setCalificacion(Integer.parseInt(Util.toUTF8(request.getParameter("calificacion"))));
		hotel.setDireccion(Util.toUTF8(request.getParameter("direccion")));
		
		Util.forward(request, response, "/Hotel/listar.jsp");

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
