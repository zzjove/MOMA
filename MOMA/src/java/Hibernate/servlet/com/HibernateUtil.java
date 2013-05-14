/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Hibernate.servlet.com;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.SessionFactory;
/**
 *
 * @author bianshujun
 */

public class HibernateUtil {
    private static final SessionFactory sessionFactory;

    static {
        try {
            // Create the SessionFactory from standard (hibernate.cfg.xml) 
            // config file.
            sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            // Log the exception. 
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}