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

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Merrill George (merrill.george@gmail.com)
 */
public final class MailSessionPropertiesProvider {

    private final static Properties properties = new Properties();

    private static void load() {
        try {
            String path = MailSessionPropertiesProvider.class.getPackage().getName();
            InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(path.replace(".", "/") + "/mailsession.properties");

            properties.load(is);
        } catch (IOException ex) {
            Logger.getLogger(MailSessionPropertiesProvider.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
    }

    public static  Properties getProperties(){

        if(properties.isEmpty()){
            load();
        }
        return properties;
    }

    public static boolean useMailAuthenticator(){
        return getProperties().getProperty("mail.smtp.auth").equalsIgnoreCase("true")?true:false;
    }

    static String getReportsSender() {
        return getProperties().getProperty("flyreports.sender");
    }

}
