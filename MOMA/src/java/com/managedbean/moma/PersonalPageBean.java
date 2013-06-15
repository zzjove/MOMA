/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.managedbean.moma;

import com.dao.hibernate.UserDao;
import com.entity.moma.Brochure;
import com.entity.moma.User;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.FlowEvent;

/**
 *
 * @author bianshujun
 */
@ManagedBean
@RequestScoped
public class PersonalPageBean {

    private User user;
    private Brochure brochure;
    private boolean skip;
    private static Logger logger = Logger.getLogger(PersonalPageBean.class.getName());

    public Brochure getBrochure() {
        return brochure;
    }

    public void setBrochure(Brochure brochure) {
        this.brochure = brochure;
    }

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        PersonalPageBean.logger = logger;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Creates a new instance of PersonalPageBean
     */
    public PersonalPageBean() {
        String userName = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("personalSpaceUserName");
        if (userName == null) {
            userName = FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userName").toString();
        }
        System.out.println(userName);
        user = UserDao.findby_userName(userName);
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
        if (skip) {
            skip = false;   //reset in case user goes back  
            return "confirm";
        } else {
            return event.getNewStep();
        }
    }

    public void modifyPersonalInfo() {
        System.out.println("InDoModify");
        System.out.println(user.getUserRealName());
        UserDao.modify_user(user);
    }
    
}
