package com.susan.app.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
	public String save(@Valid Hotel hotel, BindingResult result, Model model) {
		try {
			if (result.hasErrors()) {
				model.addAttribute("message", result.toString());
				return "newhotel";
			}

			this.hotelService.save(hotel);
			return "redirect:/hoteles";
		} catch (Exception e) {
			model.addAttribute("message", result.toString());
			return "error";
		}
	}
	
	@PostMapping("/search")
	public String findAllHotelName(@RequestParam(name = "texto") String texto, Model model) {
		if (texto.equals("") || texto.isEmpty()) {
			model.addAttribute("hoteles", hotelService.findAll());
		} else {
			model.addAttribute("hoteles", hotelService.findByNombreContaining(texto));
			
		}
		return "listhotel";
	}
	
	@PostMapping("/new")
	public String newTeacher(Model model) {

		model.addAttribute("hotel", new Hotel());
		return "newhotel";
	}
	
	@GetMapping("/delete/{id}")
	public void delete (Long id){
		this.hotelService.delete(id);
	}
	
	@GetMapping("/id/{id}")
	public String findOne(@PathVariable Long id, Model model) {
		model.addAttribute("hotel", hotelService.findOne(id));
		return "newhotel";
	}
}
