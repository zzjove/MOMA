/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.managedbean.moma;

import com.entity.moma.User;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author ASUS
 */
@ManagedBean
@RequestScoped
public class TestBean {

    
    private User user1,user2,user3;
    
    private List<User> userFriendList;
    
    public List<User> getUserFriendList() {
        return userFriendList;
    }

    public void setUserFriendSet(List<User> userFriendList) {
        this.userFriendList = userFriendList;
    }
    
    
    /**
     * Creates a new instance of testBean
     */
    public TestBean() {
        user1 = new User();
        user2 = new User();
        user3 = new User();
        user1.setUserName("周壮");
        user2.setUserName("球球");
        user3.setUserName("赵青");
        
        user1.setUserPortraitUrl("img/portrait.png");
        user2.setUserPortraitUrl("img/portrait.png");
        user3.setUserPortraitUrl("img/portrait.png");
        userFriendList = new ArrayList<User>();
        userFriendList.add(user1);
        userFriendList.add(user2);
        userFriendList.add(user3);
       
    }
    
    public String doRedirect() {
        return "welcome";
    }
    
   
    
    
}
