/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.flytxt.commons.reporting;


import com.flytxt.commons.reporting.connection.DbConnectionPropertiesProvider;
import org.hibernate.Session;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.SessionFactory;

/**
 * Hibernate Utility class with a convenient method to get Session Factory object.
 *
 * @author merrill.paul
 */
public class ReportHibernateUtil {
    private static final SessionFactory sessionFactory;

    
    static {
        try {
            // Create the SessionFactory from standard (hibernate.cfg.xml) 
            // config file.
            sessionFactory = new AnnotationConfiguration()
                    .setProperties(DbConnectionPropertiesProvider.getDBProperties())
                    .configure()
                    .buildSessionFactory();
        } catch (Throwable ex) {
            // Log the exception. 
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static Session getSession(){
        if(ThreadLocalHibernateSessionResolver.getSession()==null){
            Session session = getSessionFactory().openSession();
            new ThreadLocalHibernateSessionResolver(session);
        }
        return ThreadLocalHibernateSessionResolver.getSession();
    }

     public static void closeSession(){
       ThreadLocalHibernateSessionResolver.clear();
    }
}
