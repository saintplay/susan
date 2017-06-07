package com.app.service;

import java.util.List;

import com.app.entity.Teacher;

public interface ITeacherService {
  public List<Teacher> findAll();
  
  public void save(Teacher teacher);
}
