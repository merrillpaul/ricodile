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
import com.flytxt.commons.reporting.chart.dataset.creators.JDBCResultsetAwareCreator.ResultSetColumnInfo;
import com.flytxt.commons.reporting.chart.dataset.creators.JDBCResultsetAwareCreator.SeriesColumnNameMapping;
import com.flytxt.commons.reporting.chart.entity.ChartPromptParameters;
import com.flytxt.commons.reporting.connection.ConnectionProvider;
import com.flytxt.commons.reporting.constants.ChartConstants.ChartResultSetConstants;
import com.flytxt.commons.reporting.parameter.ParameterProviderException;
import com.flytxt.commons.reporting.parameter.objects.Parameter;
import com.flytxt.commons.reporting.util.query.QueryReportPreparedStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

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
                } catch (ParameterProviderException ex) {
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

    

    private PreparedStatement createPreparedStatement(ChartContext ctx) throws ParameterProviderException {

        String query = ctx.getChart().getChartQuery();

        Collection<Parameter> filledParameters =
                null;

        if(ctx.getChart().getChartPromptParameters()!=null){
            filledParameters =  new ArrayList<Parameter>();
            for(ChartPromptParameters promptParameter : ctx.getChart().getChartPromptParameters()){

                filledParameters.add(promptParameter.getParameter());

            }
        }

        return new QueryReportPreparedStatement(query, ctx, con).getStatement(filledParameters);
        
    }

    public ResultSetColumnInfo getColumnInfo(ResultSetMetaData rsmds) throws SQLException {

       int columnCount = rsmds.getColumnCount();
        List<String> columnNames =  new ArrayList<String>(columnCount);
        List<String> drillDownNames =  new ArrayList<String>();
       for(int i =0;i<columnCount;i++){
           columnNames.add(rsmds.getColumnName(i+1).toUpperCase());
       }

        List<SeriesColumnNameMapping> mapping = null;

       /*  1. select label "category name", value from sometable
     *  In this case this is a single series chart. The series name would be null*/
       if(columnCount<3){

            mapping =  new ArrayList<SeriesColumnNameMapping>();
            SeriesColumnNameMapping eachMap =  new SeriesColumnNameMapping();
            eachMap.seriesColumnName=null;
            eachMap.valueColumnName= columnNames.get(1);
            mapping.add(eachMap);
        }else if(columnCount==3){

            /*  */
            if(columnNames.contains(ChartResultSetConstants.SERIESNAME.name())){
                 mapping =  new ArrayList<SeriesColumnNameMapping>();
                SeriesColumnNameMapping eachMap =  new SeriesColumnNameMapping();
                eachMap.seriesColumnName=ChartResultSetConstants.SERIESNAME.name();
                eachMap.valueColumnName= columnNames.get(1);
                mapping.add(eachMap);
            }else{
                mapping =  new ArrayList<SeriesColumnNameMapping>();
                String thirdColumnName = columnNames.get(2);
                SeriesColumnNameMapping eachMap =  new SeriesColumnNameMapping();
                if(thirdColumnName.startsWith(ChartResultSetConstants.DRILLDOWN.name())){
                    eachMap.seriesColumnName=null;
                    eachMap.valueColumnName= columnNames.get(1);
                    drillDownNames.add(thirdColumnName);

                }else{
                      eachMap.seriesColumnName = columnNames.get(2);
                      eachMap.valueColumnName = columnNames.get(1);
                }
                mapping.add(eachMap);

              

                // TODO drilldown handling
            }
        }else{

                 mapping =  new ArrayList<SeriesColumnNameMapping>();
                
                 boolean seriesFound =  false;
                 boolean drilldownFound = false;
                 for(String columnName :columnNames){
                     if(columnName.startsWith(ChartResultSetConstants.DRILLDOWN.name())){
                        drillDownNames.add(columnName);
                        drilldownFound=true;
                     }
                     if(seriesFound==false){
                        if(columnName.startsWith(ChartResultSetConstants.SERIESNAME.name())){
                             seriesFound = true;
                        }
                     }
                 }
                // check whether there is series
                // if series not there then the same as adding just a single series
                // column with probably some drilldown coluklm parameters
                 if(!seriesFound){
                        if(drilldownFound){ // asuming that all the rest of the columns are drilldowns
                            for(int i=2;i<columnNames.size();i++){
                                  if(!drillDownNames.contains(columnNames.get(i))){
                                      drillDownNames.add(columnNames.get(i));
                                  }
                            }
                        }
                        
                          SeriesColumnNameMapping eachMap =  new SeriesColumnNameMapping();
                          eachMap.seriesColumnName = null;
                          eachMap.valueColumnName = columnNames.get(1);
                          mapping.add(eachMap);

                 }else{
                         List<String> seriesColumLabels = new ArrayList<String>();
                         List<String> seriesValueColumLabels = new ArrayList<String>();
                          for(String colName :columnNames){
                            if(colName.startsWith(ChartResultSetConstants.SERIESNAME.name())){
                                seriesColumLabels.add(colName);
                            }
                             if(colName.startsWith(ChartResultSetConstants.SERIESVALUE.name())){
                                seriesValueColumLabels.add(colName);
                            }
                         }
                         for(String seriesName :seriesColumLabels){
                             String seriesSuffix = (
                                     seriesName.length()==ChartResultSetConstants.SERIESNAME.name().length()?
                                         "":seriesName.substring(ChartResultSetConstants.SERIESNAME.name().length())
                                         );

                            String seriesValueColName = ChartResultSetConstants.SERIESVALUE.name()+seriesSuffix;
                            SeriesColumnNameMapping eachMap =  new SeriesColumnNameMapping();
                            eachMap.seriesColumnName = seriesName;
                            eachMap.valueColumnName = columnNames.contains(seriesValueColName)?seriesValueColName:null;
                            mapping.add(eachMap);

                         }


                           for(int i=2;i<columnNames.size();i++){
                                  if(!drillDownNames.contains(columnNames.get(i)) && !seriesColumLabels.contains(columnNames.get(i)) && !seriesValueColumLabels.contains(columnNames.get(i))){
                                      drillDownNames.add(columnNames.get(i));
                                  }
                            }

                 }


               // if there are series coluns , the n find out which all series coluns a
               // are there and add to the seriescolumn maping list
               // for the moment forget about drilldowns

          


        }





       
       return new ResultSetColumnInfo(mapping,drillDownNames/* TODO add drilldown coluns here*/);
    }




   

}
