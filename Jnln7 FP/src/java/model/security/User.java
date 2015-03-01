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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author Justin
 */
@Entity
@NamedQuery(name = "User.findAll" , query = "select u from User u")
@Table(name="sec_user")
public class User implements Serializable{
    
    @Id
    private String userName;
    private String password;

    @ManyToMany
    @JoinTable(name = "sec_user_group" , 
            joinColumns = @JoinColumn(name = "USERNAME"),
             inverseJoinColumns = @JoinColumn(name = "GROUPNAME")   )
    private List<Group> groups = new ArrayList<Group>();

    /**
     *
     * @param g
     */
    public void addGroup(Group g){
        if(! this.groups.contains(g))
            this.groups.add(g);
        if(! g.getUsers().contains(this) )
            g.getUsers().add(this);
    }

    /**
     *
     * @param userName
     * @param password
     */
    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    /**
     *
     */
    public User() {
    }
    
    
    /**
     * Get the value of groups
     *
     * @return the value of groups
     */
    public List<Group> getGroups() {
        return groups;
    }

    /**
     * Set the value of groups
     *
     * @param groups new value of groups
     */
    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }

    /**
     * Get the value of password
     *
     * @return the value of password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Set the value of password
     *
     * @param password new value of password
     */
    public void setPassword(String password) {
        this.password = password;
    }


    /**
     * Get the value of userName
     *
     * @return the value of userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Set the value of userName
     *
     * @param userName new value of userName
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    @PrePersist
    @PreUpdate
    private void hashPassword(){
        String md5Password = DigestUtils.md5Hex(this.password);
        this.password = md5Password;
    }
}
