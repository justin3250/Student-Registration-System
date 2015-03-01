/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb;

import java.util.List;
import java.util.logging.Logger;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Justin
 * @param <T>
 */

@Named
public abstract class AbstractBean<T> {
    
    @PersistenceContext(unitName = "Jnln7_FP_PU")
    private EntityManager em;

    /**
     *
     * @return
     */
    protected EntityManager getEntityManager(){
        return this.em;
    }
    
    private final Class<T> entityClass;
    
    /**
     *
     * @param entityClass
     */
    protected AbstractBean(Class entityClass) {
        this.entityClass = entityClass;
    }
    
    /**
     *
     * @param entity
     */
    public void create(T entity){
        em.persist(entity);
    }
    
    /**
     *
     * @param entity
     */
    public void update(T entity){
        em.merge(entity);
    }
    private static final Logger LOG = Logger.getLogger(AbstractBean.class.getName());
    
    /**
     *
     * @param entity
     */
    public void remove(T entity){
        LOG.info("Inside Abs bean remove method");
        em.remove(em.merge(entity));
    }
    
    /**
     *
     * @param id
     * @return
     */
    public T find(Object id){
        return em.find(entityClass, id);
    }
    
    /**
     *
     * @return
     */
    public abstract List<T> findAll();
}
