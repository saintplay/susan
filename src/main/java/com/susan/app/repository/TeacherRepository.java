package com.susan.app.repository;




import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.susan.app.entity.Teacher;

@Transactional
public interface TeacherRepository extends CrudRepository<Teacher, Long>{

	//List<Teacher> findByNameContaining(String name);
	Teacher findByName(String name);
}