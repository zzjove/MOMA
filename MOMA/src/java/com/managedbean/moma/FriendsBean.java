/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.managedbean.moma;

import com.dao.hibernate.FriendsDao;
import com.dao.hibernate.UserDao;
import com.entity.moma.User;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author bianshujun
 */
@ManagedBean
@RequestScoped
public class FriendsBean {
    User user ;

    
    private List<User> userFriendList;
    
    public List<User> getUserFriendList() {
        return userFriendList;
    }

    public void setUserFriendSet(List<User> userFriendList) {
        this.userFriendList = userFriendList;
    }
    /**
     * Creates a new instance of FriendsBean
     */
    public FriendsBean() {
        String userName = FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userName").toString();
        user = UserDao.findby_userName(userName);
        userFriendList = new ArrayList(user.getUsersForFirstUserId());
        System.out.println("userFriendList is " + userFriendList.size());
    }
    
    public void doRedirect() {
        System.out.println
                ("inDoredirect");
        String name = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("userName");
        System.out.println(name);
    }
    
    
}
