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
import model.StudentCourse;

/**
 *
 * @author Justin
 * @param <T>
 */
@Named
@Stateless
public class StudentBean<T> extends AbstractBean<Student> {
    @EJB
    private AddressBean addressBean;

    /**
     *
     */
    public StudentBean() {
        super(Student.class);
    }

    /**
     *
     * @return
     */
    @Override
    public List<Student> findAll() {
        return getEntityManager().createNamedQuery("Student.findAll",Student.class).getResultList();
    }
    
    /**
     *
     * @param username
     * @return
     */
    public Student findByUser(String username){
        
        //TypedQuery<Student> query= getEntityManager().createNamedQuery("Student.findByUsername" , Student.class);
        //query.setParameter("userName",username );
        //query.getSingleResult();
        return getEntityManager().createNamedQuery("Student.findByUsername" , Student.class).
                setParameter("userName", username).getSingleResult();
    }
    private static final Logger LOG = Logger.getLogger(StudentBean.class.getName());

    @Override
    public void update(Student unmanagedStudentWithUpdates) {
        
        Student managedStudentWithoutUpdates = getEntityManager().find(Student.class, unmanagedStudentWithUpdates.getId());
        //LOG.info("Updating student information to: "+ unmanagedStudentWithUpdates.getAddress());
        //LOG.info("Old Data is : " + managedStudentWithoutUpdates);
        Address oldAddress = managedStudentWithoutUpdates.getAddress();
        List<StudentCourse> oldsc = managedStudentWithoutUpdates.getStudentCourses();
        //LOG.info("Old address : "+oldAddress);
        //Address newAddress = getEntityManager().find(Address.class, unmanagedStudentWithUpdates.getAddress().getId());
        Address newAddress = unmanagedStudentWithUpdates.getAddress();
        addressBean.update(newAddress);
        
        List<StudentCourse> newsc = unmanagedStudentWithUpdates.getStudentCourses();
        if(!oldAddress.equals(newAddress)){
            LOG.info("old address is not equal to new address - making a switch !! ");
            oldAddress.getStudents().remove(managedStudentWithoutUpdates);
            
        }
      
        
        
        managedStudentWithoutUpdates.setFirstName(unmanagedStudentWithUpdates.getFirstName());
        managedStudentWithoutUpdates.setLastName(unmanagedStudentWithUpdates.getLastName());
        managedStudentWithoutUpdates.setGender(unmanagedStudentWithUpdates.getGender());
        managedStudentWithoutUpdates.setContactNumber(unmanagedStudentWithUpdates.getContactNumber());
        managedStudentWithoutUpdates.setAddress(newAddress);
        managedStudentWithoutUpdates.setStudentCourses(newsc);
        LOG.info("New address : "+newAddress);
        LOG.info("New Stu Course : "+newsc);
        getEntityManager().merge(managedStudentWithoutUpdates); 
        LOG.info("Managed Bean after updatee : " + managedStudentWithoutUpdates);
    }
    
    
}
