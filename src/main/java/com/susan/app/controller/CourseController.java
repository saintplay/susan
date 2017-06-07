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

import com.susan.app.entity.Course;
import com.susan.app.entity.Teacher;
import com.susan.app.service.ICourseService;
import com.susan.app.service.ITeacherService;

@Controller
@RequestMapping("/courses")
public class CourseController {

	@Autowired
	private ICourseService courseService;
	
	@Autowired
	private ITeacherService teacherService;
	
	@GetMapping
	public String findAll(Model model){
		model.addAttribute("courses", courseService.findAll());
		//model.addAttribute("teacher",new Teacher());
		return "listcourse";
	}
	
	
	@PostMapping("/new")
	public String newCourse(Model model){
		model.addAttribute("teachers",teacherService.findAll());
		model.addAttribute("course",new Course());
		return "newcourse";
	}
	
	@PostMapping
	public String save(@Valid Course course, BindingResult result,Model model){
		try {
			if(result.hasErrors()){
				//return "redirect:/teachers";
				model.addAttribute("message", result.toString());
				model.addAttribute("teachers",teacherService.findAll());
				//return "error";
				return "newcourse";
			}
			
			this.courseService.save(course);
			return "redirect:/courses";
			
		} catch (Exception e) {
			// TODO: handle exception
			model.addAttribute("message", result.toString());
			return "error";
		}
	}
	
	@GetMapping("/{id}")
	public String findOne(@PathVariable Long id,Model model){
		model.addAttribute("teachers",teacherService.findAll());
		model.addAttribute("course", courseService.findOne(id));
		return "newcourse";
	}
	
	@PostMapping("/search")
	public String findAllTeacherName(@RequestParam(name="texto") String texto,Model model){
		
		model.addAttribute("courses", courseService.findByCourseTeacher(texto));
		//model.addAttribute("teacher",new Teacher());
		return "listcourse";
	}
}
