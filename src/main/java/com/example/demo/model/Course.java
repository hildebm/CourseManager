package com.example.demo.model;


import java.util.Date;
import java.util.Set;
import com.example.demo.model.Student;
import javax.persistence.*;

@Entity
public class Course {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long courseid;

    @Column(name="coursename")
    private String name;

    private Date startDate;
    private Date endDate;
    //private String instructor;

    @ManyToMany(mappedBy = "courses")
    private Set<Student> students;


    @ManyToMany(mappedBy = "courses")
    private Set<Instructor> instructors;

    public Course() {
    }

    /*Constructor for @Beans*/
    public Course(String name) {
        this.name = name;
    }

    public Course(String name, Date startDate, Date endDate, Set<Student> students, Set<Instructor> instructors) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.students = students;
        this.instructors = instructors;
    }

    public long getCourseid() {
        return courseid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }

    public boolean hasStudent(Student student) {
        for (Student studentInCourse: getStudents()) {
            if (studentInCourse.getId() == student.getId()) {
                return true;
            }
        }
        return false;
    }

    public boolean hasInstructor(Instructor instructor){
        for(Instructor InstructorOfCourse: getInstructors()){
            if(InstructorOfCourse.getId() == instructor.getId()){
                return true;
            }
        }
        return false;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public void setCourseid(long courseid) {
        this.courseid = courseid;
    }

    public Set<Instructor> getInstructors() {
        return instructors;
    }

    public void setInstructor(Set<Instructor> instructors) {
        this.instructors = instructors;
    }

    public void removeStudent(Student student)
    {
        if(hasStudent(student)){
            this.students.remove(student);
        }
    }

    public void removeInstructor(Instructor instructor){
        if(hasInstructor(instructor)){
            this.instructors.remove(instructor);
        }
    }
}
