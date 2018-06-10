package com.example.demo.service;

import com.example.demo.model.Student;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StudentService {


    List<Student> getAll();

    Student findById(Long id);

    void update(Long id, Student student);

    void delete(Long id);

    Long create(Student student);

    void removeCourse(Long studentId, Long courseId);
}
