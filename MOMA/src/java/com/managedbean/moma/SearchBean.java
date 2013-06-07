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
        
        return "test";
    }
}
