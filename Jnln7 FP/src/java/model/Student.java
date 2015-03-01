/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import model.security.User;
import org.eclipse.persistence.annotations.CascadeOnDelete;
// many students to one address (siblings)
// many students to many studentCourses
/**
 *
 * @author Justin
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Student.findAll" , query = "select s from Student s"),
    @NamedQuery(name = "Student.findByUsername" , query = "select s from Student s where s.user.userName = :userName")    
})
@CascadeOnDelete
public class Student extends CommonEntity implements Serializable {
 
    private String firstName;
    private String lastName;
    private String gender;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateOfBirth;
    private String contactNumber;
    
    @OneToMany(fetch=FetchType.EAGER ,cascade = CascadeType.ALL,orphanRemoval=true,mappedBy = "student1" )
    @CascadeOnDelete
    private List<StudentCourse> studentCourses = new ArrayList<>();
    
    @OneToOne
    @JoinColumn(name = "USERNAME")
    private User user;

    
    @ManyToOne
    @JoinColumn(nullable = false)
    private Address address;

    /**
     *
     */
    public Student() {
    }

    /**
     *
     * @param firstName
     * @param lastName
     * @param gender
     * @param dateOfBirth
     * @param contactNumber
     * @param address
     */
    public Student(String firstName, String lastName, String gender, Date dateOfBirth, String contactNumber, Address address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.contactNumber = contactNumber;
        this.address = address;
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
     * Get the value of address
     *
     * @return the value of address
     */
    public Address getAddress() {
        return address;
    }

    /**
     * Set the value of address
     *
     * @param address new value of address
     */
    public void setAddress(Address address) {
        this.address = address;
    }

    /**
     *
     * @param firstName
     * @param lastName
     * @param gender
     * @param dateOfBirth
     * @param contactNumber
     */
    public Student(String firstName, String lastName, String gender, Date dateOfBirth, String contactNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.contactNumber = contactNumber;
    }

    /**
     *
     * @return
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     *
     * @param firstName
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     *
     * @return
     */
    public String getLastName() {
        return lastName;
    }

    /**
     *
     * @param lastName
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     *
     * @return
     */
    public String getGender() {
        return gender;
    }

    /**
     *
     * @param gender
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     *
     * @return
     */
    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     *
     * @param dateOfBirth
     */
    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    /**
     *
     * @return
     */
    public String getContactNumber() {
        return contactNumber;
    }

    /**
     *
     * @param contactNumber
     */
    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

  

    @Override
    public String toString() {
        return "Student{" + "firstName=" + firstName + ", lastName=" + lastName + ", gender=" + gender + ", dateOfBirth=" + dateOfBirth + ", contactNumber=" + contactNumber + ", id=" + id + '}';
    }
    
    
    
}
