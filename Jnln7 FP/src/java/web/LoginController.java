/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import ejb.AddressBean;
import ejb.StudentBean;
import ejb.security.GroupBean;
import ejb.security.UserBean;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import model.Address;
import model.Course;
import model.Student;
import model.security.User;
import org.hibernate.validator.constraints.Length;
import servlets.SecurityServlet;

/**
 *
 * @author Justin
 */
@Named
@RequestScoped
public class LoginController extends AbstractController {

    @EJB
    private GroupBean groupBean;
    @EJB
    private AddressBean addressBean;
    @EJB
    private UserBean userBean;
    @EJB
    private StudentBean studentBean;

    private static final Logger LOG = Logger.getLogger(SecurityServlet.class.getName());

    /**
     *
     */
    public LoginController() {
    }

    @PostConstruct
    private void postConstruct() {

    }
    //@NotEmpty(message="Username cannot be empty")
    @Length(min = 1, message = "Username cannot be empty")
    private String username;
    //@NotEmpty(message="Password cannot be empty")
    @Length(min = 1, message = "Password cannot be empty")
    private String password;

    @Length(min = 1, message = "First Name cannot be empty")
    private String firstName;

    @Length(min = 1, message = "Last Name cannot be empty")
    private String lastName;

    @Length(min = 1, message = "Gender cannot be empty")
    private String gender;

    @Length(min = 1, message = "Contact Number cannot be empty")
    private String contactNumber;
    
    @Length(min = 1, message = "Address1 cannot be empty")
    private String address1;
    
    @Length(min = 1, message = "Address2 cannot be empty")
    private String address2;
    
    @Length(min = 1, message = "Address City cannot be empty")
    private String addressCity;

    @Length(min = 1, message = "Address State cannot be empty")
    private String addressState;
    
    @Length(min = 1, message = "Address Country cannot be empty")
    private String addressCountry;
    
    @Length(min = 1, message = "Address Zip-Code cannot be empty")
    private String addressZipcode;
    
    private Date dateOfBirth;

    /**
     *
     * @return
     */
    public String getAddress1() {
        return address1;
    }

    /**
     *
     * @param address1
     */
    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    /**
     *
     * @return
     */
    public String getAddress2() {
        return address2;
    }

    /**
     *
     * @param address2
     */
    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    /**
     *
     * @return
     */
    public String getAddressCity() {
        return addressCity;
    }

    /**
     *
     * @param addressCity
     */
    public void setAddressCity(String addressCity) {
        this.addressCity = addressCity;
    }

    /**
     *
     * @return
     */
    public String getAddressState() {
        return addressState;
    }

    /**
     *
     * @param addressState
     */
    public void setAddressState(String addressState) {
        this.addressState = addressState;
    }

    /**
     *
     * @return
     */
    public String getAddressCountry() {
        return addressCountry;
    }

    /**
     *
     * @param addressCountry
     */
    public void setAddressCountry(String addressCountry) {
        this.addressCountry = addressCountry;
    }

    /**
     *
     * @return
     */
    public String getAddressZipcode() {
        return addressZipcode;
    }

    /**
     *
     * @param addressZipcode
     */
    public void setAddressZipcode(String addressZipcode) {
        this.addressZipcode = addressZipcode;
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
     * Get the value of password
     *
     * @return the value of password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Set the value of password
     *
     * @param password new value of password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Get the value of username
     *
     * @return the value of username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Set the value of username
     *
     * @param username new value of username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     *
     * @return
     */
    public String getPagePath() {
        if (isStudent()) {
            
            return "/student";
        } else if (isTeacher()) {
            
            return "/teacher";
        } else if (isAdmin()) {
            
            return "/admin";
        } else {
            return "/error.xhtml";
        }
    }

    /**
     *
     * @return
     */
    public String doLogin() {
        try {
            LOG.info("Trying to login with username :  "+ username );
            HttpServletRequest req = (HttpServletRequest) facesContext.getExternalContext().getRequest();
            req.login(username, password);
            LOG.info("Logg in sucessufull ! " );
            LOG.info("Going to path : " + getPagePath() + "/welcome.xhtml");
            return (getPagePath() + "/welcome.xhtml");
        } catch (ServletException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            facesContext.addMessage(null, new FacesMessage("Username or password was incorrect!"));
            return "/login.xhtml";
        }
    }

    /**
     *
     * @return
     */
    public String doRegister() {

        LOG.info("About to register user with username:" + firstName);
        try {
            //List<Address> addresses = addressBean.findAll();
            User user = new User(username, password);

            //checking to see if username exists
            List<Student> students = studentBean.findAll();
            for (Student stu : students) {
                if (username.equals(stu.getUser().getUserName())) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Username already exists. Please try with another username."));
                    LOG.info("Username already exists:" + username + " .Try another username");
                    return "/register.xhtml";
                }
            }

            LOG.info("By default all registered users will be student!");
            user.addGroup(groupBean.findAll().get(1));
            userBean.create(user);
            Address address = new Address(address1, address2, addressCity, addressState, addressZipcode, addressCountry);
            address.setId(Long.MIN_VALUE);
            addressBean.create(address);
            //Student student = new Student(firstName, lastName, gender, dateOfBirth, contactNumber, addressBean.findAll().get(1));
            Student student = new Student(firstName, lastName, gender, dateOfBirth, contactNumber, address);
            student.setUser(user);

            studentBean.create(student);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Sucessfully registered. Please login to continue."));
            LOG.info("Registration sucessfull!");
            return "/login.xhtml";
        } catch (Exception e) {
            LOG.info(e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error while trying to register:" + e.getMessage()));
            return "/register.xhtml";
        }

    }

    /**
     *
     * @return
     */
    public String doLogout() {
        
        try {
            HttpServletRequest req = (HttpServletRequest) facesContext.getExternalContext().getRequest();
            req.logout();
            LOG.info("Log out sucessfull!");
        } catch (ServletException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            facesContext.addMessage(null, new FacesMessage("Unable to logout!"));
            return "/error.xhtml";
        }
        return "/login.xhtml";
    }

    /**
     *
     * @return
     */
    public boolean isAdmin() {
        return facesContext.getExternalContext().isUserInRole("admin");
    }

    /**
     *
     * @return
     */
    public boolean isStudent() {
        return facesContext.getExternalContext().isUserInRole("student");
    }

    /**
     *
     * @return
     */
    public boolean isTeacher() {
        return facesContext.getExternalContext().isUserInRole("teacher");
    }

    /**
     *
     * @return
     */
    public String getRemoteUser() {
        return facesContext.getExternalContext().getRemoteUser();
    }

    /*
     public String updateUser(Course course){
     System.out.println("### inside updateCourse od Course Controller: "+ course.toString());
     return getPagePath() + "/";
     }
    
     public String deleteUser(Course course){
     LOG.info("Deleting --"+ course.toString());
     return getPagePath() + "/welcome.xhtml";
     }
     */
}
