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

import com.susan.app.entity.Teacher;
import com.susan.app.service.ITeacherService;

@Controller
@RequestMapping("/teachers")
public class TeacherController {

	@Autowired
	private ITeacherService teacherService;

	@GetMapping
	public String findAll(Model model) {
		model.addAttribute("teachers", teacherService.findAll());
		// model.addAttribute("teacher",new Teacher());
		return "listteacher";
	}

	@PostMapping("/search")
	public String findAllTeacherName(@RequestParam(name = "texto") String texto, Model model) {
		if (texto.equals("") || texto.isEmpty()) {
			model.addAttribute("teachers", teacherService.findAll());
			// model.addAttribute("teacher",new Teacher());
		} else {
			model.addAttribute("teachers", teacherService.findByName(texto));
			
		}
		return "listteacher";
	}

	@PostMapping("/new")
	public String newTeacher(Model model) {

		model.addAttribute("teacher", new Teacher());
		return "newteacher";
	}

	@PostMapping
	public String save(@Valid Teacher teacher, BindingResult result, Model model) {
		try {
			if (result.hasErrors()) {
				// return "redirect:/teachers";
				model.addAttribute("message", result.toString());
				return "newteacher";
			}

			this.teacherService.save(teacher);
			return "redirect:/teachers";

		} catch (Exception e) {
			// TODO: handle exception
			model.addAttribute("message", result.toString());
			return "error";
		}

	}

	@GetMapping("/{id}")
	public String findOne(@PathVariable Long id, Model model) {
		// model.addAttribute("teachers",teacherService.findAll());
		model.addAttribute("teacher", teacherService.findOne(id));
		return "newteacher";
	}
}
