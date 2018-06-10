package com.example.demo.service;

import com.example.demo.model.Course;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface CourseService {

    List<Course> getAll();


    Course findById(Long id);
 /*
    void update(Long id, Note note);

    void delete(Long id);*/

    Long create(Course course);
}