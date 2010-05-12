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

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;
import org.apache.commons.dbcp.BasicDataSource;

/**
 *
 * @author Merrill George (merrill.george@gmail.com)
 */
public class ConnectionProvider {

    private static DataSource ds = null;


    private static DataSource getDs(){
        if(ds==null){
            BasicDataSource bds = new BasicDataSource();
            bds.setDriverClassName(DbConnectionPropertiesProvider.getDriver());
            bds.setUrl(DbConnectionPropertiesProvider.getUrl());
            bds.setUsername(DbConnectionPropertiesProvider.getUser());
            bds.setPassword(DbConnectionPropertiesProvider.getPwd());

           // Logger.getLogger(ConnectionProvider.class.getName()).log(Level.INFO,"Num Active : "+bds.getNumActive());
           // Logger.getLogger(ConnectionProvider.class.getName()).log(Level.INFO,"Num Idle : "+bds.getNumIdle());

            ds = bds;

        }


        return ds;
    }


    public Connection getConnection() throws SQLException{
        
        return getDs().getConnection();
    }

}
