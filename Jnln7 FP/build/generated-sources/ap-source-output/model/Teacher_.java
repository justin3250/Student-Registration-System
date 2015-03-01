package model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.Address;
import model.TeacherCourse;
import model.security.User;

@Generated(value="EclipseLink-2.5.0.v20130507-rNA", date="2014-12-12T18:04:55")
@StaticMetamodel(Teacher.class)
public class Teacher_ extends CommonEntity_ {

    public static volatile SingularAttribute<Teacher, Address> address;
    public static volatile SingularAttribute<Teacher, String> name;
    public static volatile ListAttribute<Teacher, TeacherCourse> teacherCourses;
    public static volatile SingularAttribute<Teacher, User> user;

}