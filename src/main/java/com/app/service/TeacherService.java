package com.app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.app.entity.Teacher;

@Service
public class TeacherService implements ITeacherService {

	private static final List<Teacher> LIST_TEACHER=new  ArrayList<>();
	
	static{
		LIST_TEACHER.add(new Teacher(1L, "Henry Mendoza Puerta"));
		LIST_TEACHER.add(new Teacher(2L, "Patricia Plasencia Burgos"));
	}
	
	@Override
	public List<Teacher> findAll() {
		// TODO Auto-generated method stub
		return TeacherService.LIST_TEACHER;
	}

	@Override
	public void save(Teacher teacher) {
		// TODO Auto-generated method stub
		TeacherService.LIST_TEACHER.add(teacher);
	}

}
