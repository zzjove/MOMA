/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao.hibernate;

import com.entity.moma.User;
import java.util.Iterator;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author bianshujun
 */
public class UserDao {

    public static User findby_userId(int userId) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        
        String sql = "select * from User where use_id=";
        List userList = session.createSQLQuery(sql + userId + ";")
                .addEntity(User.class).list();
 
        session.close();

        Iterator it = userList.iterator();
        if (it.hasNext()) {
            User userTemp = (User) it.next();
            return userTemp;
        } else {
            return null;
        }
    }
    
    public static User findby_userName(String userName) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();

        String hql="from User where user_name=:userName";
        Query query = session.createQuery(hql);
        query.setString("userName",userName);
        List userList = (List<User>) query.list();
        
        session.close();

        Iterator it = userList.iterator();
        if (it.hasNext()) {
            User userTemp = (User) it.next();
            return userTemp;
        } else {
            return null;
        }
    }
        
        
    public static User findby_userEmail(String userEmail) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();

        String hql="from User where user_email=:userEmail";
        Query query = session.createQuery(hql);
        query.setString("userEmail",userEmail);
        List userList = (List<User>) query.list();

        session.close();

        Iterator it = userList.iterator();
        if (it.hasNext()) {
            User userTemp = (User) it.next();
            return userTemp;
        } else {
            return null;
        }
    }

    public static int getMaxUserId() {
        
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();  
        Transaction transaction = session.beginTransaction();
        int maxUserId = (Integer) session.createQuery(
				"select max(user.userId) from User user").uniqueResult();
        return maxUserId;
    }
    
    
    public static void add_user(User user) {
        
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        
        if (user.getUserRealName() == null) {
            user.setUserRealName("realName");
        }
        session.save(user);
        session.flush();

        transaction.commit();
    }
    
    public static void modify_user(User user) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        
//        session.merge(user);
        session.update(user);
        session.flush();
        
        transaction.commit();
    } 
    
    public static User loginby_userName_pw(String userName, String password) {

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        
//        String sql = "select * from User where user_name=";
//        List userList = session.createSQLQuery(sql + userName + ";")
//                .addEntity(User.class).list();
        
        String hql="from User where user_name=:userName";
        Query query = session.createQuery(hql);
        query.setString("userName",userName);
        List userList = (List<User>) query.list();

        session.close();
        
        Iterator it = userList.iterator();
        if (it.hasNext()) {
            User customer_temp = (User) it.next();
            if (customer_temp.getUserPassword().equals(password)) {
                return customer_temp;
            }
        }
        return null;
    }


}
