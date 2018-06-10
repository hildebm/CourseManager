package com.example.demo.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Instructor {
    private long id;
    private String firstName;
    private String lastName;
    private String email;
    private Set<Course> courses = new HashSet<Course>(0);

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    public Instructor(){}

    public Instructor(long id, String firstName, String lastName, String email, Set<Course> courses) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.courses = courses;
    }

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(name = "firstname")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name = "lastname")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "student_course", joinColumns = { @JoinColumn(name = "id") }, inverseJoinColumns = { @JoinColumn(name = "courseid") })
    public Set<Course> getCourses() {
        return courses;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }

    public boolean hasCourse(Course course) {
        for (Course instructorCourse: getCourses()) {
            if (instructorCourse.getCourseid() == course.getCourseid()) {
                return true;
            }
        }
        return false;
    }

    public void removeCourse(Course course)
    {
        if(hasCourse(course)){
            this.courses.remove(course);
            log.info("removed course: " + course.getName());
        }
    }
}
