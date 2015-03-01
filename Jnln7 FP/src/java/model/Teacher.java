/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import model.security.User;
import org.eclipse.persistence.annotations.CascadeOnDelete;

/**
 *
 * @author Justin
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Teacher.findAll" , query = "select t from Teacher t"),
    @NamedQuery(name = "Teacher.findByUsername" , query = "select t from Teacher t where t.user.userName = :userName")    
})
@CascadeOnDelete
public class Teacher extends CommonEntity implements Serializable {
   
private static final Logger LOG = Logger.getLogger(Teacher.class.getName());
    @OneToMany(fetch=FetchType.EAGER ,cascade = CascadeType.ALL,mappedBy = "teacher" )
    @CascadeOnDelete
    private List<TeacherCourse> teacherCourses = new ArrayList<>();
     
    @OneToOne
    @JoinColumn(name = "USERNAME")
    private User user;
    
     @ManyToOne
     @JoinColumn(nullable = false)
     private Address address;
    
    /**
     *
     * @param teachercourse
     */
    public void addTeacherCourse(TeacherCourse teachercourse) {
        if (!this.teacherCourses.contains(teachercourse)) {
            this.teacherCourses.add(teachercourse);
        }
       // if (!teachercourse.getTeacher().contains(this)) {
        //    teachercourse.getTeacher().add(this);
        //}
    }
    
    /**
     * Get the value of user
     *
     * @return the value of user
     */
    public User getUser() {
        return user;
    }

    /**
     * Set the value of user
     *
     * @param user new value of user
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     *
     */
    public Teacher() {
    }

    /**
     *
     * @param address
     * @param name
     */
    public Teacher(Address address, String name ) {
        this.address = address;
        this.name = name;
    }

     
     
    /**
     *
     * @return
     */
    public Address getAddress() {
        return address;
    }

    /**
     *
     * @param address
     */
    public void setAddress(Address address) {
        this.address = address;
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
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }
    
    private String name;

    //private int addressID;
    @Override
    public String toString() {
        return "Teacher{" + "teacherCourses=" + teacherCourses + ", user=" + user + ", address=" + address + ", name=" + name + '}';
    }

  

   
    
    
}
