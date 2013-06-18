/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.managedbean.moma;

import com.dao.hibernate.BrochureDao;
import com.dao.hibernate.UserDao;
import com.entity.moma.Brochure;
import com.entity.moma.User;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author bianshujun
 */
@ManagedBean
@RequestScoped
public class SearchListBean {

    private ArrayList<User>searchUserList = new ArrayList();
    private ArrayList<Brochure>searchBrochureList = new ArrayList();
    private int searchUserCount;
    private int searchBrochureCount;
    private String searchName;

    public ArrayList<User> getSearchUserList() {
        return searchUserList;
    }

    public void setSearchUserList(ArrayList<User> searchUserList) {
        this.searchUserList = searchUserList;
    }

    public ArrayList<Brochure> getSearchBrochureList() {
        return searchBrochureList;
    }

    public void setSearchBrochureList(ArrayList<Brochure> searchBrochureList) {
        this.searchBrochureList = searchBrochureList;
    }

    public int getSearchUserCount() {
        return searchUserCount;
    }

    public void setSearchUserCount(int searchUserCount) {
        this.searchUserCount = searchUserCount;
    }

    public int getSearchBrochureCount() {
        return searchBrochureCount;
    }

    public void setSearchBrochureCount(int searchBrochureCount) {
        this.searchBrochureCount = searchBrochureCount;
    }

    public String getSearchName() {
        return searchName;
    }

    public void setSearchName(String searchName) {
        this.searchName = searchName;
    }
    
    

    /**
     * Creates a new instance of SearchBean
     */
    public SearchListBean() {
        System.out.println("In searchList bean");
        FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        searchName = FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("searchName").toString();    
        System.out.println("searchName is " + searchName);
        searchUserList = (ArrayList<User>) UserDao.findby_userRealName(searchName);
        searchBrochureList = (ArrayList<Brochure>) BrochureDao.findby_brochureName(searchName);
        searchUserCount = searchUserList.size();
        searchBrochureCount = searchBrochureList.size();
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
