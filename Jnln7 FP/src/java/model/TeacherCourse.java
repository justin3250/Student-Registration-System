/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import org.eclipse.persistence.annotations.CascadeOnDelete;

/**
 *
 * @author Justin
 */
@Entity
@NamedQuery(name = "TeacherCourse.findAll" , query = "select tc from TeacherCourse tc")
@CascadeOnDelete
public class TeacherCourse extends CommonEntity implements Serializable {
    
    @ManyToOne
    @JoinColumn(nullable = false)
    private Teacher teacher;
    
     
     @ManyToOne
     @JoinColumn(nullable = false)
    private Course course;

    /**
     *
     */
    public TeacherCourse() {
    }

    /**
     *
     * @param teacher
     * @param course
     */
    public TeacherCourse(Teacher teacher, Course course) {
        this.teacher = teacher;
        this.course = course;
    }

     
    /**
     *
     * @return
     */
    public Course getCourse() {
        return course;
    }

    /**
     *
     * @param course
     */
    public void setCourse(Course course) {
        this.course = course;
    }
     
    /**
     *
     * @return
     */
    public Teacher getTeacher() {
        return teacher;
    }

    /**
     *
     * @param teacher
     */
    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    @Override
    public String toString() {
        return "TeacherCourse{" + "id=" + id + ", teacher=" + teacher + ", course=" + course + '}';
    }
 
    
}
