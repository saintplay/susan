package com.app.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.app.dominio.Habitacion;
import com.app.dominio.Hotel;
import com.app.service.HabitacionService;
import com.app.service.HotelService;
import com.app.util.Util;

@WebServlet(name = "HabitacionServlet", urlPatterns = { "/habitacion" })
public class HabitacionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private HabitacionService habitacionService = new HabitacionService();
	private HotelService hotelService = new HotelService();
	private Habitacion habitacion;
	private List<Habitacion> lista;
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
		lista = habitacionService.listarTodos();
	}
	
	protected void listartodos(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		recargarlista();

		List<Hotel> hoteles = hotelService.listarTodos();
		
		request.setAttribute("list", lista);
		request.setAttribute("hoteles", hoteles);
		
		Util.forward(request, response, "/Habitacion/listar.jsp");
	}

	protected void mostrarporid(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		recargarlista();
		habitacion = new Habitacion();

		int habitacion_id = Integer.parseInt(request.getParameter("habitacion_id"));
		
		for(Habitacion h : lista) {
	        if(h.getId() == habitacion_id) {
	            habitacion =  h;
	            break;
	        }
	    }		

		request.setAttribute("i", habitacion);
		Util.forward(request, response, "/Habitacion/verporid.jsp");
	}

	protected void editarporid(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		recargarlista();
		
		int habitacion_id = Integer.parseInt(request.getParameter("habitacion_id"));
		habitacion = new Habitacion();
		
		habitacion.setId(habitacion_id);
		habitacion.setNombre(Util.toUTF8(request.getParameter("nombre")));
		habitacion.setPiso(Integer.parseInt(Util.toUTF8(request.getParameter("calificacion"))));
		habitacion.setTipo(Integer.parseInt(Util.toUTF8(request.getParameter("tipo"))));
		habitacion.setDescripcion(Util.toUTF8(request.getParameter("descripcion")));
		habitacion.setFoto(Util.toUTF8(request.getParameter("foto")));
		habitacion.setHotelId(Integer.parseInt(Util.toUTF8(request.getParameter("hotel_id"))));
		habitacion.setPrecio(Float.parseFloat(Util.toUTF8(request.getParameter("precio"))));
		
		Util.forward(request, response, "/Habitacion/listar.jsp");

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
