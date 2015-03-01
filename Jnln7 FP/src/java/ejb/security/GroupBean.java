/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb.security;

import ejb.AbstractBean;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Named;
import model.security.Group;

/**
 *
 * @author Justin
 */
@Named
@Stateless
public class GroupBean extends AbstractBean<Group>{

    /**
     *
     */
    public GroupBean() {
        super(Group.class);
    }

    /**
     *
     * @return
     */
    @Override
    public List<Group> findAll() {
     return getEntityManager().createNamedQuery("Group.findAll",Group.class).getResultList();
    }
    
}
