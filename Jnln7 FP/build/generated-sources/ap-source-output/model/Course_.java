package model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.StudentCourse;
import model.TeacherCourse;

@Generated(value="EclipseLink-2.5.0.v20130507-rNA", date="2014-12-12T18:04:55")
@StaticMetamodel(Course.class)
public class Course_ extends CommonEntity_ {

    public static volatile SingularAttribute<Course, String> courseName;
    public static volatile ListAttribute<Course, TeacherCourse> teacherCourses;
    public static volatile SingularAttribute<Course, String> courseDescription;
    public static volatile ListAttribute<Course, StudentCourse> studentCourses;

}