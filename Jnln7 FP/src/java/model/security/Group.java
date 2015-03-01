/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model.security;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Justin
 */
@Entity
@NamedQuery(name = "Group.findAll" , query = "select g from Group g")
@Table(name="sec_group")
public class Group implements Serializable {
    
    @Id
    private String groupName;
    private String groupDescription;
    
    @ManyToMany(mappedBy = "groups")
    private List<User> users = new ArrayList<User>();
    
    /**
     *
     * @param u
     */
    public void addUser(User u){
        if(! this.users.contains(u))
            this.users.add(u);
        if(! u.getGroups().contains(this) )
            u.getGroups().add(this);
    }

    /**
     *
     * @param groupName
     * @param groupDescription
     */
    public Group(String groupName, String groupDescription) {
        this.groupName = groupName;
        this.groupDescription = groupDescription;
    }

    /**
     *
     */
    public Group() {
    }
    
    
    /**
     * Get the value of users
     *
     * @return the value of users
     */
    public List<User> getUsers() {
        return users;
    }

    /**
     * Set the value of users
     *
     * @param users new value of users
     */
    public void setUsers(List<User> users) {
        this.users = users;
    }


    /**
     * Get the value of groupDescription
     *
     * @return the value of groupDescription
     */
    public String getGroupDescription() {
        return groupDescription;
    }

    /**
     * Set the value of groupDescription
     *
     * @param groupDescription new value of groupDescription
     */
    public void setGroupDescription(String groupDescription) {
        this.groupDescription = groupDescription;
    }

    /**
     * Get the value of groupName
     *
     * @return the value of groupName
     */
    public String getGroupName() {
        return groupName;
    }

    /**
     * Set the value of groupName
     *
     * @param groupName new value of groupName
     */
    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

}
