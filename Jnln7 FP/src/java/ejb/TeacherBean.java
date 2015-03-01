/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb;

import java.util.List;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Named;
import model.Address;
import model.Student;
import model.Teacher;
import model.TeacherCourse;

/**
 *
 * @author Justin
 */
@Named
@Stateless
public class TeacherBean extends AbstractBean<Teacher>{
    @EJB
    private AddressBean addressBean;

    
    /**
     *
     */
    public TeacherBean() {
        super(Teacher.class);
    }

    /**
     *
     * @return
     */
    @Override
    public List<Teacher> findAll() {
        return getEntityManager().createNamedQuery("Teacher.findAll",Teacher.class).getResultList();
    }
    
    /**
     *
     * @param username
     * @return
     */
    public Teacher findByUser(String username){
        
        return getEntityManager().createNamedQuery("Teacher.findByUsername" , Teacher.class).
                setParameter("userName", username).getSingleResult();
    }
    private static final Logger LOG = Logger.getLogger(TeacherBean.class.getName());

    @Override
    public void update(Teacher unmanagedTeacherWithUpdates) {
         //LOG.info("Updating student information to: "+ unmanagedTeacherWithUpdates.getAddress());
        Teacher managedTeacherWithoutUpdates = getEntityManager().find(Teacher.class, unmanagedTeacherWithUpdates.getId());
        //LOG.info("Old Data is : " + managedTeacherWithoutUpdates.getName());
        Address oldAddress = managedTeacherWithoutUpdates.getAddress();
        //LOG.info("Old address : "+oldAddress);
        //Address newAddress = getEntityManager().find(Address.class, unmanagedStudentWithUpdates.getAddress().getId());
        Address newAddress = unmanagedTeacherWithUpdates.getAddress();
        addressBean.update(newAddress);
         List<TeacherCourse> newtc = unmanagedTeacherWithUpdates.getTeacherCourses();
        
        if(!oldAddress.equals(newAddress)){
            LOG.info("old address is not equal to new address - making a switch !! ");
            oldAddress.getTeachers().remove(managedTeacherWithoutUpdates);
            
        }
      
        managedTeacherWithoutUpdates.setName(unmanagedTeacherWithUpdates.getName());
        managedTeacherWithoutUpdates.setAddress(newAddress);
        managedTeacherWithoutUpdates.setTeacherCourses(newtc);
        LOG.info("New address : "+newAddress);
        getEntityManager().merge(managedTeacherWithoutUpdates); 
        LOG.info("Managed Bean after updatee : " + managedTeacherWithoutUpdates.getName());
    }
    
    
    
    
}
