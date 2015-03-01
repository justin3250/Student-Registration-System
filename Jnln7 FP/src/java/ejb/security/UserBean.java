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
import model.security.User;

/**
 *
 * @author Justin
 */
@Named
@Stateless
public class UserBean extends AbstractBean<User>{

    /**
     *
     */
    public UserBean() {
        super(User.class);
    }
     
    /**
     *
     * @return
     */
    @Override
    public List<User> findAll() {
        return getEntityManager().createNamedQuery("User.findAll",User.class).getResultList();       
    }
    
}
