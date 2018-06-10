package com.example.demo.service;

import com.example.demo.model.Course;
import com.example.demo.model.Student;
import com.example.demo.repository.CourseRepository;
import com.example.demo.repository.StudentRepository;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class CourseServiceImpl implements CourseService{

    private final CourseRepository courseRepository;
    private final StudentRepository studentRepository;


    public CourseServiceImpl(CourseRepository courseRepository, StudentRepository studentRepository) {
        this.courseRepository = courseRepository;
        this.studentRepository = studentRepository;
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

    @Override
    public  void update(Long id, Course course){
        Course currentCourse = findById(id);
        currentCourse.setName(course.getName());
        currentCourse.setInstructor(course.getInstructor());
        //currentCourse.setStartDate(course.getStartDate());
        //currentCourse.setEndDate(course.getEndDate());
        courseRepository.save(currentCourse);
    }

    @Override
    public void delete(Long id){
        //remove Student from all Courses
        Optional<Course> courseOptional = courseRepository.findById(id);
        Course course = courseOptional.get();
        Set<Student> students = course.getStudents();

        for (Student student : students)
        {
            student.removeCourse(course);
            studentRepository.save(student);
        }

        //deleteStudent
        courseRepository.deleteById(id);
    }
}
