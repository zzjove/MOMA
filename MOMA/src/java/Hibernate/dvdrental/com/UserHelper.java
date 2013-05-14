/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Hibernate.dvdrental.com;

import Hibernate.moma.com.HibernateUtil;
import Hibernate.moma.com.User;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author bianshujun
 */
public class UserHelper {

    Session session = null;

    public UserHelper() {
        this.session = HibernateUtil.getSessionFactory().getCurrentSession();
    }

    public List getUser() {
        List<User> UserList = null;
        try {
            org.hibernate.Transaction tx = session.beginTransaction();
            Query q = session.createQuery("from User");
            UserList = (List<User>) q.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return UserList;
    }
    
    public User getUserByUserName(String userName){
        User user = null;
        try {
            org.hibernate.Transaction tx = session.beginTransaction();
            Query q = session.createQuery("from User as user where user.user_name=" + "'"+userName+"'");
            user = (User) q.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }
    
    public int getMaxUserId(){
        User user = new User();
        try {
            org.hibernate.Transaction tx = session.beginTransaction();
            Query q = session.createQuery("select max(user_id )from User as user_id");
            user = (User)q.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user.getUserId();
    }
}
