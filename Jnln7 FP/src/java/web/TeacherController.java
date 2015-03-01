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
import model.Teacher;
import model.TeacherCourse;

/**
 *
 * @author Justin
 */
@Named
@RequestScoped
public class TeacherController extends AbstractController{
   
    @EJB
    private CourseBean courseBean;

    private static final Logger LOG = Logger.getLogger(TeacherController.class.getName());
    
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
    private Student student;
    private Address address;
    private Teacher teacher;
    private TeacherCourse teacherCourse;

    /**
     *
     * @return
     */
    public TeacherCourse getTeacherCourse() {
        return teacherCourse;
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
    public Student getStudent() {
        return student;
    }

    /**
     *
     * @param student
     */
    public void setStudent(Student student) {
        this.student = student;
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
    public TeacherController() {
    }

    @PostConstruct
    private void postConstruct() {
        teacher = new Teacher();
        teacher.setAddress(new Address());
        teacherCourse = new TeacherCourse();
        refreshTeacherInfo();
        
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
    
    private void refreshTeacherInfo(){
        if (loginController.isStudent()) {
            student = studentBean.findByUser(loginController.getRemoteUser());
        } else if (loginController.isTeacher()) {
            teacher = teacherBean.findByUser(loginController.getRemoteUser());
        } else {
            teacher = new Teacher();
        }
    }
    
    /**
     *
     * @param teacher
     * @return
     */
    public String viewEnrollments(Teacher teacher) {
         LOG.info("Trying to view enrollments of the teacher !");
         try{
        if (this.teacher.getUser().getUserName().equals(teacher.getUser().getUserName())) {
            teacherCourses = this.teacher.getTeacherCourses();
        } else {
            this.teacher = teacher;
            teacherCourses = teacher.getTeacherCourses();
        }
        //LOG.info("### Showing enrollments for student : " + this.teacherCourses.toString());
        return loginController.getPagePath() + "/showTeacherEnrolments.xhtml";
         }catch (Exception e){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error while showing your enrollments "));
             LOG.info("Error while trying to show teacher enrollments !");
             return loginController.getPagePath() + "/welcome.xhtml";
         }
    }
    
    /**
     *
     * @param teacher
     * @return
     */
    public String updateTeacher(Teacher teacher){
        this.teacher = teacher;
        return loginController.getPagePath() + "/updateTeacher.xhtml";
    }
    
    /**
     *
     * @return
     */
    public String executeUpdateTeacher(){
        try{
        LOG.info("About to update teacher information: " + teacher.getName());
        teacherBean.update(teacher);
         refreshTeacherInfo();
         FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("teacher information sucessfully updated !! "));
        } catch(Exception e){
            LOG.info(e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error while trying to update teacher information " + e.getMessage()));
        }
        return loginController.getPagePath() + "/welcome.xhtml";
    }
    
    /**
     *
     * @param course
     * @return
     */
    public String deleteRegistration(Course course){
         LOG.info("Deleting --"+ course.toString());
         courseBean.remove(course);
         refreshCourseList();
        return loginController.getPagePath() + "/viewCourses.xhtml";
    }
    
    /**
     *
     * @param course
     * @return
     */
    public String deleteEnrollment(Course course) {
        
         try{
        LOG.info("Deleting --" + course.getCourseName() + " for teacher : " + teacher);
        List<TeacherCourse> teacherCourses1= teacherCourseBean.findAll();
        for (TeacherCourse stCourse : teacherCourses1) {
            if (stCourse.getCourse().getCourseName().equals(course.getCourseName())) {
                teacherCourse = stCourse;
            }
        }
        //LOG.info("Student Course after finding from list  : "+studentCourse);
        course.getTeacherCourses().remove(teacherCourse);
        
        teacher.getTeacherCourses().remove(teacherCourse);
        //LOG.info("After removing studentCourse from student " + student.getStudentCourses());
        teacherCourseBean.delete2(teacherCourse,course, teacher);
            //this.course = course;
        LOG.info("Teacher Courses after removing from list  : "+teacherCourses);
        refreshCourseList();
        
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Enrollment sucessfully deleted !! "));
        return loginController.getPagePath() + "/welcome.xhtml";
         }
         catch (Exception e){
             LOG.info(e.getMessage());
             FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error while deleting student enrollment "));
             return loginController.getPagePath() + "/welcome.xhtml";
         }
         
         
    }
    
}
