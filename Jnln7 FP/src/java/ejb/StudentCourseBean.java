/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb;

import java.util.List;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Named;
import model.Course;
import model.Student;
import model.StudentCourse;

/**
 *
 * @author Justin
 * @param <T>
 */
@Named
@Stateless
public class StudentCourseBean<T> extends AbstractBean<StudentCourse> {

    /**
     *
     */
    public StudentCourseBean() {
        super(StudentCourse.class);
    }

    /**
     *
     * @return
     */
    @Override
    public List<StudentCourse> findAll() {
        return getEntityManager().createNamedQuery("StudentCourse.findAll",StudentCourse.class).getResultList();
    }

    /**
     *
     * @param username
     * @return
     */
    public StudentCourse findByUser(String username){
        
        //TypedQuery<Student> query= getEntityManager().createNamedQuery("Student.findByUsername" , Student.class);
        //query.setParameter("userName",username );
        //query.getSingleResult();
        return getEntityManager().createNamedQuery("StudentCourse.findByUsername" , StudentCourse.class).
                setParameter("userName", username).getSingleResult();
    }
    
    @Override
    public void create(StudentCourse studentCourse) {
        super.create(studentCourse); 
    }
    private static final Logger LOG = Logger.getLogger(StudentCourseBean.class.getName());

    
    @Override
    public void remove(StudentCourse studentCourse) {
        LOG.info("Removing Course !!" + studentCourse.getId()+ ":::: " + studentCourse.getCourse().getCourseName());
           studentCourse = getEntityManager().getReference(StudentCourse.class, studentCourse.getId());
           studentCourse.getCourse().getStudentCourses().remove(studentCourse);
           List<StudentCourse> studentcourses =findAll();
           //LOG.info("About to remove student course .." + studentCourse);
           getEntityManager().remove(studentCourse);
           studentcourses.remove(studentCourse);
           LOG.info("After removing  .." + studentcourses);
           //getEntityManager().flush();
           
    }
    
    /**
     *
     * @param studentCourse
     * @param course
     * @param student
     * @return
     */
    public List<StudentCourse> delete(StudentCourse studentCourse, Course course, Student student) {
        LOG.info("Removing Course !!" + studentCourse.getId()+ ":::: " + studentCourse.getCourse().getCourseName());
           studentCourse = getEntityManager().getReference(StudentCourse.class, studentCourse.getId());
                     
           course = getEntityManager().getReference(Course.class, course.getId());
           course.getStudentCourses().remove(course);
        
           studentCourse.getCourse().getStudentCourses().remove(studentCourse);
           LOG.info("After flush 1: " + course.getStudentCourses());
           
           student = getEntityManager().getReference(Student.class, student.getId());
           student.getStudentCourses().remove(studentCourse);
           LOG.info("After flush 2: " + student.getStudentCourses());
           
           List<StudentCourse> studentcourses =findAll();
           //LOG.info("About to remove student course .." + studentCourse);
           studentcourses.remove(studentCourse);
           studentCourse.getStudent().getStudentCourses().remove(studentCourse);
           getEntityManager().remove(studentCourse);
           
           LOG.info("After flush 3: " + studentCourse.getCourse().getCourseName());
           
           //LOG.info("After removing  .." + studentcourses);
           //getEntityManager().flush();
           return studentcourses;
    }
    
    /**
     *
     * @param studentCourse
     * @param course
     * @param student
     */
    public void delete2(StudentCourse studentCourse, Course course, Student student) {
        LOG.info("Removing Course !!" + studentCourse.getId()+ ":::: " + studentCourse.getCourse().getCourseName());
           studentCourse = getEntityManager().getReference(StudentCourse.class, studentCourse.getId());
                     
           course = getEntityManager().getReference(Course.class, course.getId());
           course.getStudentCourses().remove(course);
        
           studentCourse.getCourse().getStudentCourses().remove(studentCourse);
          // LOG.info("After flush 1: " + course.getStudentCourses());
           
           student = getEntityManager().getReference(Student.class, student.getId());
           student.getStudentCourses().remove(studentCourse);
           //LOG.info("After flush 2: " + student.getStudentCourses());
           
           List<StudentCourse> studentcourses =findAll();
           //LOG.info("About to remove student course .." + studentCourse);
           studentcourses.remove(studentCourse);
           studentCourse.getStudent().getStudentCourses().remove(studentCourse);
           getEntityManager().remove(studentCourse);
           
           //LOG.info("After flush 3: " + studentCourse.getCourse().getCourseName());
           
           //LOG.info("After removing  .." + studentcourses);
           //getEntityManager().flush();
           
    }
    
    
}
