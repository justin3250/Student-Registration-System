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
    @NamedQuery(name = "Address.findAll" , query = "select a from Address a")
})
public class Address extends CommonEntity implements Serializable {
    
//    @OneToMany(cascade = CascadeType.ALL ,mappedBy = "address" )
//    private List<Student> students = new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "address")
    @CascadeOnDelete
    private List<Student> students = new ArrayList<>();
    
//    @OneToMany(cascade = CascadeType.ALL , mappedBy = "address" )
//    private List<Teacher> teachers = new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "address")
    @CascadeOnDelete
    private List<Teacher> teachers = new ArrayList<>();
    
    private String address1;
    private String address2;
    private String addressCity;
    private String addressState;
    private String addressZipCode;
    private String addressCountry;

    /**
     *
     * @param address1
     * @param address2
     * @param addressCity
     * @param addressState
     * @param addressZipCode
     * @param addressCountry
     */
    public Address(String address1, String address2, String addressCity, String addressState, String addressZipCode, String addressCountry) {
        this.address1 = address1;
        this.address2 = address2;
        this.addressCity = addressCity;
        this.addressState = addressState;
        this.addressZipCode = addressZipCode;
        this.addressCountry = addressCountry;
    }

    /**
     *
     */
    public Address() {
    }

    
    
    /**
     *
     * @return
     */
    public List<Teacher> getTeachers() {
        return teachers;
    }

    /**
     *
     * @param teachers
     */
    public void setTeachers(List<Teacher> teachers) {
        this.teachers = teachers;
    }

    
        /**
     * Get the value of students
     *
     * @return the value of students
     */
    public List<Student> getStudents() {
        return students;
    }

    /**
     * Set the value of students
     *
     * @param students new value of students
     */
    public void setStudents(List<Student> students) {
        this.students = students;
    }

    /**
     * Get the value of addressCountry
     *
     * @return the value of addressCountry
     */
    public String getAddressCountry() {
        return addressCountry;
    }

    /**
     * Set the value of addressCountry
     *
     * @param addressCountry new value of addressCountry
     */
    public void setAddressCountry(String addressCountry) {
        this.addressCountry = addressCountry;
    }


    /**
     * Get the value of addressZipCode
     *
     * @return the value of addressZipCode
     */
    public String getAddressZipCode() {
        return addressZipCode;
    }

    /**
     * Set the value of addressZipCode
     *
     * @param addressZipCode new value of addressZipCode
     */
    public void setAddressZipCode(String addressZipCode) {
        this.addressZipCode = addressZipCode;
    }


    /**
     * Get the value of addressState
     *
     * @return the value of addressState
     */
    public String getAddressState() {
        return addressState;
    }

    /**
     * Set the value of addressState
     *
     * @param addressState new value of addressState
     */
    public void setAddressState(String addressState) {
        this.addressState = addressState;
    }


    /**
     * Get the value of addressCity
     *
     * @return the value of addressCity
     */
    public String getAddressCity() {
        return addressCity;
    }

    /**
     * Set the value of addressCity
     *
     * @param addressCity new value of addressCity
     */
    public void setAddressCity(String addressCity) {
        this.addressCity = addressCity;
    }


    /**
     * Get the value of address2
     *
     * @return the value of address2
     */
    public String getAddress2() {
        return address2;
    }

    /**
     * Set the value of address2
     *
     * @param address2 new value of address2
     */
    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    /**
     * Get the value of address1
     *
     * @return the value of address1
     */
    public String getAddress1() {
        return address1;
    }

    /**
     * Set the value of address1
     *
     * @param address1 new value of address1
     */
    public void setAddress1(String address1) {
        this.address1 = address1;
    }

   

    @Override
    public String toString() {
        return "Address{" + "id=" + id + ", address1=" + address1 + ", address2=" + address2 + ", addressCity=" + addressCity + ", addressState=" + addressState + ", addressZipCode=" + addressZipCode + ", addressCountry=" + addressCountry + '}';
    }

    
}
