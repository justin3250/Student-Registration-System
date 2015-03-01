/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import ejb.CourseBean;
import ejb.StudentBean;
import ejb.StudentCourseBean;
import ejb.TeacherBean;
import ejb.TeacherCourseBean;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import model.Address;
import model.Course;
import model.Student;
import model.StudentCourse;
import model.TeacherCourse;
import model.security.User;
import org.hibernate.validator.constraints.Length;

/**
 *
 * @author Justin
 */
@Named
@RequestScoped
public class CourseController extends AbstractController{
   
    @EJB
    private CourseBean courseBean;

    private static final Logger LOG = Logger.getLogger(CourseController.class.getName());
    
    @EJB
    private TeacherCourseBean teacherCourseBean;
    @EJB
    private StudentCourseBean studentCourseBean;
    
    @EJB
    private TeacherBean teacherBean;
    @EJB
    private StudentBean studentBean;
    

    private List<StudentCourse> studentCourses;
    private List<TeacherCourse> teacherCourses;
    private List<Course> courses;
    private Course course;
    private Course courseNew;
    
    @Length(min=1,message = "Course Name cannot be empty")
    private String courseName;
    //@NotEmpty(message="Password cannot be empty")
    @Length(min=1,message = "Course Description cannot be empty")
    private String courseDescription;

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
    public List<Course> getCourses() {
        return courses;
    }

    /**
     *
     * @param courses
     */
    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }
    
    
    /**
     * Get the value of teacherCourses
     *
     * @return the value of teacherCourses
     */
    public List<TeacherCourse> getTeacherCourses() {
        return teacherCourses;
    }

    /**
     * Set the value of teacherCourses
     *
     * @param teacherCourses new value of teacherCourses
     */
    public void setTeacherCourses(List<TeacherCourse> teacherCourses) {
        this.teacherCourses = teacherCourses;
    }

    /**
     * Get the value of studentCourses
     *
     * @return the value of studentCourses
     */
    public List<StudentCourse> getStudentCourses() {
        return studentCourses;
    }

    /**
     * Set the value of studentCourses
     *
     * @param studentCourses new value of studentCourses
     */
    public void setStudentCourses(List<StudentCourse> studentCourses) {
        this.studentCourses = studentCourses;
    }

    @Inject
    LoginController loginController;

    /**
     *
     */
    public CourseController() {
    }

    @PostConstruct
    private void postConstruct() {
        course = new Course();
        courses = new ArrayList<>();
        //course.setStudentCourses((List<StudentCourse>) new StudentCourse());
        refreshCourseList();
        
    }
    
    private void refreshCourseList(){
        if (loginController.isStudent()) {
            studentCourses = studentBean.findByUser(loginController.getRemoteUser()).getStudentCourses();
        } else if (loginController.isTeacher()) {
            teacherCourses = teacherBean.findByUser(loginController.getRemoteUser()).getTeacherCourses();
        } else {
            studentCourses = new ArrayList<>();
        }
    }
    
    /**
     *
     * @param course
     * @return
     */
    public String updateCourse(Course course){
        if(course!=null){
        this.course = course;
        LOG.info("### inside updateCourse od Course Controller: "+ this.course.toString());
        }
        return loginController.getPagePath() + "/updateCourse.xhtml";
    }
    
    /**
     *
     * @return
     */
    public String executeUpdate(){
        try{
        LOG.info("About to update : " + course.toString());
        courseBean.update(course);
         refreshCourseList();
        return loginController.getPagePath() + "/viewCourses.xhtml";
        } catch (Exception e){
            LOG.info(e.getMessage() );
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("SError while updating course !" + e.getMessage()));
            return loginController.getPagePath() + "/welcome.xhtml";
        }
    }
    
    /**
     *
     * @return
     */
    public String addCourse(){
        try{
            course.setCourseName(courseName);
            course.setCourseDescription(courseDescription);
            course.setId(Long.MIN_VALUE);
            LOG.info("Adding course : " + course);
            //LOG.info( gender + firstName + lastName + dateOfBirth + contactNumber + username + password);
            
            
            //LOG.info("Newe user obj");
            
            //checking to see if username exists
            
            courses = courseBean.findAll();
            for(Course course1 : courses){
                if(course1.getCourseName().equals(course.getCourseName()))
                {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("CourseName already exists. Please try with another Course Name."));
                    return loginController.getPagePath() +"/createCourse.xhtml";
                }
            }
            
            
            courseBean.create(course);
            //LOG.info("NUser bean creates user");
           
             FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Sucessfully added course!"));
        }catch (Exception e){
            LOG.info(e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error while adding course - " + e.getMessage()));
        }
             return loginController.getPagePath() +"/welcome.xhtml";
    }
    
    /**
     *
     * @param course
     * @return
     */
    public String deleteCourse(Course course){
         try{
         LOG.info("Deleting --"+ course.toString());
         courseBean.remove(course);
         refreshCourseList();
        return loginController.getPagePath() + "/viewCourses.xhtml";
         }
         catch (Exception e){
             LOG.info(e.getMessage());
             FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error while deleting course " + e.getMessage()));
              return loginController.getPagePath() + "/welcome.xhtml";
         }
    }
    
    /**
     *
     * @return
     */
    public String getSearchCourse(){
            LOG.info("About to search for student : " + courseName);
            List<Course> courses = courseBean.findAll();
            boolean foundFlag = false;
            for (Course course : courses) {
            if (course.getCourseName().equals(courseName)) {
                this.course = course;
                this.courses.add(course);
                foundFlag = true;
                LOG.info("Found course: " + this.courses);
                //FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Could not find course- " + courseName));
                return loginController.getPagePath() + "/showSearchResults.xhtml";
            }
            }
            if(!foundFlag)
            {
                LOG.info("Could not find course -" + courseName);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Could not find course- " + courseName));
            }
            return loginController.getPagePath() + "/searchCourse.xhtml";
        }
}
