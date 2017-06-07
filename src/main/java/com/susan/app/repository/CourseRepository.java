package com.susan.app.repository;


import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.susan.app.entity.Course;

@Transactional
public interface CourseRepository extends CrudRepository<Course, Long> {

	@Query("select c from Course c where c.teacher.name  like ?1% or c.name like ?1%")
	List<Course> findByCourseTeacher(String nameTeacher);
}
