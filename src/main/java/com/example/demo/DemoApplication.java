package com.example.demo;

import com.example.demo.model.Course;
import com.example.demo.model.Student;
import com.example.demo.service.CourseService;
import com.example.demo.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class DemoApplication {

	//private static final Logger log = LoggerFactory.getLogger(DemoApplication.class);

	public static void main(String[] args)
	{
		SpringApplication.run(DemoApplication.class, args);
	}

	/*
	@Bean
	public CommandLineRunner demo(StudentService studentService, CourseService courseService) {
		return (args) -> {
			// save students
			Student student1 = new Student("John", "Johnson", "IT", "john@john.com");
			studentService.create(new Student("Steve", "Stevens", "IT", "steve.stevent@st.com"));
			studentService.create(new Student("Mary", "Robinson", "IT", "mary@robinson.com"));
			studentService.create(new Student("Kate", "Keystone", "Nursery","kate@kate.com"));
			studentService.create(new Student("Diana", "Doll", "Business","diana@doll.com"));

			Course course1 = new Course("Programming Java");
			Course course2 = new Course("Spring Boot basics");
			courseService.create(new Course("Marketing 1"));
			courseService.create(new Course("Marketing 2"));

			courseService.create(course1);
			courseService.create(course2);

			Set<Course> courses = new HashSet<Course>();
			courses.add(course1);
			courses.add(course2);

			student1.setCourses(courses);
			studentService.create(student1);
		};
	}*/
}
