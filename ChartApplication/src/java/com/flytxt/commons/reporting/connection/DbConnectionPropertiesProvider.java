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
package com.flytxt.commons.reporting.connection;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Merrill George (merrill.george@gmail.com)
 */
public final class DbConnectionPropertiesProvider {

    private final static Properties dbProperties = new Properties();

    private static void load() {
        try {
            String path = DbConnectionPropertiesProvider.class.getPackage().getName();
            InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(path.replace(".", "/") + "/dsconnection.properties");
           
            dbProperties.load(is);
            
        } catch (IOException ex) {
            Logger.getLogger(DbConnectionPropertiesProvider.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
    }

    public static  Properties getDBProperties(){

        if(dbProperties.isEmpty()){
            load();
        }
        return dbProperties;
    }


    public static String getUrl(){
        return getDBProperties().getProperty("hibernate.connection.url");
    }

    public static String getDriver(){
        return getDBProperties().getProperty("hibernate.connection.driver_class");
    }

     public static String getUser(){
        return getDBProperties().getProperty("hibernate.connection.username");
    }

      public static String getPwd(){
        return getDBProperties().getProperty("hibernate.connection.password");
    }


  //  public static String get
}
