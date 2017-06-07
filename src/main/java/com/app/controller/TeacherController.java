package com.app.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.app.entity.Teacher;
import com.app.service.ITeacherService;

@Controller
@RequestMapping("/")
public class TeacherController {

	@Autowired
	private ITeacherService teacherService;
	
	
	@GetMapping("/teachers")
	public String findAll(Model model){
		model.addAttribute("teachers", teacherService.findAll());
		model.addAttribute("teacher",new Teacher());
		return "Teacher";
	}
	
	
	@PostMapping("/teachers")
	public String save(@Valid Teacher teacher, BindingResult result){
		if(result.hasErrors()){
			return "redirect:/teachers";
		}
		
		this.teacherService.save(teacher);
		
		return "redirect:/teachers";
	}
	
}
