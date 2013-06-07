/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.managedbean.moma;

import com.dao.hibernate.UserDao;
import com.entity.moma.User;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ASUS
 */
@ManagedBean
@RequestScoped
public class WelcomeBean {

    String loginUserName;
    String loginUserPassword;
    User user;

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
     * Creates a new instance of RegisterBean
     */
    public WelcomeBean() {
        user = new User();
    }

    public String doRegistration() {
        if ((UserDao.findby_userName(user.getUserName()) == null)
                && UserDao.findby_userEmail(user.getUserEmail()) == null) {
            UserDao.add_user(user);
        }
//        UserDao.add_user_friend("shitVincent","shit");
//        for (User user : UserDao.findby_userName("shit").getUsersForFirstUserId()) {
//            System.out.println(user.getUserName());
//        }
        return "test";
    }

    public String doLogin() {
        System.out.println(loginUserName + " and " + loginUserPassword);
        if (UserDao.loginby_userName_pw(loginUserName, loginUserPassword) == null) {
            System.out.println("LoginFail");
            return "welcome";
        } else {
            System.out.println("LoginSuccessfully");

//            FacesContext facesContext = FacesContext.getCurrentInstance();
//            ExternalContext extContext = facesContext.getExternalContext();
//            HttpSession session = (HttpSession) extContext.getSession(true);
//            session.setAttribute("name", loginUserName);
            
//            Map <String,Object>   sessionParameterMap   =   FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
//            sessionParameterMap.put("name", loginUserName);
            
//            System.out.println(session.getAttribute("name").toString());

            return "test";
        }
    }
}