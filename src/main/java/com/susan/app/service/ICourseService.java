package com.susan.app.service;



import java.util.List;

import com.susan.app.entity.Course;

public interface ICourseService {
	public Iterable<Course> findAll();

	public void save(Course course);

	public Course findOne(Long id);

	public void delete(Long id);
	
	public List<Course> findByCourseTeacher(String nameTeacher);

}
