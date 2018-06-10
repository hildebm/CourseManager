package com.example.demo.service;

import com.example.demo.model.Course;
import com.example.demo.repository.CourseRepository;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImpl implements CourseService{

    private final CourseRepository courseRepository;


    public CourseServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public List<Course> getAll(){
        List<Course> courseList = new ArrayList<>();
        courseRepository.findAll().iterator().forEachRemaining(courseList::add);
        return courseList;
    }

    @Override
    public Course findById(Long id){
        Optional<Course> courseOptional = courseRepository.findById(id);

        if (!courseOptional.isPresent()) {
            throw new RuntimeException("Course Not Found!");
        }

        return courseOptional.get();

    }

    @Override
    public Long create(Course course){
        courseRepository.save(course);
        return course.getCourseid();
    }
}
