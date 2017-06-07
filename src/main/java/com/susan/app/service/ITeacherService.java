package com.susan.app.service;


import com.susan.app.entity.Teacher;

public interface ITeacherService {
	public Iterable<Teacher> findAll();

	public void save(Teacher teacher);

	public Teacher findOne(Long id);

	public void delete(Long id);
	
	//public Iterable<Teacher> findByNameContaining(String name);
	public Teacher findByName(String name);

}
