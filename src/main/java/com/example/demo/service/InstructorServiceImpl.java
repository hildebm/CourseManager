package com.example.demo.service;

import com.example.demo.model.Course;
import com.example.demo.model.Instructor;
import com.example.demo.repository.CourseRepository;
import com.example.demo.repository.InstructorRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class InstructorServiceImpl implements InstructorService {

    private final CourseRepository courseRepository;
    private final InstructorRepository instructorRepository;

    public InstructorServiceImpl(CourseRepository courseRepository, InstructorRepository instructorRepository) {
        this.courseRepository = courseRepository;
        this.instructorRepository = instructorRepository;
    }

    @Override
    public List<Instructor> getAll(){
        List<Instructor> instructorList = new ArrayList<>();
        instructorRepository.findAll().iterator().forEachRemaining(instructorList::add);
        return instructorList;
    }

    @Override
    public Instructor findById(Long id){
        Optional<Instructor> instructorOptional =  instructorRepository.findById(id);

        if(!instructorOptional.isPresent()){
            throw new RuntimeException("Instructor not Found");
        }
        return instructorOptional.get();
    }

    @Override
    public Long create(Instructor instructor){
        instructorRepository.save(instructor);
        return instructor.getId();
    }

    @Override
    public void update(Long id, Instructor instructor){
        Instructor currentInstructor = findById(id);
        currentInstructor.setFirstName(instructor.getFirstName());
        currentInstructor.setLastName(instructor.getLastName());
        currentInstructor.setEmail(instructor.getEmail());
        instructorRepository.save(currentInstructor);
    }

    @Override
    public void delete(Long id){
        //remove Instructor from all Courses
        Optional<Instructor> instructorOptional = instructorRepository.findById(id);
        Instructor instructor = instructorOptional.get();

        Set<Course> courses = instructor.getCourses();

        for (Course course : courses)
        {
            course.removeInstructor(instructor);
            instructorRepository.save(instructor);
        }
    }
}
