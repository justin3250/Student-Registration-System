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
import model.Teacher;
import model.TeacherCourse;

/**
 *
 * @author Justin
 * @param <T>
 */
@Named
@Stateless
public class TeacherCourseBean<T> extends AbstractBean<TeacherCourse> {

    /**
     *
     */
    public TeacherCourseBean() {
        super(TeacherCourse.class);
    }

    /**
     *
     * @return
     */
    @Override
    public List<TeacherCourse> findAll() {
        return getEntityManager().createNamedQuery("TeacherCourse.findAll",TeacherCourse.class).getResultList();
    }
    private static final Logger LOG = Logger.getLogger(TeacherCourseBean.class.getName());
    
    /**
     *
     * @param teacherCourse
     * @param course
     * @param teacher
     */
    public void delete2(TeacherCourse teacherCourse, Course course, Teacher teacher) {
        LOG.info("Removing Course !!" + teacherCourse.getId()+ ":::: " + teacherCourse.getCourse().getCourseName());
           teacherCourse = getEntityManager().getReference(TeacherCourse.class, teacherCourse.getId());
                     
           course = getEntityManager().getReference(Course.class, course.getId());
           course.getTeacherCourses().remove(course);
        
           teacherCourse.getCourse().getTeacherCourses().remove(teacherCourse);
          // LOG.info("After flush 1: " + course.getStudentCourses());
           
           teacher = getEntityManager().getReference(Teacher.class, teacher.getId());
           teacher.getTeacherCourses().remove(teacherCourse);
           //LOG.info("After flush 2: " + student.getStudentCourses());
           
           List<TeacherCourse> teacherCourses =findAll();
           //LOG.info("About to remove student course .." + studentCourse);
           teacherCourses.remove(teacherCourse);
           teacherCourse.getTeacher().getTeacherCourses().remove(teacherCourse);
           getEntityManager().remove(teacherCourse);
           
           //LOG.info("After flush 3: " + studentCourse.getCourse().getCourseName());
           
           //LOG.info("After removing  .." + studentcourses);
           //getEntityManager().flush();
           
    }
}
