package com.example.demo.repository;

import java.util.List;

import com.example.demo.model.Course;
import org.springframework.data.repository.CrudRepository;

public interface CourseRepository extends CrudRepository<Course, Long>  {

    List<Course> findByName(String name);
}

