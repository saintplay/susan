package com.susan.app.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.susan.app.entity.Hotel;
import com.susan.app.service.IHotelService;

@Controller
@RequestMapping("/hoteles")
public class HotelController {

	@Autowired
	private IHotelService hotelService;

	@GetMapping
	public String findAll(Model model) {
		model.addAttribute("hoteles", hotelService.findAll());
		return "listhotel";
	}

	@PostMapping
	public String save(@Valid Hotel hotel, BindingResult result) {
		if (result.hasErrors()) {
			return "redirect:/hoteles";
		}

		this.hotelService.save(hotel);

		return "redirect:/hoteles";
	}
}
