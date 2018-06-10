package com.example.demo.controller;

import com.example.demo.model.Course;
import com.example.demo.model.Student;
import com.example.demo.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class courseController {
    @Autowired
    private CourseService courseService;

    @RequestMapping("/courses")
    public String index(Model model)
    {
        List<Course> courses = (List<Course>) courseService.getAll();
        model.addAttribute("courses", courses);
        return "courses/courses";
    }

    @RequestMapping("/course/add")
    public String addCourse(Model model)
    {
        model.addAttribute("course", new Course());
        return "courses/addCourse";
    }

    @RequestMapping(value = "/course/save", method = RequestMethod.POST)
    public String saveCourse(Course course)
    {
        courseService.create(course);
        return "redirect:/courses";
    }
}
