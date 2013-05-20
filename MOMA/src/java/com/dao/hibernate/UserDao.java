/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao.hibernate;

import Hibernate.moma.com.HibernateUtil;
import Hibernate.moma.com.User;
import java.util.Iterator;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author ZZ
 */
public class UserDao {
    //通过userid查找user
    public static User findUserByUserId(int userId){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "select * from user where user_id=";
        List userList = session.createSQLQuery(sql+userId+";").addEntity(User.class).list();
        session.close();
        
        Iterator it = userList.iterator();
        if(it.hasNext()){
            User userTemp = (User)it.next();
            return userTemp;
        }
        else
            return null;
    }
}
