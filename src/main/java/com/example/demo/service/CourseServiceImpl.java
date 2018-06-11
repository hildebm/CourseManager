package com.example.demo.service;

import com.example.demo.model.Course;
import com.example.demo.model.Instructor;
import com.example.demo.model.Student;
import com.example.demo.repository.CourseRepository;
import com.example.demo.repository.InstructorRepository;
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
    private final InstructorRepository instructorRepository;


    public CourseServiceImpl(CourseRepository courseRepository, StudentRepository studentRepository, InstructorRepository instructorRepository) {
        this.courseRepository = courseRepository;
        this.studentRepository = studentRepository;
        this.instructorRepository = instructorRepository;
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
        //currentCourse.setInstructor(course.getInstructor());
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

    @Override
    public void removeInstructor(Long instructorId, Long courseId){
        //betreffenden Instructor rausfiltern
        Optional<Instructor> instructorOptional = instructorRepository.findById(instructorId);
        if(!instructorOptional.isPresent()){
            throw new RuntimeException("Instructor not Found");
        }

        //betreffenden Kurs rausfiltern
        Optional<Course> courseOptional = courseRepository.findById(courseId);
        if (!courseOptional.isPresent()) {
            throw new RuntimeException("Course Not Found!");
        }

        Course course = courseOptional.get();
        Instructor instructor = instructorOptional.get();

        course.removeInstructor(instructor);
        instructor.removeCourse(course);

        courseRepository.save(course);
        instructorRepository.save(instructor);
    }

    @Override
    public void removeStudent(Long studentId, Long courseId){
        //betreffenden Student rausfiltern
        Optional<Student> studentOptional = studentRepository.findById(studentId);
        if(!studentOptional.isPresent()){
            throw new RuntimeException("Student not Found");
        }

        //betreffenden Kurs rausfiltern
        Optional<Course> courseOptional = courseRepository.findById(courseId);
        if (!courseOptional.isPresent()) {
            throw new RuntimeException("Course Not Found!");
        }

        Course course = courseOptional.get();
        Student student = studentOptional.get();

        course.removeStudent(student);
        student.removeCourse(course);

        courseRepository.save(course);
        studentRepository.save(student);
    }

}
