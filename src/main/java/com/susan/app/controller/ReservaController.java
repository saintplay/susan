package com.susan.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.susan.app.entity.Reserva;
import com.susan.app.service.IReservaService;

@Controller
public class ReservaController {
	
	@Autowired
	private IReservaService reservaService;

	@RequestMapping("/reservas")
	@GetMapping
	public String reservas(Model model) {
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
		return "reservar";
	}
	
}