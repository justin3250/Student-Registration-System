package model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.Course;
import model.Teacher;

@Generated(value="EclipseLink-2.5.0.v20130507-rNA", date="2014-12-12T18:04:55")
@StaticMetamodel(TeacherCourse.class)
public class TeacherCourse_ extends CommonEntity_ {

    public static volatile SingularAttribute<TeacherCourse, Teacher> teacher;
    public static volatile SingularAttribute<TeacherCourse, Course> course;

}