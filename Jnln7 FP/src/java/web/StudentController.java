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
import java.util.Iterator;
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
import org.hibernate.validator.constraints.Length;

/**
 *
 * @author Justin
 */
@Named
@RequestScoped
public class StudentController extends AbstractController {

    @EJB
    private CourseBean courseBean;

    private static final Logger LOG = Logger.getLogger(StudentController.class.getName());

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
    private StudentCourse studentCourse;

    @Length(min = 1, message = "Student Name cannot be empty")
    private String studentName;

    /**
     *
     * @return
     */
    public String getStudentName() {
        return studentName;
    }

    /**
     *
     * @param studentName
     */
    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    /**
     *
     * @return
     */
    public StudentCourse getStudentCourse() {
        return studentCourse;
    }

    /**
     *
     * @param studentCourse
     */
    public void setStudentCourse(StudentCourse studentCourse) {
        this.studentCourse = studentCourse;
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
    public StudentController() {
    }

    @PostConstruct
    private void postConstruct() {
        student = new Student();
        studentCourse = new StudentCourse();
        course = new Course();
        student.setAddress(new Address());
        courses = new ArrayList<>();
        studentCourses = new ArrayList<>();
        refreshStudentInfo();
        refreshCourseList();

    }

    private void refreshCourseList() {
        if (loginController.isStudent()) {
            studentCourses = studentBean.findByUser(loginController.getRemoteUser()).getStudentCourses();
        } else if (loginController.isTeacher()) {
            teacherCourses = teacherBean.findByUser(loginController.getRemoteUser()).getTeacherCourses();
        } else {
            studentCourses = new ArrayList<>();
        }
    }

    private void refreshStudentInfo() {
        if (loginController.isStudent()) {
            student = studentBean.findByUser(loginController.getRemoteUser());
        } else if (loginController.isTeacher()) {
            //teacher = teacherBean.findByUser(loginController.getRemoteUser());
        } else {
            studentCourses = new ArrayList<>();
        }
    }

    private void refreshStudentCourseInfo() {
        if (loginController.isStudent()) {
            student = studentBean.findByUser(loginController.getRemoteUser());
            
        } else if (loginController.isTeacher()) {
            //teacher = teacherBean.findByUser(loginController.getRemoteUser());
        } else {
            studentCourses = new ArrayList<>();
        }
    }

    /**
     *
     * @param student
     * @return
     */
    public String newEnrollment(Student student) {
        this.student = student;
        //this.courses = getAllCourses();
        /*
         this.courses =courseBean.findAll();
         for(StudentCourse sc:student.getStudentCourses())
         {
         Iterator<Course> it = courses.iterator();
         while(it.hasNext()){
         Course course1 = it.next();
         if(course1.getCourseName().equals(sc.getCourse().getCourseName()))
         it.remove();
         }
         }
         */
        //LOG.info("### Showing available enrollments for student : "+ courses);
        return loginController.getPagePath() + "/showCoursesToEnrol.xhtml";
    }

    /**
     *
     * @param course
     * @return
     */
    public String enrollStudentCourse(Course course) {
        try{
        LOG.info("Trying to enrol for student with course :" + course.getCourseName());

        List<StudentCourse> checkstudentCourseExist = student.getStudentCourses();
        for (StudentCourse stCourse : checkstudentCourseExist) {
            if (course.getId().equals(stCourse.getCourse().getId())) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("You have already registered for this course! Try another course "));
                return loginController.getPagePath() +"/showCoursesToEnrol.xhtml";
            }
        }
        
        LOG.info(studentCourse.toString());
        studentCourse.setCourse(course);
        studentCourse.setStudent(student);
        studentCourse.setId(Long.MIN_VALUE);
        
         student.getStudentCourses().add(studentCourse);
         studentBean.update(student);
         LOG.info("List of new Student courses : " + student.getStudentCourses());
         course.getStudentCourses().add(studentCourse);
         courseBean.update(course);
         LOG.info("List of new course.getStudentCOurses : " + course.getStudentCourses());
        studentCourseBean.create(studentCourse); 
        refreshStudentCourseInfo();
        facesContext.addMessage(null, new FacesMessage("You have sucessfully registered for this course!"));
          
        return loginController.getPagePath() +"/showCoursesToEnrol.xhtml";
        }
        catch(Exception e){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error while trying to enrol for course -" + e.getMessage()));
            LOG.info(e.getMessage());
            return loginController.getPagePath() +"/welcome.xhtml";
        }
    }

    /**
     *
     * @param student
     * @return
     */
    public String viewEnrollments(Student student) {
        if (this.student.getUser().getUserName().equals(student.getUser().getUserName())) {
            LOG.info("About to view enrollments ");
            if(studentCourses==null){
                //LOG.info("Student courses is null"+studentCourses);
                studentCourses = this.student.getStudentCourses();
            }
            else{
               // LOG.info("Student courses is not null"+studentCourses);
            }
        } else {
            this.student = student;
            if(studentCourses==null)
            studentCourses = student.getStudentCourses();
        }
        LOG.info("### Showing enrollments for student : " + this.studentCourses.toString());
        return loginController.getPagePath() + "/showStudentEnrolments.xhtml";
    }

    /**
     *
     * @param student
     * @return
     */
    public String updateStudent(Student student) {
        this.student = student;
        LOG.info("### inside updateStudent of Student Controller: " + student.toString());
        return loginController.getPagePath() + "/updateStudent.xhtml";
    }

    /**
     *
     * @return
     */
    public String executeUpdateStudent() {
        try{
        LOG.info("About to update student information: " + student.toString());
        studentBean.update(student);
        refreshStudentInfo();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Student information sucessfully updated !! "));
        } catch (Exception e){
            e.getMessage();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error while trying to update student information !! " + e.getMessage()));
        }
        
        return loginController.getPagePath() + "/welcome.xhtml";
    }

    /**
     *
     * @param course
     * @return
     */
    public String deleteRegistration(Course course) {
        try{
        LOG.info("Deleting --" + course.toString());
        courseBean.remove(course);
        refreshCourseList();
        return loginController.getPagePath() + "/viewCourses.xhtml";
        }catch(Exception e){
            LOG.info("Error while deleting course " + e.getMessage());
            return loginController.getPagePath() + "/welcome.xhtml";
        }
    }

    /**
     *
     * @param course
     * @return
     */
    public String deleteEnrollment(Course course) {
        
         try{
        LOG.info("Deleting --" + course.getCourseName() + " for student : " + student);
        List<StudentCourse> studentCourses1= studentCourseBean.findAll();
        for (StudentCourse stCourse : studentCourses1) {
            if (stCourse.getCourse().getCourseName().equals(course.getCourseName())) {
                studentCourse = stCourse;
            }
        }
        //LOG.info("Student Course after finding from list  : "+studentCourse);
        course.getStudentCourses().remove(studentCourse);
        
        student.getStudentCourses().remove(studentCourse);
        //LOG.info("After removing studentCourse from student " + student.getStudentCourses());
        studentCourseBean.delete2(studentCourse,course, student);
            //this.course = course;
        //LOG.info("Student Courses after removing from list  : "+studentCourses);
        refreshCourseList();
        
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Enrollment sucessfully deleted !! "));
        return loginController.getPagePath() + "/welcome.xhtml";
         }
         catch (Exception e){
             LOG.info(e.getMessage());
             FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error while deleting student enrollment "));
             return loginController.getPagePath() + "/viewCourses.xhtml";
         }
    }
        
        
        

}
