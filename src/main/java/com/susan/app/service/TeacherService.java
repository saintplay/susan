package com.susan.app.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.susan.app.entity.Teacher;
import com.susan.app.repository.TeacherRepository;

@Service
public class TeacherService implements ITeacherService {

	@Autowired
	private TeacherRepository teacherRepository;
	
	@Override
	public Iterable<Teacher> findAll() {
		// TODO Auto-generated method stub
		return teacherRepository.findAll();
	}

	@Override
	public void save(Teacher teacher) {
		// TODO Auto-generated method stub
		teacherRepository.save(teacher);
	}

	@Override
	public Teacher findOne(Long id) {
		// TODO Auto-generated method stub
		return teacherRepository.findOne(id);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		teacherRepository.delete(id);
	}

	@Override
	public Teacher findByName(String name) {
		// TODO Auto-generated method stub
		return teacherRepository.findByName(name);
	}

}
