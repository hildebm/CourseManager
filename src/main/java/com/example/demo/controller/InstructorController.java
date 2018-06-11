package com.example.demo.controller;

import com.example.demo.model.Course;
import com.example.demo.model.Instructor;
import com.example.demo.service.CourseService;
import com.example.demo.service.InstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class InstructorController {

    @Autowired
    private InstructorService instructorService;

    @Autowired
    private CourseService courseService;

    @RequestMapping("/instructors")
    public String index(Model model){
        List<Instructor> instructors = (List<Instructor>) instructorService.getAll();
        model.addAttribute("instructors" , instructors);
        return "instructors/instructors";
    }

    @RequestMapping(value = "/instructor/add")
    public String addInstructor(Model model)
    {
        model.addAttribute("instructor", new Instructor());
        return "instructors/addInstructor";
    }

    @RequestMapping(value = "/instructor/save", method = RequestMethod.POST)
    public String saveInstructor(Instructor instructor)
    {
        instructorService.create(instructor);
        return "redirect:/instructors";
    }

    /*edit a single instructor*/
    @GetMapping("/edit/instructor/{id}")
    public String editInstructor(@PathVariable(value = "id") Long id, Model model) {
        Instructor instructor = instructorService.findById(id);
        model.addAttribute("instructor", instructor);
        return "instructors/editInstructor";
    }

    /*---Update a instructor by id---*/
    @RequestMapping(path = "/instructor/{id}", method = RequestMethod.POST)
    public String updateInstructor(@PathVariable("id") long id, Instructor instructor) {
        instructorService.update(id, instructor);
        return "redirect:/instructors";
    }

    @RequestMapping(value = "/instructor/delete/{id}", method = RequestMethod.GET)
    public String deleteInstructor(@PathVariable("id") Long id, Model model)
    {
        instructorService.delete(id);
        return "redirect:/instructors";
    }

    @RequestMapping(value = "addInstructorForCourse/{id}", method = RequestMethod.GET)
    public String addCourse(@PathVariable("id") Long id, Model model){
        model.addAttribute("courses", courseService.getAll());
        model.addAttribute("instructor", instructorService.findById(id));
        return "instructors/addInstructorForCourse";
    }

    @RequestMapping(value="/instructor/{id}/courses", method=RequestMethod.GET)
    public String addInstructorForCourse(@PathVariable Long id, @RequestParam Long courseId, Model model) {
        Course course = courseService.findById(courseId);
        Instructor instructor = instructorService.findById(id);

        if (instructor != null) {
            if (!instructor.hasCourse(course)) {
                instructor.getCourses().add(course);
            }
            instructorService.create(instructor);
            model.addAttribute("instructor", instructorService.findById(id));
            model.addAttribute("courses", courseService.getAll());
            return "redirect:/instructors";
        }

        return "redirect:/instructors";
    }
}
