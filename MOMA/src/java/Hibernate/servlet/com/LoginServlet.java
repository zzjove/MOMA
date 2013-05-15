/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Hibernate.servlet.com;

import Hibernate.dao.com.UserHelper;
import Hibernate.moma.com.User;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author bianshujun
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {

    private UserHelper userhelper = new UserHelper();
    private User currentLoginUser = null;
    private PersonalPageServlet currentPersonalPage= null;
    private ArrayList<PersonalPageServlet> personalPageList = new ArrayList<PersonalPageServlet>();

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
        if (isRightPassword(request)) {
            if (!hasSamePersonPage()){
                currentPersonalPage = new PersonalPageServlet(currentLoginUser);
                personalPageList.add(currentPersonalPage);
            }
            request.setAttribute("currentPersonalPage", currentPersonalPage);
            //response.getWriter().println(currentPersonalPage.getuserofPage().getUserName());
            request.getRequestDispatcher("PersonalSpace.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("index.html").forward(request, response);
        }
    }

    private boolean hasSamePersonPage() {
        for (PersonalPageServlet tempEntity : personalPageList) {
            if (tempEntity.getPersonalPageUserId() == currentLoginUser.getUserId()) {
                currentPersonalPage = tempEntity;
                return true;
            }
        }
        return false;
    }

    private boolean isRightPassword(HttpServletRequest request) {
        List<User> userList = userhelper.getUser();
        for (User userEntity : userList) {
            if (userEntity.getUserName().equals(request.getParameter("userName"))) {
                if (userEntity.getUserPassword().equals(request.getParameter("userPassword"))) {
                    currentLoginUser = userEntity;
                    return true;
                }
            }
        }
        return false;
    }
}
