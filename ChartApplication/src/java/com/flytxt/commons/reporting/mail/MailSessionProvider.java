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
package com.flytxt.commons.reporting.mail;

import java.util.Properties;
import javax.mail.Session;

/**
 *
 * @author Merrill George (merrill.george@gmail.com)
 */
public final class MailSessionProvider {

    private static Session reportPlatformMailSession =null;




    private static Session createSession(String userName,String password){

        Properties p = MailSessionPropertiesProvider.getProperties();
        boolean useAuthenticator =
                MailSessionPropertiesProvider.useMailAuthenticator();

        Session session = null;
        if(useAuthenticator){
            session = Session.getDefaultInstance(p,new SMTPAuthenticator(userName,password));
        }else{
            session = Session.getDefaultInstance(p);
        }
        return session;
    }


    public static Session getNewSession(String userName,String password){

        return createSession(userName, password);

    }

    public static Session getReportSession(){



        if(reportPlatformMailSession==null){
            reportPlatformMailSession = createSession("ricodile.reports", "reports123");
        }
        return reportPlatformMailSession;
    }



}
