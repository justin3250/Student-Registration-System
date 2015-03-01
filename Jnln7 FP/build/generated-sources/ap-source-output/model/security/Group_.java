package model.security;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.security.User;

@Generated(value="EclipseLink-2.5.0.v20130507-rNA", date="2014-12-12T18:04:55")
@StaticMetamodel(Group.class)
public class Group_ { 

    public static volatile SingularAttribute<Group, String> groupName;
    public static volatile SingularAttribute<Group, String> groupDescription;
    public static volatile ListAttribute<Group, User> users;

}