/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Hibernate.servlet.com;

import Hibernate.dvdrental.com.UserHelper;
import Hibernate.moma.com.User;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author bianshujun
 */
@WebServlet(name = "ResigterServlet", urlPatterns = {"/ResigterServlet"})
public class ResigterServlet extends HttpServlet {

    private UserHelper userhelper = new UserHelper();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (!isSameEmail(request.getParameter("newUserEmail")) && !isSameUserName(request.getParameter("newUserName")) && !isEmptyInput(request)) {
            if (isTwicePasswordSame(request.getParameter("newUserPassword"), request.getParameter("newUserPasswordConfirm"))) {
                setNewUserInformation(request);
                /*检查UserId
                List<User> userIdList = userhelper.getUser();
                for (User tempEntity : userIdList) {
                    response.getWriter().println(tempEntity.getUserId());
                }
                */
                response.getWriter().println("Database Updated Successfully");
            } else {
                //修改第二行密码输入栏为两次密码不一致
            }
        }

    }

    private void setNewUserInformation(HttpServletRequest request) {
        SessionFactory sessionfactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionfactory.openSession();
        Transaction transaction = session.beginTransaction();
        User user = new User();
        user.setUserRealName("RealName");
        user.setUserId(getNewUserId());
        user.setUserEmail(request.getParameter("newUserEmail"));
        user.setUserName(request.getParameter("newUserName"));
        user.setUserPassword(request.getParameter("newUserPassword"));
        session.save(user);
        transaction.commit();
    }

    private boolean isEmptyInput(HttpServletRequest request){
        if(request.getParameter("newUserEmail") == null &&
           request.getParameter("newUserName") == null &&
           request.getParameter("newUserPassword") == null)
            return true;
        return false;
    }
    
    private boolean isSameEmail(String registerEmail) {
        List<User> userList = userhelper.getUser();
        for (User tempEntity : userList) {
            if (tempEntity.getUserEmail().equals(registerEmail)) {
                return true;
            }
        }
        return false;
    }
    
    private boolean isSameUserName(String registerUserName) {
        List<User> userList = userhelper.getUser();
        for (User tempEntity : userList) {
            if (tempEntity.getUserName().equals(registerUserName)) {
                return true;
            }
        }
        return false;
    }

    private int getNewUserId() {
        int maxUserId = userhelper.getMaxUserId();
        return maxUserId + 1;
    }

    private boolean isTwicePasswordSame(String firstPassword, String secondPassword) {
        if (firstPassword.equals(secondPassword)) {
            return true;
        }
        return false;
    }
}
