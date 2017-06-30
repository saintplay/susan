package com.susan.app.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.susan.app.entity.Hotel;
import com.susan.app.entity.Reserva;
import com.susan.app.service.IHotelService;
import com.susan.app.service.IReservaService;
import com.susan.app.utils.Security;

@Controller
public class ReservaController {
	
	@Autowired
	private IReservaService reservaService;
	@Autowired
	private IHotelService hotelService;

	@RequestMapping("/reservas")
	@GetMapping
	public String reservas(Model model, Authentication authentication) {
		if (Security.tieneRol(authentication, "ROLE_USER")) {
			return "redirect:/reservar";
		}

		User user = (User) SecurityContextHolder
				 .getContext().getAuthentication()
				 .getPrincipal();
		model.addAttribute("username", user.getUsername());
		
		Iterable<Reserva> reservas = reservaService.findAll();
		model.addAttribute("reservas", reservas);
		
		return "reservas";
	}
	
	@RequestMapping("/reservar")
	@GetMapping
	public String reservar(Model model) {
		Iterable<Hotel> hoteles = hotelService.findAll();
		model.addAttribute("hoteles", hoteles);

		return "reservar";
	}
	
}