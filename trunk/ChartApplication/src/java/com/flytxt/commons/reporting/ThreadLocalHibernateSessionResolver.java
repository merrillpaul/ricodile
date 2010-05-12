/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/*
 * @(#) $Id:
 * Copyright Flytxt Technologies Pvt Limited. All Rights Reserved.
 *
 * This Software is the proprietary information of Flytxt technologies Pvt Limited.
 * Use is subject to License terms.
 *
 */
package com.flytxt.commons.reporting;

import org.hibernate.Session;

/**
 * To be used inside service impls
 * @author Merrill George (merrill.george@gmail.com)
 */
public class ThreadLocalHibernateSessionResolver {

    private static ThreadLocal<Session> sessionHolder;

    public ThreadLocalHibernateSessionResolver(Session session) {
        super();
        if(sessionHolder==null){
            sessionHolder =  new ThreadLocal<Session>();
        }
        sessionHolder.set(session);
    }

    public static Session getSession(){
        return sessionHolder!=null?sessionHolder.get():null;
    }

    public static void clear(){
        
        Session session = sessionHolder.get();
        if(session!=null){
        session.close();
        sessionHolder.remove();
        }
    }


}
