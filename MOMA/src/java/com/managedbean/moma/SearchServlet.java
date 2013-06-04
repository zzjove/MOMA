/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.managedbean.moma;

import com.dao.hibernate.UserHelper;
import com.entity.moma.User;
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

    private PersonalPageServlet personalPage;
    private String userNametoSearch = null;
    private UserHelper userhelper = new UserHelper();
    private String relatedFriend = null;

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
        personalPage = (PersonalPageServlet) request.getSession().getAttribute("currentPersonalPageforSearch");

        userNametoSearch = request.getParameter("SearchName");

        if (isSameUserName(userNametoSearch)) {
            if (!isSameFriend(userNametoSearch)) {
                List<User> allUsers = userhelper.getUser();
                for (User tempEntity : allUsers) {
                    if (tempEntity.getUserName().equals(userNametoSearch)) {
                        personalPage.addFriend(tempEntity);
                    }
                }
                response.getWriter().println(personalPage.getUserofPage().getUserName() + " add " + userNametoSearch + " as a friend successfully");
                findRelatedFriend(userNametoSearch);
                response.getWriter().println(relatedFriend);
            } else {
                response.getWriter().println("He/She is already your friend");
            }
        } else {
            response.getWriter().println("There is no such user");
        }

        for (User tempUser : personalPage.getUserofPage().getUsersForSecondUserId()) {
            response.getWriter().println("Now you have friends:" + tempUser.getUserName());
        }

        for (User tempUser : personalPage.getUserofPage().getUsersForFirstUserId()) {
            response.getWriter().println("Now you have friends:" + tempUser.getUserName());
        }

        //request.setAttribute("searchUserList", personalPage);
        //request.getRequestDispatcher("SearchFriend.jsp").forward(request, response);
    }

    private boolean isSameFriend(String friendName) {
        for (User tempEntity : personalPage.getUserofPage().getUsersForSecondUserId()) {
            if (tempEntity.getUserName().equals(friendName)) {
                return true;
            }
        }

        for (User tempEntity : personalPage.getUserofPage().getUsersForFirstUserId()) {
            if (tempEntity.getUserName().equals(friendName)) {
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

    private void findRelatedFriend(String registerUserName) {
        List<User> userList = userhelper.getUser();
        for (User tempEntity : userList) {
            if (tempEntity.getUserName().equals(registerUserName)) {
                for (User tempUser : tempEntity.getUsersForFirstUserId()) {
                    relatedFriend = tempUser.getUserName();
                }
            }
        }
    }
}
