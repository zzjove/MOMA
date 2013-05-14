/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Hibernate.servlet.com;

import Hibernate.moma.com.Brochure;
import Hibernate.moma.com.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author bianshujun
 */
public class PersonalPageServlet {

    private User userofPage = null;

    public PersonalPageServlet(User user) {
        if (userofPage == null) {
            userofPage = user;
        }
    }

    public int getPersonalPageUserId() {
        return this.userofPage.getUserId();
    }
    
    public User getuserofPage(){
        return this.userofPage;
    }

    public void addFriend(User friendUser) {
        SessionFactory sessionfactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionfactory.openSession();
        Transaction transaction = session.beginTransaction();
        userofPage.addFriend(friendUser);
        transaction.commit();
    }

    public void removeFriend(User friendUser) {
        SessionFactory sessionfactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionfactory.openSession();
        Transaction transaction = session.beginTransaction();
        userofPage.removeFriend(friendUser);
        transaction.commit();
    }

    public void addBrochure(Brochure userBrochure) {
        SessionFactory sessionfactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionfactory.openSession();
        Transaction transaction = session.beginTransaction();
        userofPage.addBrochure(userBrochure);
        transaction.commit();
    }
}
