/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao.hibernate;

import com.entity.moma.Brochure;
import java.util.Iterator;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author bianshujun
 */
public class BrochureDao {
    
    public static Brochure findby_brochureId(int brochureId) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        String sql = "select * from Brochure where brochure_id=";
        List brochureList = session.createSQLQuery(sql + brochureId + ";")
                .addEntity(Brochure.class).list();
        session.close();

        Iterator it = brochureList.iterator();
        if (it.hasNext()) {
            Brochure brochure = (Brochure) it.next();
            return brochure;
        } else {
            return null;
        }
    }
    
    public static List<Brochure> findby_brochureName(String brochureName) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        
        String hql="from Brochure where brochure_name=:brochureName";
        Query query = session.createQuery(hql);
        query.setString("brochureName",brochureName);
        List brochureList = (List<Brochure>) query.list();
        session.close();
        
        return brochureList;
    }
    
    public static void add_brochure(Brochure brochure) {
        
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();

        session.save(brochure);
        session.flush();

        transaction.commit();
    }
    
    public static void modify_brochure(Brochure brochure) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        
//        session.merge(brochure);
        session.update(brochure);
        session.flush();
        
        transaction.commit();
    }
    
    
    public static int getMaxBrochureId() {

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        int maxUserId = (Integer) session.createQuery(
                "select max(brochure.brochureId) from Brochure brochure").uniqueResult();
        return maxUserId;
    }
}
