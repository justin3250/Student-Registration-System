package model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.Student;
import model.Teacher;

@Generated(value="EclipseLink-2.5.0.v20130507-rNA", date="2014-12-12T18:04:55")
@StaticMetamodel(Address.class)
public class Address_ extends CommonEntity_ {

    public static volatile SingularAttribute<Address, String> addressZipCode;
    public static volatile SingularAttribute<Address, String> addressCountry;
    public static volatile SingularAttribute<Address, String> address2;
    public static volatile ListAttribute<Address, Teacher> teachers;
    public static volatile SingularAttribute<Address, String> address1;
    public static volatile ListAttribute<Address, Student> students;
    public static volatile SingularAttribute<Address, String> addressState;
    public static volatile SingularAttribute<Address, String> addressCity;

}