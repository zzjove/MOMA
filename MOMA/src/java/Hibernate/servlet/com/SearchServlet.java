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

@WebServlet(name = "SearchServlet", urlPatterns = {"/SearchServlet"})
public class SearchServlet extends HttpServlet {

    private PersonalPageServlet personalPage = null;
    private String userNametoSearch = null;
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
        userNametoSearch = request.getParameter("SearchName");
        List<User> allUsers =  userhelper.getUser();
        for(User tempEntity: allUsers){
            if (tempEntity.getUserName().equals(userNametoSearch)){
                personalPage.addFriend(tempEntity);
            }
        }
        
        //request.setAttribute("searchUserList", personalPage);
        //request.getRequestDispatcher("SearchFriend.jsp").forward(request, response);
    }

}
