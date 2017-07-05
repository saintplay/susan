package com.susan.app.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.susan.app.entity.Habitacion;
import com.susan.app.entity.Hotel;
import com.susan.app.entity.Usuario;
import com.susan.app.service.IHabitacionService;
import com.susan.app.service.IHotelService;
import com.susan.app.service.UsuarioService;
import com.susan.app.utils.Security;
import com.susan.app.utils.Vista;

@Controller
@RequestMapping("/habitaciones")
public class HabitacionController {

	@Autowired
	private IHabitacionService habitacionService;
	@Autowired
	private IHotelService hotelService;
	@Autowired
	private UsuarioService usuarioService;
	
	String getHabitacionesData(Model model, Authentication authentication) throws JsonProcessingException {
		Iterable<Habitacion> habitaciones = null;
		
		User user = (User) SecurityContextHolder
				 .getContext().getAuthentication()
				 .getPrincipal();
		
		String username = user.getUsername();
		
		if (Security.tieneRol(authentication, "ROLE_ADMIN")) {
			habitaciones = habitacionService.findAll();
		}
		else if (Security.tieneRol(authentication, "ROLE_WORKER")) {
			Usuario usuario = usuarioService.findOne(username);
			Hotel hotel = usuario.getHotel();
			habitaciones = habitacionService.findByHotel(hotel);
		}
		
		List<JsonNode> list_node = new ArrayList<JsonNode>();
		ObjectMapper mapper = new ObjectMapper();
		
		for (Habitacion habitacion : habitaciones) {
			JsonNode habitacion_node = mapper.createObjectNode();
			((ObjectNode) habitacion_node).put("habitacionid", habitacion.getId().toString());
			((ObjectNode) habitacion_node).put("hotelid", habitacion.getHotel().getId().toString());
			((ObjectNode) habitacion_node).put("nombrehotel", habitacion.getHotel().getNombre());
			((ObjectNode) habitacion_node).put("nombre", habitacion.getNombre());
			((ObjectNode) habitacion_node).put("piso", habitacion.getPiso());
			((ObjectNode) habitacion_node).put("numero", habitacion.getNumero());
			((ObjectNode) habitacion_node).put("descripcion", habitacion.getDescripcion());
			((ObjectNode) habitacion_node).put("foto", habitacion.getFoto());
			((ObjectNode) habitacion_node).put("precio", habitacion.getPrecio());
			list_node.add(habitacion_node);
		}

		return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(list_node);
	}
	
	@GetMapping
	public String findAll(Model model, Authentication authentication) throws JsonProcessingException {
		String habitaciones_json = getHabitacionesData(model, authentication);
		model.addAttribute("habitaciones", habitaciones_json);
		return Vista.enviar(model, authentication, "habitaciones");
	}
	
	@PostMapping("/save")
	public String saveReserva(HttpServletRequest request, Model model) throws ParseException {
		long habitacionid = Long.parseLong(request.getParameter("habitacionid"));
		long hotelid;
		String nombre = request.getParameter("nombre");
		int piso = Integer.parseInt(request.getParameter("piso"));
		String numero = request.getParameter("numero");
		String descripcion = request.getParameter("descripcion");
		String foto = request.getParameter("foto");
		float precio = Float.parseFloat(request.getParameter("precio"));
		
		Habitacion habitacion = null;

		if (habitacionid == -1) {
			habitacion = new Habitacion();
			hotelid = Long.parseLong(request.getParameter("hotelid"));
			habitacion.setHotel(hotelService.findOne(hotelid));
		}
		else {
			habitacion = habitacionService.findOne(habitacionid);
		}
		
		habitacion.setNombre(nombre);
		habitacion.setPiso(piso);
		habitacion.setNumero(numero);
		habitacion.setDescripcion(descripcion);
		habitacion.setFoto(foto);
		habitacion.setPrecio(precio);
		
		try {
			this.habitacionService.save(habitacion);
			return "redirect:/habitaciones";
		} catch (Exception e) {
			return "redirect:/habitaciones/new";
		}
	}

	@RequestMapping("/new")
	@GetMapping
	public String mostrarNuevo(Model model, Authentication authentication) {
		Iterable<Hotel> hoteles = null;
		
		User user = (User) SecurityContextHolder
				 .getContext().getAuthentication()
				 .getPrincipal();
		
		String username = user.getUsername();
		
		if (Security.tieneRol(authentication, "ROLE_ADMIN")) {
			hoteles = hotelService.findAll();
		}
		else if (Security.tieneRol(authentication, "ROLE_WORKER")) {
			Usuario usuario = usuarioService.findOne(username);
			long hotelid = usuario.getHotel().getId();

			hoteles = new ArrayList<Hotel>();
			((ArrayList<Hotel>) hoteles).add(hotelService.findOne(hotelid));
		}

		model.addAttribute("hoteles", hoteles);
		model.addAttribute("habitacionid", -1);
		model.addAttribute("foto", "http://lorempixel.com/output/city-q-c-400-400-1.jpg");
		return Vista.enviar(model, authentication, "habitacionadmin");
	}
	
	@GetMapping("/editar/{habitacionid}")
	public String editarHabitacion(@PathVariable Long habitacionid, HttpServletRequest request, Model model, Authentication authentication) throws ParseException, JsonProcessingException {
		Habitacion habitacion = this.habitacionService.findOne(habitacionid);
		model.addAttribute("habitacionid", habitacion.getId());
		model.addAttribute("hotelid", habitacion.getHotel().getId().toString());;
		model.addAttribute("nombre", habitacion.getNombre());
		model.addAttribute("piso", habitacion.getPiso());
		model.addAttribute("numero", habitacion.getNumero());
		model.addAttribute("descripcion", habitacion.getDescripcion());
		model.addAttribute("foto", habitacion.getFoto());
		model.addAttribute("precio", habitacion.getPrecio());

		return Vista.enviar(model, authentication, "habitacionadmin");
	}
}
