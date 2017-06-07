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

import com.susan.app.entity.Servicio;
import com.susan.app.service.IServicioService;

@Controller
@RequestMapping("/servicios")
public class ServicioController {

  @Autowired
  private IServicioService servicioService;
  
  @GetMapping
  public String findAll(Model model){
    model.addAttribute("servicios",servicioService.findAll());
    return "listservicio";
  }
  
  @PostMapping
  public String save(@Valid Servicio servicio, BindingResult result, Model model) {try {
  		if (result.hasErrors()) {
  			model.addAttribute("message", result.toString());
  			return "newservicio";
  		}

  		this.servicioService.save(servicio);
  		return "redirect:/servicios";
  	} catch (Exception e) {
  		model.addAttribute("message", result.toString());
  		return "error";
  	}
  }
  
  @PostMapping("/search")
  public String findAllServicioName(@RequestParam(name = "texto") String texto, Model model) {
  	if (texto.equals("") || texto.isEmpty()) {
  		model.addAttribute("servicios", servicioService.findAll());
  	} else {
  		model.addAttribute("servicios", servicioService.findByNombreContaining(texto));
  		
  	}
  	return "listservicio";
  }

  @PostMapping("/new")
  public String newTeacher(Model model) {

  	model.addAttribute("servicio", new Servicio());
  	return "newservicio";
  }

  @GetMapping("/delete/{id}")
  public void delete (Long id){
  	this.servicioService.delete(id);
  }

  @GetMapping("/id/{id}")
  public String findOne(@PathVariable Long id, Model model) {
  	model.addAttribute("servicio", servicioService.findOne(id));
  	return "newservicio";
  }
 
}