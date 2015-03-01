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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import org.eclipse.persistence.annotations.CascadeOnDelete;

/**
 *
 * @author Justin
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "StudentCourse.findAll" , query = "select sc from StudentCourse sc"),
    @NamedQuery(name = "StudentCourse.findByUsername" , query = "select sc from StudentCourse sc where sc.student1.user.userName = :userName")    
})
@CascadeOnDelete
public class StudentCourse extends CommonEntity implements Serializable {
   
    
     private String homeWork1;
    private String homeWork2;
    private String homeWork3;
    private String homeWork4;
    private String finalHomeWork;
    
    @ManyToOne
    @JoinColumn(nullable = false)
    private Student student1;
    
    @ManyToOne
     @JoinColumn(nullable = false)
    private Course course1;

    /**
     *
     */
    public StudentCourse() {
    }

    /**
     *
     * @param homeWork1
     * @param homeWork2
     * @param homeWork3
     * @param homeWork4
     * @param finalHomeWork
     * @param student
     * @param course
     */
    public StudentCourse(String homeWork1, String homeWork2, String homeWork3, String homeWork4, String finalHomeWork, Student student, Course course) {
        this.homeWork1 = homeWork1;
        this.homeWork2 = homeWork2;
        this.homeWork3 = homeWork3;
        this.homeWork4 = homeWork4;
        this.finalHomeWork = finalHomeWork;
        this.student1 = student;
        this.course1 = course;
    }

    
    
    /**
     *
     * @return
     */
    public Course getCourse() {
        return course1;
    }

    /**
     *
     * @param course
     */
    public void setCourse(Course course) {
        this.course1 = course;
    }

    /**
     *
     * @return
     */
    public Student getStudent() {
        return student1;
    }

    /**
     *
     * @param student
     */
    public void setStudent(Student student) {
        this.student1 = student;
    }
   
    /**
     * Get the value of finalHomeWork
     *
     * @return the value of finalHomeWork
     */
    public String getFinalHomeWork() {
        return finalHomeWork;
    }

    /**
     * Set the value of finalHomeWork
     *
     * @param finalHomeWork new value of finalHomeWork
     */
    public void setFinalHomeWork(String finalHomeWork) {
        this.finalHomeWork = finalHomeWork;
    }


    /**
     * Get the value of homeWork4
     *
     * @return the value of homeWork4
     */
    public String getHomeWork4() {
        return homeWork4;
    }

    /**
     * Set the value of homeWork4
     *
     * @param homeWork4 new value of homeWork4
     */
    public void setHomeWork4(String homeWork4) {
        this.homeWork4 = homeWork4;
    }


    /**
     * Get the value of homeWork3
     *
     * @return the value of homeWork3
     */
    public String getHomeWork3() {
        return homeWork3;
    }

    /**
     * Set the value of homeWork3
     *
     * @param homeWork3 new value of homeWork3
     */
    public void setHomeWork3(String homeWork3) {
        this.homeWork3 = homeWork3;
    }


    /**
     * Get the value of homeWork2
     *
     * @return the value of homeWork2
     */
    public String getHomeWork2() {
        return homeWork2;
    }

    /**
     * Set the value of homeWork2
     *
     * @param homeWork2 new value of homeWork2
     */
    public void setHomeWork2(String homeWork2) {
        this.homeWork2 = homeWork2;
    }


    /**
     * Get the value of homeWork1
     *
     * @return the value of homeWork1
     */
    public String getHomeWork1() {
        return homeWork1;
    }

    /**
     * Set the value of homeWork1
     *
     * @param homeWork1 new value of homeWork1
     */
    public void setHomeWork1(String homeWork1) {
        this.homeWork1 = homeWork1;
    }

  

    @Override
    public String toString() {
        return "StudentCourse{" + "id=" + id + ", homeWork1=" + homeWork1 + ", homeWork2=" + homeWork2 + ", homeWork3=" + homeWork3 + ", homeWork4=" + homeWork4 + ", finalHomeWork=" + finalHomeWork + ", student=" + student1 + ", course=" + course1 +'}';
    }

    
}
