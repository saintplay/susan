package com.susan.app.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.susan.app.entity.Habitacion;
import com.susan.app.entity.Hotel;
import com.susan.app.entity.Reserva;
import com.susan.app.entity.Servicio;
import com.susan.app.entity.Usuario;
import com.susan.app.service.HabitacionService;
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
	@Autowired
	private HabitacionService habitacionService;
	
	long getDifferenceDays(Date d1, Date d2) {
	    long diff = d2.getTime() - d1.getTime();
	    return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
	}
	
	String getReservasData(Model model, Authentication authentication) throws JsonProcessingException {
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

		List<JsonNode> list_node = new ArrayList<JsonNode>();
		ObjectMapper mapper = new ObjectMapper();
		Date hoy = new Date();
		
		for (Reserva reserva : reservas) {
			JsonNode reserva_node = mapper.createObjectNode();
			((ObjectNode) reserva_node).put("reservaid", reserva.getId().toString());
			((ObjectNode) reserva_node).put("hotelname", reserva.getHabitacion().getHotel().getNombre());
			((ObjectNode) reserva_node).put("habitacionname", reserva.getHabitacion().getNombre());
			((ObjectNode) reserva_node).put("username", reserva.getUsuario().getUsername());
			((ObjectNode) reserva_node).put("fechadesde", reserva.getFechaDesde().toString());
			((ObjectNode) reserva_node).put("fechahasta", reserva.getFechaHasta().toString());
			((ObjectNode) reserva_node).put("fechareserva", reserva.getFechaReserva().toString());
			((ObjectNode) reserva_node).put("diasrestantes", getDifferenceDays(reserva.getFechaReserva(), hoy));
			((ObjectNode) reserva_node).put("estado", reserva.getEstado());
			((ObjectNode) reserva_node).put("costototal", reserva.getCostoTotal());
			list_node.add(reserva_node);
		}

		return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(list_node);
	}
	
	@RequestMapping("/reservas")
	@GetMapping
	public String reservas(Model model, Authentication authentication) throws JsonProcessingException {	
		String reservas_json = getReservasData(model, authentication);
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
		Iterable<Habitacion> habitaciones = habitacionService.findAll();
		model.addAttribute("habitaciones", habitaciones);

		return Vista.enviar(model, authentication, "reservar");
	}
	
	@RequestMapping("/reservar/new")
	@PostMapping
	public String saveReserva(HttpServletRequest request, Model model) throws ParseException {
		Reserva reserva = new Reserva();
		SimpleDateFormat datef = new SimpleDateFormat("yyyy-mm-dd");
		
		Long habitacionid = Long.parseLong(request.getParameter("habitacionid"));
		String usuarioid = request.getParameter("usuarioid");
		Date fechadesde = datef.parse(request.getParameter("fechadesde"));
		Date fechahasta = datef.parse(request.getParameter("fechahasta"));
		Date fechareserva = datef.parse(request.getParameter("fechareserva"));
		float costototal = Float.parseFloat(request.getParameter("costototal"));
		
		reserva.setHabitacion(habitacionService.findOne(habitacionid));
		reserva.setUsuario(usuarioService.findOne(usuarioid));
		reserva.setFechaDesde(fechadesde);
		reserva.setFechaHasta(fechahasta);
		reserva.setFechaReserva(fechareserva);
		reserva.setCostoTotal(costototal);
		
		try {
			this.reservaService.save(reserva);
			return "redirect:/reservas";
		} catch (Exception e) {
			return "redirect:/reservar";
		}
	}
	
	@RequestMapping(value="/reservas/marcarpagado", produces="application/json", method=RequestMethod.POST)
	public @ResponseBody JsonNode marcarPagado(HttpServletRequest request, Model model, Authentication authentication) throws ParseException, JsonProcessingException {
		Long reservaid = Long.parseLong(request.getParameter("reservaid"));
		Reserva reserva = this.reservaService.findOne(reservaid);
		reserva.setEstado("1");
		this.reservaService.save(reserva);

		String reservas_json = getReservasData(model, authentication);

		ObjectMapper mapper = new ObjectMapper();
		JsonNode response = mapper.createObjectNode();
		((ObjectNode) response).put("reservas", reservas_json);
		return response;
	}
	
}