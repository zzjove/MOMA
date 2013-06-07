/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.managedbean.moma;

import com.dao.hibernate.UserDao;
import com.entity.moma.User;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author bianshujun
 */
@ManagedBean
@SessionScoped
public class PersonalPageBean {

    private String loginUserName;
    private String loginUserPassword;
    private User user;

    public String getLoginUserName() {
        return loginUserName;
    }

    public void setLoginUserName(String loginUserName) {
        this.loginUserName = loginUserName;
    }

    public String getLoginUserPassword() {
        return loginUserPassword;
    }

    public void setLoginUserPassword(String loginUserPassword) {
        this.loginUserPassword = loginUserPassword;
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
//        FacesContext facesContext = FacesContext.getCurrentInstance();
//        ExternalContext extContext = facesContext.getExternalContext();
//        HttpSession session = (HttpSession) extContext.getSession(true);
//        String user = (String) session.getAttribute("userName");
        
//        String name = FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("name").toString();
//        System.out.println(name);
    }
    
        public String doLogin() {
        System.out.println(loginUserName + " and " + loginUserPassword);
        if (UserDao.loginby_userName_pw(loginUserName, loginUserPassword) == null) {
            System.out.println("LoginFail");
            return "welcome";
        } else {
            System.out.println("LoginSuccessfully");
            user = UserDao.findby_userName(loginUserName);
            return "test";
        }
    }
}
