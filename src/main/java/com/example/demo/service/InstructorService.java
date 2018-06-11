package com.example.demo.service;

import com.example.demo.model.Instructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface InstructorService {

    List<Instructor> getAll();

    Instructor findById(Long id);

    void update(Long id, Instructor instructor);

    void delete(Long id);

    Long create(Instructor instructor);


}
