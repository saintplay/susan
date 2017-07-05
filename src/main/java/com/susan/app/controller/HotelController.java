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
import com.susan.app.entity.Hotel;
import com.susan.app.entity.Usuario;
import com.susan.app.service.IHotelService;
import com.susan.app.service.UsuarioService;
import com.susan.app.utils.Security;
import com.susan.app.utils.Vista;

@Controller
@RequestMapping("/hoteles")
public class HotelController {

	@Autowired
	private IHotelService hotelService;
	@Autowired
	private UsuarioService usuarioService;
	
	String getHotelesData(Model model, Authentication authentication) throws JsonProcessingException {
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

		List<JsonNode> list_node = new ArrayList<JsonNode>();
		ObjectMapper mapper = new ObjectMapper();
		
		for (Hotel hotel : hoteles) {
			JsonNode hotel_node = mapper.createObjectNode();
			((ObjectNode) hotel_node).put("hotelid", hotel.getId().toString());
			((ObjectNode) hotel_node).put("nombre", hotel.getNombre());
			((ObjectNode) hotel_node).put("direccion", hotel.getDireccion());
			((ObjectNode) hotel_node).put("calificacion", hotel.getCalificacion());
			list_node.add(hotel_node);
		}

		return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(list_node);
	}
	
	@GetMapping
	public String findAll(Model model, Authentication authentication) throws JsonProcessingException {
		String hoteles_json = getHotelesData(model, authentication);
		model.addAttribute("hoteles", hoteles_json);
		return Vista.enviar(model, authentication, "hoteles");
	}
	
	@PostMapping("/save")
	public String saveReserva(HttpServletRequest request, Model model) throws ParseException {
		long hotelid = Long.parseLong(request.getParameter("hotelid"));
		String nombre = request.getParameter("nombre");
		String direccion = request.getParameter("direccion");
		int calificacion = Integer.parseInt(request.getParameter("calificacion"));
		
		Hotel hotel = null;

		if (hotelid == -1) {
			hotel = new Hotel();
		}
		else {
			hotel = hotelService.findOne(hotelid);
		}
		
		hotel.setNombre(nombre);
		hotel.setDireccion(direccion);
		hotel.setCalificacion(calificacion);
		
		try {
			this.hotelService.save(hotel);
			return "redirect:/hoteles";
		} catch (Exception e) {
			return "redirect:/hoteles/new";
		}
	}

	@RequestMapping("/new")
	@GetMapping
	public String mostrarNuevo(Model model, Authentication authentication) {
		model.addAttribute("calificacion", 3);
		model.addAttribute("hotelid", -1);
		return Vista.enviar(model, authentication, "hoteladmin");
	}
	
	@GetMapping("/editar/{hotelid}")
	public String editarHotel(@PathVariable Long hotelid, HttpServletRequest request, Model model, Authentication authentication) throws ParseException, JsonProcessingException {
		Hotel hotel = this.hotelService.findOne(hotelid);

		model.addAttribute("hotelid", hotel.getId());
		model.addAttribute("nombre", hotel.getNombre());
		model.addAttribute("direccion", hotel.getDireccion());
		model.addAttribute("calificacion", hotel.getCalificacion());

		return Vista.enviar(model, authentication, "hoteladmin");
	}
}
