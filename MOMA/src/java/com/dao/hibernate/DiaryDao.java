/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao.hibernate;

import com.entity.moma.Diary;
import java.util.Iterator;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author bianshujun
 */
public class DiaryDao {

    public static Diary findby_diaryId(int diaryId) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();

        String sql = "select * from Diary where diary_id=";
        List diaryList = session.createSQLQuery(sql + diaryId + ";")
                .addEntity(Diary.class).list();

        session.close();

        Iterator it = diaryList.iterator();
        if (it.hasNext()) {
            Diary diary = (Diary) it.next();
            return diary;
        } else {
            return null;
        }
    }

    public static Diary findby_diaryurl(String diaryurl) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();

        String hql = "from Diary where diary_url=:diaryurl";
        Query query = session.createQuery(hql);
        query.setString("diaryurl", diaryurl);
        List diaryList = (List<Diary>) query.list();
        session.close();

        Iterator it = diaryList.iterator();
        if (it.hasNext()) {
            Diary diary = (Diary) it.next();
            return diary;
        } else {
            return null;
        }
    }

    public static Diary findby_userId(int userId) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();

        String sql = "select * from Diary where diary_user_fk=";
        List diaryList = session.createSQLQuery(sql + userId + ";")
                .addEntity(Diary.class).list();

        Iterator it = diaryList.iterator();
        if (it.hasNext()) {
            Diary diary = (Diary) it.next();
            return diary;
        } else {
            return null;
        }
    }

    public static Diary findby_brochureId(int brochureId) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();

        String sql = "select * from Diary where diary_brochure_fk=";
        List diaryList = session.createSQLQuery(sql + brochureId + ";")
                .addEntity(Diary.class).list();

        Iterator it = diaryList.iterator();
        if (it.hasNext()) {
            Diary diary = (Diary) it.next();
            return diary;
        } else {
            return null;
        }
    }

    public static void add_diary(Diary diary) {

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();

        session.save(diary);
        session.flush();

        transaction.commit();
    }

    public static void modify_diary(Diary diary) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();

//        session.merge(diary);
        session.update(diary);
        session.flush();

        transaction.commit();
    }
    
}
