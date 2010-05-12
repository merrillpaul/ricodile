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
package com.flytxt.commons.reporting.chart.dataset.jdbc;

import com.flytxt.commons.reporting.chart.ChartContext;
import com.flytxt.commons.reporting.chart.dataset.creators.JDBCResultsetAwareCreator;
import com.flytxt.commons.reporting.connection.ConnectionProvider;
import com.flytxt.commons.reporting.parameter.ParameterProviderException;
import com.flytxt.commons.reporting.util.query.JasperQueryExecuter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.design.JRDesignParameter;
import net.sf.jasperreports.engine.design.JRDesignQuery;

/**
 *
 * @author Merrill George (merrill.george@gmail.com)
 */
public class ChartResultSetPreparer {


    private PreparedStatement pstr = null;
    private Connection con =   null;
    private ResultSet rs = null;
    public ChartResultSetPreparer(){

    }

    public ResultSet getResultSet(  JDBCResultsetAwareCreator jdbccreator, ChartContext ctx) throws ParameterProviderException
    {
        try {
            this.con = new ConnectionProvider().getConnection();
            if ((ctx.getInitialParameters() == null || ctx.getInitialParameters().isEmpty()) && ctx.getChart().getChartQuery().indexOf("$P{") == -1) {
                pstr = con.prepareStatement(ctx.getChart().getChartQuery());
            } else {
                try {
                    pstr = createPreparedStatement(ctx);
                } catch (JRException ex) {
                    Logger.getLogger(ChartResultSetPreparer.class.getName()).log(Level.SEVERE, null, ex);
                    throw new ParameterProviderException(ex);
                }
            }
            this.rs = pstr.executeQuery();
            return rs;
        } catch (SQLException ex) {
            Logger.getLogger(ChartResultSetPreparer.class.getName()).log(Level.SEVERE, null, ex);
            throw new ParameterProviderException(ex);
        }
    }

    public void cleanUp(){
        if(rs!=null)
            try {
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(ChartResultSetPreparer.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(this.pstr!=null)
            try {
            this.pstr.close();
        } catch (SQLException ex) {
            Logger.getLogger(ChartResultSetPreparer.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(this.con!=null)
            try {
            this.con.close();
        } catch (SQLException ex) {
            Logger.getLogger(ChartResultSetPreparer.class.getName()).log(Level.SEVERE, null, ex);
        }
     }

    private PreparedStatement createPreparedStatement(ChartContext ctx) throws JRException {

        String query = ctx.getChart().getChartQuery();
        Map<String,Object> params =  new HashMap<String, Object>();
        params.put("FLYTXT_REPORT_USER_ID", ctx.getUserId());
        params.put("FLYTXT_REPORT_USER_NAME", "Merrill");
        ArrayList<Integer> arra = new ArrayList<Integer>();
        arra.add(10040);
        arra.add(1);
        params.put("FLYTXT_REPORT_PARTNER_ID",arra);

        Map<String,JRDesignParameter> jrParameters =
                JasperQueryExecuter.buildJRDesignParameters(params);
        JRDesignQuery jrQuery = new JRDesignQuery();
        jrQuery.setText(query);
        return JasperQueryExecuter.getStatement(jrQuery, jrParameters, params, con);
        
        
    }


   

}
