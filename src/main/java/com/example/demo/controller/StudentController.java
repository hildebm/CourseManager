package com.example.demo.controller;

import com.example.demo.model.Course;
import com.example.demo.model.Student;
import com.example.demo.service.CourseService;
import com.example.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private CourseService courseService;

    /* Index page for Students -> shows all DB entries from Table "students"*/
    @RequestMapping("/students")
    public String index(Model model)
    {
        List<Student> students = (List<Student>) studentService.getAll();
        model.addAttribute("students", students);
        return "students/students";
    }

    /*When hitting Button "add new Student"
    -> this route leads to template "addStudent.html" with Form for new Student*/
    @RequestMapping(value = "add")
    public String addStudent(Model model)
    {
        model.addAttribute("student", new Student());
        return "students/addStudent";
    }

    /*Completed Form for "new Student" in Template "addStudent.html"
    -> when hitting Button "save" leads here
    StudentService will create new Entry in Database
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public String save(Student student)
    {
        studentService.create(student);
        return "redirect:/students";
    }

    /*Delete*/
    /*When Deleting student-> deletes Student from all enrolled Courses, too*/
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteStudent(@PathVariable("id") Long studentId, Model model)
    {
        studentService.delete(studentId);
        return "redirect:/students";
    }

    /*leads to Template where user can add a course to a student's courseList*/
    /*{id} = studentId*/
    @RequestMapping(value = "addStudentCourse/{id}", method = RequestMethod.GET)
    public String addCourse(@PathVariable("id") Long studentId, Model model){
        model.addAttribute("courses", courseService.getAll());
        model.addAttribute("student", studentService.findById(studentId));
        return "students/addStudentCourse";
    }

    /*adds selected course with {id} = courseId to student's CourseList*/
    @RequestMapping(value="/student/{id}/courses", method=RequestMethod.GET)
    public String studentsAddCourse(@PathVariable Long id, @RequestParam Long courseId, Model model) {
        Course course = courseService.findById(courseId);
        Student student = studentService.findById(id);

        if (student != null) {
            if (!student.hasCourse(course)) {
                student.getCourses().add(course);
            }
            studentService.create(student);
            model.addAttribute("student", studentService.findById(id));
            model.addAttribute("courses", courseService.getAll());
            return "redirect:/students";
        }

        return "redirect:/students";
    }

    @RequestMapping(value = "getstudents", method = RequestMethod.GET)
    public @ResponseBody List<Student> getStudents()
    {
        return (List<Student>)studentService.getAll();
    }

    /*edit a single Student*/
    @GetMapping("/edit/student/{id}")
    public String editStudent(@PathVariable(value = "id") Long studentId, Model model) {
        Student student = studentService.findById(studentId);
        model.addAttribute("student", student);
        return "students/editStudent";
    }

    /*---Update a student by id---*/
    @RequestMapping(path = "/student/{id}", method = RequestMethod.POST)
    public String update(@PathVariable("id") long id, Student student) {
        studentService.update(id, student);
        //return "redirect:/edit/student/" + id;
        return "redirect:/students";
    }

    /*Remove a course from template:editStudent*/
    /*automatically removes Student from course's List, too*/
    @RequestMapping(path = "/student/{studentId}/removeCourse/{courseId}", method = RequestMethod.GET)
    public String removeCourse(@PathVariable(value = "studentId") long studentId,
                         @PathVariable(value = "courseId") long courseId,
                         Model model)
    {
        //removes Course with id=courseId from the student's courseList
        studentService.removeCourse(studentId, courseId);

        return "redirect:/edit/student/" + studentId;
    }
}
