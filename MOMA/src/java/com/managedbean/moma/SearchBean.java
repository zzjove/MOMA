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
public class SearchBean {
    
    private String searchName;

    public String getSearch() {
        return searchName;
    }

    public void setSearch(String search) {
        this.searchName = search;
    }
    /**
     * Creates a new instance of SearchBean
     */
    public SearchBean() {
    }
    
    public String searchConfirm() {
        User searchUser = UserDao.findby_userName(searchName);
        return "searchAnswer";
    }
}
