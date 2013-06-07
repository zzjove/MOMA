/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.managedbean.moma;

import com.dao.hibernate.UserDao;
import com.entity.moma.User;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author bianshujun
 */
@ManagedBean
@RequestScoped
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
    }

    public String doLogin() {
        System.out.println(loginUserName + " and " + loginUserPassword);
        if (UserDao.loginby_userName_pw(loginUserName, loginUserPassword) == null) {
            System.out.println("LoginFail");
            return "welcome";
        } else {
            System.out.println("LoginSuccessfully");
            user = UserDao.findby_userName(loginUserName);
            if ("RealName".equals(user.getUserRealName())) {
                
                FacesContext.getCurrentInstance().getExternalContext().getSession(true);
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("userName", loginUserName);

//                String testSession = FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userName").toString();
//                System.out.println(testSession);

                return "userInfoCompletion";
            } else {
                return "test";
            }
        }
    }
}
