/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.managedbean.moma;

import com.dao.hibernate.BrochureDao;
import com.dao.hibernate.UserDao;
import com.entity.moma.Brochure;
import com.entity.moma.User;
import java.util.Iterator;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author bianshujun
 */
@ManagedBean
@RequestScoped
public class SearchBean {

    private String searchName;

    public String getSearchName() {
        return searchName;
    }

    public void setSearchName(String searchName) {
        this.searchName = searchName;
    }

    /**
     * Creates a new instance of SearchBean
     */
    public SearchBean() {
    }

    public String doSearch() {
        List<User> userList = UserDao.findby_userRealName(searchName);
        Iterator userIt = userList.iterator();
        //......
        List<Brochure> brochureList = BrochureDao.findby_brochureName(searchName);
        Iterator brochureIt = brochureList.iterator();
        //..
        return "test";
    }

    public void addFriend(String friendName) {
        String userName = FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userName").toString();
        User user = UserDao.findby_userName(userName);
        UserDao.add_user_friend(userName, friendName);
        for (User temp_user : user.getUsersForSecondUserId()) {
            System.out.println(temp_user.getUserName());
        }
    }
    
    public void followBrochure(int BrochureId) {
        String userName = FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userName").toString();
        UserDao.add_user_follow_brochure(userName, BrochureId);
    }
    
}
