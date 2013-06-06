/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.managedbean.moma;

import com.dao.hibernate.UserDao;
import com.entity.moma.User;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author bianshujun
 */
@ManagedBean
@RequestScoped
public class RegisterBean {
    
    public User user;
    
    public RegisterBean() {
        user = new User();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    /**
     * Creates a new instance of WelcomeManagedBean
     */
    
    public void doRegistration() {
        if (UserDao.findby_userName(user.getUserName()) == null 
                && UserDao.findby_userEmail(user.getUserEmail()) == null) {
            UserDao.add_user(user);
        }
    }
}
