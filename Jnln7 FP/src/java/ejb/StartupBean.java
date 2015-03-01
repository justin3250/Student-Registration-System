/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb;

import ejb.security.GroupBean;
import ejb.security.UserBean;
import java.util.GregorianCalendar;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import model.Address;
import model.Course;
import model.Student;
import model.StudentCourse;
import model.Teacher;
import model.TeacherCourse;
import model.security.Group;
import model.security.User;

/**
 *
 * @author Justin
 */
@Singleton
@Startup
public class StartupBean {
    private static final Logger LOG = Logger.getLogger(StartupBean.class.getName());
    
    @EJB
    private UserBean userBean;
    
    @EJB
    private GroupBean groupBean;
    
    @EJB
    private TeacherCourseBean teacherCourseBean;
    
    @EJB
    private TeacherBean teacherBean;
    
    @EJB
    private StudentCourseBean studentCourseBean;
    
    @EJB
    private StudentBean studentBean;
    
    @EJB
    private CourseBean courseBean;
    
    @EJB
    private AddressBean addressBean;
    
    /**
     *
     */
    public StartupBean() {
    }
    
    @PostConstruct
    private void populateDatabase(){
        Group group = new Group("teachers", "group of all teachers");
        Group group1 = new Group("students", "group of all students");
        Group group2 = new Group("admins", "group of all admins");
        groupBean.create(group);
        groupBean.create(group1);
        groupBean.create(group2);
        
        User user = new User("justin", "justin");
        User user3 = new User("jason", "jason");
        User user4 = new User("scott", "scott");
        User user5 = new User("student", "student");
        User user6 = new User("omar", "omar");
        User user7 = new User("admin", "admin");
        user.addGroup(group1);
        user3.addGroup(group);
        user4.addGroup(group);
        user5.addGroup(group1);
        user6.addGroup(group);
        user7.addGroup(group2);
        
        userBean.create(user);
        userBean.create(user3);
        userBean.create(user4);
        userBean.create(user5);
        userBean.create(user6);
        userBean.create(user7);
        
        Address address = new Address("2001 S michigan avenue" , "708" , "Chicago" , "IL" , "60616" , "USA");
        Address address2 = new Address("2001 S King drive avenue" , "22708" , "Chicago" , "IL" , "60616" , "USA");
        Address address3 = new Address("145 S Wenworth Ave" , "4522" , "San Diego" , "CA" , "4425" , "USA");
        
        
        Course course = new Course("ITMD 562" , "PHP Development");
        Course course2 = new Course("ITMD 515" , "Advanced Java Development");
        Course course3 = new Course("ITMD 540" , "Data Networks");
        Course course4 = new Course("ITMD 561" , "HTML/CSS Development");
        Course course5 = new Course("ITMD 422" , "Data Analytics");
        Course course6 = new Course("CS 505" , "Advanced Database Management");
        Course course7 = new Course("ITMD 510" , "Introduction to Web Development");
        Course course8 = new Course("ITMD 565" , "Angular JS Development");
        Course course9 = new Course("ITMD 311" , "Introduction to Java");
        
        Student student = new Student("Justin", "francis", "Male", new GregorianCalendar(2013 ,12,20).getTime(),"999-999-9999",address2);
        Student student2 = new Student("Lady", "Spy", "FeMale", new GregorianCalendar(1876 ,12,20).getTime(),"888-888-9999",address);
        
        Teacher teacher = new Teacher(address, "Scott Spyrison");
        Teacher teacher2 = new Teacher(address2, "Jason Lambert");
        Teacher teacher3 = new Teacher(address3, "Omar Alduwad");
        
        StudentCourse studentCourse = new StudentCourse("25", "l","24", "20","97", student, course);
        StudentCourse studentCourseJustin = new StudentCourse("25", "25","24", "20","99", student, course2);
        StudentCourse studentCourseJustin2 = new StudentCourse("25", "25","24", "20","99", student, course3);
        StudentCourse studentCourseJustin3 = new StudentCourse("25", "25","24", "20","99", student, course5);
        StudentCourse studentCourse2 = new StudentCourse("14", "l4","21", "25","87", student2, course2);
        StudentCourse studentCourse3 = new StudentCourse("14", "l4","21", "25","87", student2, course4);
        StudentCourse studentCourse4 = new StudentCourse("14", "l4","21", "25","87", student2, course6);
        StudentCourse studentCourse5 = new StudentCourse("14", "l4","21", "25","87", student2, course7);
        StudentCourse studentCourse6 = new StudentCourse("14", "l4","21", "25","87", student2, course8);
        
        TeacherCourse teacherCourse = new TeacherCourse(teacher, course2); // Scott //515
        TeacherCourse teacherCourse2 = new TeacherCourse(teacher, course7); // Scott //510
        
        TeacherCourse teacherCourse3 = new TeacherCourse(teacher2, course); //jason //562
        TeacherCourse teacherCourse4 = new TeacherCourse(teacher2, course4); //jason //565
        TeacherCourse teacherCourse5 = new TeacherCourse(teacher2, course8); //jason //561
        
        TeacherCourse teacherCourse6 = new TeacherCourse(teacher3, course5); //omar //422
        TeacherCourse teacherCourse7 = new TeacherCourse(teacher3, course6); //omar //311
        TeacherCourse teacherCourse8 = new TeacherCourse(teacher3, course9); //omar //
        
        student.setUser(user);
        student2.setUser(user5);
        
        teacher.setUser(user4);
        teacher2.setUser(user3);
        teacher3.setUser(user6);
        
        student.getStudentCourses().add(studentCourse);
        student.getStudentCourses().add(studentCourseJustin);
        student.getStudentCourses().add(studentCourseJustin2);
        student.getStudentCourses().add(studentCourseJustin3);
        student2.getStudentCourses().add(studentCourse2);
        student2.getStudentCourses().add(studentCourse3);
        student2.getStudentCourses().add(studentCourse4);
        student2.getStudentCourses().add(studentCourse5);
        student2.getStudentCourses().add(studentCourse6);
        
        course.getStudentCourses().add(studentCourse);
        course2.getStudentCourses().add(studentCourse2);
        course2.getStudentCourses().add(studentCourseJustin);
        course3.getStudentCourses().add(studentCourseJustin2);
        course4.getStudentCourses().add(studentCourse3);
        course5.getStudentCourses().add(studentCourseJustin3);
        course6.getStudentCourses().add(studentCourse4);
        course7.getStudentCourses().add(studentCourse5);
        course8.getStudentCourses().add(studentCourse6);

       
        //managing two sided relationship for teacher course and teacherCourse
        //teacher.getTeacherCourses().add(teacherCourse);
        //teacher2.getTeacherCourses().add(teacherCourse2);
        
        teacher.addTeacherCourse(teacherCourse);
        teacher.addTeacherCourse(teacherCourse2);
        
        teacher2.addTeacherCourse(teacherCourse3);
        teacher2.addTeacherCourse(teacherCourse4);
        teacher2.addTeacherCourse(teacherCourse5);
        
        teacher3.addTeacherCourse(teacherCourse6);
        teacher3.addTeacherCourse(teacherCourse7);
        teacher3.addTeacherCourse(teacherCourse8);
        
        //LOG.info(teacher.getTeacherCourses().size()+"SIZE ::");
        //  LOG.info(teacher2.getTeacherCourses().iterator().next().getCourse().getCourseName() +"SIZE ::");
        
        course.getTeacherCourses().add(teacherCourse3);
        course2.getTeacherCourses().add(teacherCourse);
        course4.getTeacherCourses().add(teacherCourse4);
        course5.getTeacherCourses().add(teacherCourse6);
        course6.getTeacherCourses().add(teacherCourse7);
        course7.getTeacherCourses().add(teacherCourse2);
        course8.getTeacherCourses().add(teacherCourse5);
        course9.getTeacherCourses().add(teacherCourse8);
        
        
        addressBean.create(address);
        addressBean.create(address2);
        addressBean.create(address3);
        
        courseBean.create(course);
        courseBean.create(course2);
        courseBean.create(course3);
        courseBean.create(course4);
        courseBean.create(course5);
        courseBean.create(course6);
        courseBean.create(course7);
        courseBean.create(course8);
        courseBean.create(course9);
        
        studentBean.create(student);
        studentBean.create(student2);
        
        teacherBean.create(teacher);
        teacherBean.create(teacher2);
        teacherBean.create(teacher3);
                
        studentCourseBean.create(studentCourse);
        studentCourseBean.create(studentCourse2);
        studentCourseBean.create(studentCourse3);
        studentCourseBean.create(studentCourse4);
        studentCourseBean.create(studentCourse5);
        studentCourseBean.create(studentCourse6);
        studentCourseBean.create(studentCourseJustin);
        studentCourseBean.create(studentCourseJustin2);
        studentCourseBean.create(studentCourseJustin3);
        
        teacherCourseBean.create(teacherCourse);
        teacherCourseBean.create(teacherCourse2);
        teacherCourseBean.create(teacherCourse3);
        teacherCourseBean.create(teacherCourse4);
        teacherCourseBean.create(teacherCourse5);
        teacherCourseBean.create(teacherCourse6);
        teacherCourseBean.create(teacherCourse7);
        teacherCourseBean.create(teacherCourse8);
    }   
    
}
