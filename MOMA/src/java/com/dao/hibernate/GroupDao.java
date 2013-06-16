/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao.hibernate;

import com.entity.moma.Diary;
import com.entity.moma.GroupOwner;
import com.entity.moma.User;
import java.util.Iterator;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author bianshujun
 */
public class GroupDao {

    public static List<GroupOwner> findby_ownerId(int userId) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();

        String sql = "select * from GroupOwner where owner_id=";
        List groupOwnerList = session.createSQLQuery(sql + userId + ";")
                .addEntity(Diary.class).list();

        session.close();

        return groupOwnerList;
    }

    public static void add_group(GroupOwner groupOwner) {

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();

        session.save(groupOwner);
        session.flush();

        transaction.commit();
    }

    public static void modify_user(GroupOwner groupOwner) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();

        session.merge(groupOwner);
//        session.update(user);
        session.flush();

        transaction.commit();
    }
}
