package com.example.demo.service;
import com.example.demo.model.Course;
import com.example.demo.model.Student;
import com.example.demo.repository.CourseRepository;
import com.example.demo.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    public StudentServiceImpl(StudentRepository studentRepository, CourseRepository courseRepository) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }

    @Override
    public List<Student> getAll(){
        List<Student> studentList = new ArrayList<>();
        studentRepository.findAll().iterator().forEachRemaining(studentList::add);
        return studentList;
    }


    @Override
    public Student findById(Long id){
        Optional<Student> studentOptional = studentRepository.findById(id);

        if (!studentOptional.isPresent()) {
            throw new RuntimeException("Student Not Found!");
        }

        return studentOptional.get();

    }

    @Override
    public  void update(Long id, Student student){
        Student currentStudent = findById(id);
        currentStudent.setFirstName(student.getFirstName());
        currentStudent.setLastName(student.getLastName());
        currentStudent.setDepartment(student.getDepartment());
        currentStudent.setEmail(student.getEmail());
        studentRepository.save(currentStudent);
    }

    @Override
    public void delete(Long id){
        //remove Student from all Courses
        Optional<Student> studentOptional = studentRepository.findById(id);
        Student student = studentOptional.get();
        Set<Course> courses = student.getCourses();

        for (Course course : courses)
        {
            course.removeStudent(student);
            courseRepository.save(course);
        }

        //deleteStudent
        studentRepository.deleteById(id);
    }


    @Override
    public Long create(Student student){
        studentRepository.save(student);
        return student.getId();
    }

    @Override
        //removes Course with id=courseId from the student's courseList
    public void removeCourse(Long studentId, Long courseId){

        //betreffenden studenten rausfiltern
        Optional<Student> studentOptional = studentRepository.findById(studentId);
        if (!studentOptional.isPresent()) {
            throw new RuntimeException("Student Not Found!");
        }

        //betreffenden studenten rausfiltern
        Optional<Course> courseOptional = courseRepository.findById(courseId);
        if (!courseOptional.isPresent()) {
            throw new RuntimeException("Student Not Found!");
        }

        //Wenn ja, daann Course aus Kursliste des Studenten entfernen
        Student student = studentOptional.get();
        Course course = courseOptional.get();

        student.removeCourse(course);
        course.removeStudent(student);

        studentRepository.save(student);
        courseRepository.save(course);
    }
}
