package com.susan.app.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.susan.app.entity.Hotel;
import com.susan.app.entity.Reserva;
import com.susan.app.entity.Servicio;
import com.susan.app.entity.Usuario;
import com.susan.app.service.IHotelService;
import com.susan.app.service.IReservaService;
import com.susan.app.service.IServicioService;
import com.susan.app.service.UsuarioService;
import com.susan.app.utils.Security;
import com.susan.app.utils.Vista;

@Controller
public class ReservaController {
	
	@Autowired
	private IReservaService reservaService;
	@Autowired
	private IHotelService hotelService;
	@Autowired
	private IServicioService servicioService;
	@Autowired
	private UsuarioService usuarioService;

	@RequestMapping("/reservas")
	@GetMapping
	public String reservas(Model model, Authentication authentication) throws JsonProcessingException {
		
		Iterable<Reserva> reservas = null;
		
		User user = (User) SecurityContextHolder
				 .getContext().getAuthentication()
				 .getPrincipal();
		
		if (Security.tieneRol(authentication, "ROLE_USER")) {
			Usuario usuario = usuarioService.findOne(user.getUsername());
			reservas = reservaService.findByUsuario(usuario);
		}
		else if (Security.tieneRol(authentication, "ROLE_ADMIN")) {
			reservas = reservaService.findAll();
		}
		
		ObjectMapper mapper = new ObjectMapper();
		List<JsonNode> list_node = new ArrayList<JsonNode>();

		for (Reserva reserva : reservas) {
			JsonNode reserva_node = mapper.createObjectNode();
			((ObjectNode) reserva_node).put("username", reserva.getUsuario().getUsername());
			((ObjectNode) reserva_node).put("fechadesde", reserva.getFechaDesde().toString());
			((ObjectNode) reserva_node).put("fechahasta", reserva.getFechaHasta().toString());
			((ObjectNode) reserva_node).put("fechareserva", reserva.getFechaReserva().toString());
			((ObjectNode) reserva_node).put("estado", reserva.getEstado());
			((ObjectNode) reserva_node).put("costototal", reserva.getCostoTotal());
			list_node.add(reserva_node);
		}

		String reservas_json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(list_node);
		model.addAttribute("reservas", reservas_json);
		
		return Vista.enviar(model, authentication, "reservas");
	}
	
	@RequestMapping("/reservar")
	@GetMapping
	public String reservar(Model model, Authentication authentication) {
		Iterable<Servicio> servicios = servicioService.findAll();
		model.addAttribute("servicios", servicios);
		Iterable<Hotel> hoteles = hotelService.findAll();
		model.addAttribute("hoteles", hoteles);

		return Vista.enviar(model, authentication, "reservar");
	}
	
	@RequestMapping("/reservar/new")
	@PostMapping
	public String saveReserva(Model model, Authentication authentication) {
		Iterable<Servicio> servicios = servicioService.findAll();
		model.addAttribute("servicios", servicios);
		Iterable<Hotel> hoteles = hotelService.findAll();
		model.addAttribute("hoteles", hoteles);

		return "redirect:/reservas";
	}
	
}