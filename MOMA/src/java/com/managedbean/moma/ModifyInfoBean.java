/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.managedbean.moma;

import com.entity.moma.User;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import org.primefaces.event.FlowEvent;

/**
 *
 * @author ASUS
 */
@ManagedBean
@RequestScoped
public class ModifyInfoBean {

    /**
     * Creates a new instance of ModifyInfoBean
     */
    
    private User user = new User();  
     
    private boolean skip;  
    private static Logger logger = Logger.getLogger(ModifyInfoBean.class.getName());  
    
    public User getUser() {  
        return user;  
    }  
  
    public void setUser(User user) {  
        this.user = user;  
    }  
    
     public void modifyPersonalInfo(ActionEvent actionEvent) {  
        //Persist user  
          
        FacesMessage msg = new FacesMessage("Successful", "Welcome :" + user.getUserRealName());  
        FacesContext.getCurrentInstance().addMessage(null, msg);  
    }  
     
     public boolean isSkip() {  
        return skip;  
    }  
  
    public void setSkip(boolean skip) {  
        this.skip = skip;  
    }  
    
     public String onFlowProcess(FlowEvent event) {  
        logger.info("Current wizard step:" + event.getOldStep());  
        logger.info("Next step:" + event.getNewStep());  
          if(skip) {  
            skip = false;   //reset in case user goes back  
            return "confirm";  
        }  
        else {  
            return event.getNewStep();  
        }  
    }  
        
        
        
    public ModifyInfoBean() {
    }
    
    
    
}
