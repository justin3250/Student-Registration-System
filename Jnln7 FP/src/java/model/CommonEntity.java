/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.util.Date;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;

/**
 *
 * @author Justin
 */
@MappedSuperclass
public class CommonEntity {

    /**
     *
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
        private Date activityDate;

    /**
     * Get the value of activityDate
     *
     * @return the value of activityDate
     */
    public Date getActivityDate() {
        return activityDate;
    }

    /**
     * Set the value of activityDate
     *
     * @param activityDate new value of activityDate
     */
    public void setActivityDate(Date activityDate) {
        this.activityDate = activityDate;
    }

    /**
     *
     * @return
     */
    public Long getId() {
        return id;
    }

    /**
     *
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     *
     */
    public CommonEntity() {
    }
    
    @PrePersist
    @PreUpdate
    private void doActivityDate(){
        this.activityDate = new Date();
    }
}
