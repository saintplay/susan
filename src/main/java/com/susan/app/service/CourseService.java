package com.susan.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.susan.app.entity.Course;
import com.susan.app.repository.CourseRepository;

@Service
public class CourseService implements ICourseService {
	
	@Autowired
	private CourseRepository courseRepository;

	@Override
	public Iterable<Course> findAll() {
		// TODO Auto-generated method stub
		return courseRepository.findAll();
	}

	@Override
	public void save(Course course) {
		// TODO Auto-generated method stub
		courseRepository.save(course);
	}

	@Override
	public Course findOne(Long id) {
		// TODO Auto-generated method stub
		return courseRepository.findOne(id);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		courseRepository.delete(id);
	}

	@Override
	public List<Course> findByCourseTeacher(String nameTeacher) {
		// TODO Auto-generated method stub
		return courseRepository.findByCourseTeacher(nameTeacher);
	}

}
