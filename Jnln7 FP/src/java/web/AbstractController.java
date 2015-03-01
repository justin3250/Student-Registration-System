/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package web;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;

/**
 *
 * @author Justin
 */

public class AbstractController {

    /**
     *
     */
    protected FacesContext facesContext;

    /**
     *
     */
    protected Flash flash;
    
    /**
     *
     */
    public AbstractController() {
    }
 
    @PostConstruct
    private void postConstruct(){
        facesContext = FacesContext.getCurrentInstance();
        flash = facesContext.getExternalContext().getFlash();
    }
    
}
