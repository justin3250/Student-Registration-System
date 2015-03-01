/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb;

import java.util.List;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Named;
import model.Address;

/**
 *
 * @author Justin
 */
@Named
@Stateless
public class AddressBean extends AbstractBean<Address>{
    
    /**
     *
     */
    public AddressBean() {
        super(Address.class);
    }

    /**
     *
     * @return
     */
    @Override
    public List<Address> findAll() {
        return getEntityManager().createNamedQuery("Address.findAll",Address.class).getResultList();
    }
    private static final Logger LOG = Logger.getLogger(AddressBean.class.getName());

    @Override
    public void update(Address unmanagedAddressWithUpdates) {
        
        Address managedAddressWithoutUpdates = getEntityManager().find(Address.class, unmanagedAddressWithUpdates.getId());
        
        managedAddressWithoutUpdates.setAddress1(unmanagedAddressWithUpdates.getAddress1());
        managedAddressWithoutUpdates.setAddress2(unmanagedAddressWithUpdates.getAddress2());
        managedAddressWithoutUpdates.setAddressCity(unmanagedAddressWithUpdates.getAddressCity());
        managedAddressWithoutUpdates.setAddressCountry(unmanagedAddressWithUpdates.getAddressCountry());
        managedAddressWithoutUpdates.setAddressState(unmanagedAddressWithUpdates.getAddressState());
        managedAddressWithoutUpdates.setAddressZipCode(unmanagedAddressWithUpdates.getAddressZipCode());
        
        getEntityManager().merge(managedAddressWithoutUpdates); 
        
        LOG.info("Address bean after update : " + managedAddressWithoutUpdates);
    }
 
    
    
}
