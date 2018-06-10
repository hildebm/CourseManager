package com.example.demo.controller;

import com.example.demo.model.Course;
import com.example.demo.model.Student;
import com.example.demo.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    /*edit a single Course*/
    @GetMapping("/edit/course/{id}")
    public String editCourse(@PathVariable(value = "id") Long courseId, Model model) {
        Course course = courseService.findById(courseId);
        model.addAttribute("course", course);
        return "courses/editCourse";
    }

    /*---Update a course by id---*/
    @RequestMapping(path = "/course/{id}", method = RequestMethod.POST)
    public String updateCourse(@PathVariable("id") long id, Course course) {
        courseService.update(id, course);
        //return "redirect:/edit/course/" + id;
        return "redirect:/courses";
    }

    @RequestMapping(value = "/course/delete/{id}", method = RequestMethod.GET)
    public String deleteCourse(@PathVariable("id") Long courseId, Model model)
    {
        courseService.delete(courseId);
        return "redirect:/courses";
    }
}
