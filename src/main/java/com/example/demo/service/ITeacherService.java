package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Teacher;

public interface ITeacherService {
  public List<Teacher> findAll();
  
  public void save(Teacher teacher);
}
