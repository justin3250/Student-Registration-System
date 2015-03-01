/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import org.eclipse.persistence.annotations.CascadeOnDelete;

/**
 *
 * @author Justin
 */
@Entity
@NamedQueries({
         @NamedQuery(name = "Course.findAll" , query = "select c from Course c")
})
@CascadeOnDelete
public class Course extends CommonEntity implements Serializable {
    
    
    private String courseName;
    private String courseDescription;

     @OneToMany(cascade = CascadeType.ALL,mappedBy = "course1")
     @CascadeOnDelete
     private List<StudentCourse> studentCourses = new ArrayList<>();
    
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "course")
    @CascadeOnDelete
    private List<TeacherCourse> teacherCourses = new ArrayList<>();
    
     

    /**
     *
     * @param courseName
     * @param courseDescription
     */
    public Course(String courseName, String courseDescription) {
        this.courseName = courseName;
        this.courseDescription = courseDescription;
    }

    /**
     *
     */
    public Course() {
    }

     
    /**
     *
     * @return
     */
    public List<StudentCourse> getStudentCourses() {
        return studentCourses;
    }

    /**
     *
     * @param studentCourses
     */
    public void setStudentCourses(List<StudentCourse> studentCourses) {
        this.studentCourses = studentCourses;
    }
     
    /**
     *
     * @return
     */
    public List<TeacherCourse> getTeacherCourses() {
        return teacherCourses;
    }

    /**
     *
     * @param teacherCourses
     */
    public void setTeacherCourses(List<TeacherCourse> teacherCourses) {
        this.teacherCourses = teacherCourses;
    }
    
 

    /**
     *
     * @return
     */
    public String getCourseName() {
        return courseName;
    }

    /**
     *
     * @param courseName
     */
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    /**
     *
     * @return
     */
    public String getCourseDescription() {
        return courseDescription;
    }

    /**
     *
     * @param courseDescription
     */
    public void setCourseDescription(String courseDescription) {
        this.courseDescription = courseDescription;
    }

    @Override
    public String toString() {
        return "Course{" + "id=" + id + ", courseName=" + courseName + ", courseDescription=" + courseDescription + '}';
    }
    
    
}
