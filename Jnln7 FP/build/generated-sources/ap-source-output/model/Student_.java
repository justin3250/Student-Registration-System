package model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.Address;
import model.StudentCourse;
import model.security.User;

@Generated(value="EclipseLink-2.5.0.v20130507-rNA", date="2014-12-12T18:04:55")
@StaticMetamodel(Student.class)
public class Student_ extends CommonEntity_ {

    public static volatile SingularAttribute<Student, String> firstName;
    public static volatile SingularAttribute<Student, String> lastName;
    public static volatile SingularAttribute<Student, Address> address;
    public static volatile SingularAttribute<Student, String> gender;
    public static volatile SingularAttribute<Student, String> contactNumber;
    public static volatile SingularAttribute<Student, Date> dateOfBirth;
    public static volatile SingularAttribute<Student, User> user;
    public static volatile ListAttribute<Student, StudentCourse> studentCourses;

}