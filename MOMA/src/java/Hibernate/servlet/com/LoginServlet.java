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
@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {

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
      //  if (isRightPassword(request)) {
            request.getRequestDispatcher("PersonalSpace.html").forward(request, response);
        //}
       //User userByName = userhelper.getUserByUserName("shitVincent");
       //response.getWriter().println(userByName.getUserPassword());
    }

    private boolean isRightPassword(HttpServletRequest request) {
        User userByName = userhelper.getUserByUserName(request.getParameter("userName"));
        if (userByName.getUserPassword().equals(request.getParameter("userPassword"))) {
            return true;
        }
        return false;
    }
}
