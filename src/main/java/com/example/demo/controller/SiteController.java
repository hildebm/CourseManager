package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SiteController {

    @RequestMapping({"/index", "/"})
    public String showIndex()
    {
        return "home/index";
    }

    @RequestMapping("/contact")
    public String showContactPage()
    {
        return "home/contact";
    }

    @RequestMapping("/announcements")
    public String showAnnouncementsPage()
    {
        return "home/announcements";
    }

    @RequestMapping("/profile")
    public String showProfilePage()
    {
        return "home/profile";
    }

    @RequestMapping("/course/content")
    public String showCourseContent()
    {
        return "home/showCourse";
    }

    @RequestMapping("/student/courses")
    public String showStudentsCourses()
    {
        return "home/studentCourses";
    }

    @RequestMapping("/openCourse")
    public String openCourse()
    {
        return "home/openCourse";
    }

    @RequestMapping("/course/newBlogEntry")
    public String newBlogEntry()
    {
        return "home/newBlogEntry";
    }

    @RequestMapping("/course/buyCourse")
    public String buyCourse()
    {
        return "home/buyCourse";
    }

    @RequestMapping("/impressium")
    public String showImpressiumPage()
    {
        return "home/impressium";
    }

    @RequestMapping("/adminControlPanel")
    public String adminControlPanel()
    {
        return "home/adminControlPanel";
    }

    @RequestMapping("/course/blogPosts")
    public String showAllCourseBlogPosts()
    {
        return "home/blogPosts";
    }

    @RequestMapping("/course/materials")
    public String showAllCourseMaterials()
    {
        return "home/showMaterials";
    }

    @RequestMapping("/course/timeTable")
    public String showCourseTimeTable()
    {
        return "home/timeTable";
    }

    @RequestMapping("/course/questionsAndAnswers")
    public String showCourseQuestionsAndAnswers()
    {
        return "home/questionsAndAnswers";
    }
}
