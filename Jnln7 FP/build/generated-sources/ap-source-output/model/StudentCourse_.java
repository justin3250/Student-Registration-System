package model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.Course;
import model.Student;

@Generated(value="EclipseLink-2.5.0.v20130507-rNA", date="2014-12-12T18:04:55")
@StaticMetamodel(StudentCourse.class)
public class StudentCourse_ extends CommonEntity_ {

    public static volatile SingularAttribute<StudentCourse, Student> student1;
    public static volatile SingularAttribute<StudentCourse, String> homeWork4;
    public static volatile SingularAttribute<StudentCourse, String> homeWork2;
    public static volatile SingularAttribute<StudentCourse, String> homeWork3;
    public static volatile SingularAttribute<StudentCourse, String> homeWork1;
    public static volatile SingularAttribute<StudentCourse, String> finalHomeWork;
    public static volatile SingularAttribute<StudentCourse, Course> course1;

}