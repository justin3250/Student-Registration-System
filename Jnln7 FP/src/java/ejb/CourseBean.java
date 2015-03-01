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
import model.StudentCourse;
import model.TeacherCourse;

/**
 *
 * @author Justin
 * @param <T>
 */
@Named
@Stateless
public class CourseBean<T> extends AbstractBean<Course> {

    /**
     *
     */
    public CourseBean() {
        super(CourseBean.class);
    }

    /**
     *
     * @return
     */
    @Override
    public List<Course> findAll() {
        return getEntityManager().createNamedQuery("Course.findAll", Course.class).getResultList();
    }
    private static final Logger LOG = Logger.getLogger(CourseBean.class.getName());

    @Override
    public void remove(Course course) {

        course = getEntityManager().getReference(Course.class, course.getId());

        for (StudentCourse sc : course.getStudentCourses()) {
            //LOG.info("removing sc :" + sc);
            getEntityManager().remove(sc);
            getEntityManager().flush();
        }

        course.getStudentCourses().remove(course);

        for (TeacherCourse tc : course.getTeacherCourses()) {
               //LOG.info("removing tc :" + tc);
            getEntityManager().remove(tc);
            getEntityManager().flush();
        }

        //LOG.info("removing this getTeacherCourses of course "+ course.getTeacherCourses());
        course.getTeacherCourses().remove(course);

        //LOG.info("Now finally removing from the course table !! ");
        getEntityManager().remove(course);
    }

    @Override
    public void update(Course unmanagedCourseWithUpdates) {
        Course managedCourseWithoutUpdates = getEntityManager().find(Course.class, unmanagedCourseWithUpdates.getId());
        
        managedCourseWithoutUpdates.setCourseName(unmanagedCourseWithUpdates.getCourseName());
        managedCourseWithoutUpdates.setCourseDescription(unmanagedCourseWithUpdates.getCourseDescription());
        
        getEntityManager().merge(managedCourseWithoutUpdates); 
    }

    
}
