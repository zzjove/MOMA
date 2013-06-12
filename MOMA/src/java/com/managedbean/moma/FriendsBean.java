/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.managedbean.moma;

import com.dao.hibernate.UserDao;
import com.entity.moma.User;
import java.util.List;
import java.util.Set;
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
    
    /**
     * Creates a new instance of FriendsBean
     */
    public FriendsBean() {
        String userName = FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userName").toString();
        user = UserDao.findby_userName(userName);
    }
    
    public Set<User> userFriends() {
        return user.getUsersForSecondUserId();
    }
}
