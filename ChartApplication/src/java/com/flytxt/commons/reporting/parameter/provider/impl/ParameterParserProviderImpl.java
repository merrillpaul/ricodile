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
package com.flytxt.commons.reporting.parameter.provider.impl;

import com.flytxt.commons.reporting.ReportServiceResolverException;
import com.flytxt.commons.reporting.connection.ConnectionProvider;
import com.flytxt.commons.reporting.factory.ServiceFactory;
import com.flytxt.commons.reporting.parameter.ParameterConstants.Type;
import com.flytxt.commons.reporting.parameter.ParameterConstants.ValueClassType;
import com.flytxt.commons.reporting.parameter.ParameterProviderException;
import com.flytxt.commons.reporting.parameter.business.ParameterMasterService;
import com.flytxt.commons.reporting.parameter.objects.Parameter;
import com.flytxt.commons.reporting.parameter.objects.ParameterValue;
import com.flytxt.commons.reporting.parameter.provider.DateProvider;
import com.flytxt.commons.reporting.parameter.provider.ParameterParserProvider;
import com.flytxt.commons.reporting.util.query.JasperQueryExecuter;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRQueryChunk;
import net.sf.jasperreports.engine.design.JRDesignParameter;
import net.sf.jasperreports.engine.design.JRDesignQuery;

/**
 *
 * @author Merrill George (merrill.george@gmail.com)
 */
public class ParameterParserProviderImpl implements ParameterParserProvider{

   private static final DateProvider dateProvider =  DateProvider.getDateProvider();
   private static final DateProvider dateTimeProvider =  DateProvider.getDateTimeProvider();

   private ParameterMasterService paramService;

    public ParameterParserProviderImpl() {
         try {
            paramService = ServiceFactory.getParameterMasterService();
        } catch (ReportServiceResolverException ex) {
            Logger.getLogger(ParameterParserProviderImpl.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
    }

   



    public Collection<ParameterValue> getParameterValues(
            String parameterName, Map<String, Object> filledUpParams)
            throws ParameterProviderException {
        
       
       if(filledUpParams==null)
           filledUpParams= new HashMap<String, Object>();

        Parameter parameter = null;

        parameter = paramService.getParameterByName(parameterName);


        Type type = parameter.getType();
        ValueClassType clazzType = parameter.getValueClassType();


        Collection<ParameterValue> paramValues = null;


        switch(type){

            case TEXT:
                paramValues = new ArrayList<ParameterValue>();
                ParameterValue value =  new ParameterValue();
                value.setValue(parameter.getData());
                paramValues.add(value);
                break;

            case LIST:
                paramValues = parseListParameters(parameter);
                break;

            case    QUERY:
                paramValues = getParamValuesFromDataBase(parameter,filledUpParams);
                break;
        }

        return paramValues;
        
    }

    /**
     * Prepares a map with parameter name and value ( which can be a collection as well )
     * from a filled up parameter
     * @param parameter
     * @return
     * @throws ParameterProviderException
     */
    public Map<String, Object> toMapFromFilledParameter(Parameter parameter) throws ParameterProviderException {

        Type type = parameter.getType();
        ValueClassType clazzType = parameter.getValueClassType();
        Map<String,Object> map = new HashMap<String, Object>();

        switch(type){


          /*  case BOOLEAN:

                break;*/

            case LIST:
                prepareListMap(map,parameter);
                break;


            case QUERY:
                 prepareListMap(map,parameter);
                break;

            case TEXT:
                map.put(parameter.getParameterName(),
                        parseParameter(
                        clazzType,
                            ( parameter.getParameterValues()==null || parameter.getParameterValues().isEmpty())?
                            null:
                            parameter.getParameterValues().iterator().next().getValue()  ));


        }


        return map;
    }



    /**
     * Parses the query and gets a collection of parameter names
     * provided as $P{<PARAMNAME>}
     * @param queryString
     * @return
     */
    public Collection<String> getQueryInlineProvidedParameterNames(String queryString){

        Collection<String> paramNames =  new ArrayList<String>();
        /*String[] strArr = queryString.split("[$P{]+");
        for(String s : strArr){
                if(s.trim().length()==0)
                    continue;
            paramNames.add(s.substring(0,s.indexOf("}")));
        }*/



        JRDesignQuery  query = new JRDesignQuery();
        query.setText(queryString);
        JRQueryChunk[] chunks = query.getChunks();
        if (chunks != null && chunks.length > 0){
            for(JRQueryChunk chunk :chunks){
                switch (chunk.getType()){
                    case JRQueryChunk.TYPE_PARAMETER :
                        paramNames.add(chunk.getText());
                        break;
                }

            }
        }


        return paramNames;
    }



    private void prepareListMap(Map<String, Object> map, Parameter parameter) throws ParameterProviderException {

         ValueClassType clazzType = parameter.getValueClassType();
         Collection<ParameterValue> paramValues = parameter.getParameterValues();
         Collection<Object> values = new ArrayList<Object>();
         for(ParameterValue value :paramValues){
                     values.add(parseParameter(clazzType, value.getValue()));
          }
        map.put(parameter.getParameterName(), values);
        
    }



    private Object parseParameter(ValueClassType clazzType,String value) throws ParameterProviderException{

        Object obj = null;
        try{
        switch(clazzType){
            case STRING:
                obj = value;
                break;

            case DOUBLE:
                obj = new Double(value);
                break;

            case INTEGER:
                obj =  new Integer(value);
                break;

            case LONG:
                obj = new Long(value);
                break;

            case BIGDECIMAL:
                obj =  new BigDecimal(value);
                break;

            case DATE:
                obj = dateProvider.parseDate(value);
                break;

            case SQLDATE:
                obj = dateProvider.parseDate(value);
                break;

            case TIMESTAMP:
                obj = new Timestamp( dateTimeProvider.parseDate(value).getTime());
                break;

            case BOOLEAN:
                /*
                 * paramvalue.setValue(
                            ;*/
                obj = new Boolean(
                            (
                                value.equals("1") ||
                                value.equalsIgnoreCase("on") ||
                                value.equalsIgnoreCase("true") ||
                                value.equalsIgnoreCase("yes"))
                                ?
                                    true:false
                             );
                break;

        }
        }catch(Exception ex){
            throw new ParameterProviderException(ex);
        }

        return obj;
    }


    private String parseParameterValue(ValueClassType clazzType,ResultSet rs) throws ParameterProviderException {

        String result = null;
        try{
        switch(clazzType){
            case STRING:
                result = rs.getString(1);
                break;

            case DOUBLE:
                result = new Double(rs.getDouble(1)).toString();
                break;

            case INTEGER:
                result = new Integer( rs.getInt(1)).toString();
                break;

            case LONG:
                result = new Long(rs.getLong(1)).toString();
                break;

            case BIGDECIMAL:
                result =  rs.getBigDecimal(1).toString();
                break;

            case DATE:
                result = dateProvider.formatDate(rs.getDate(1));
                break;

            case SQLDATE:
                result = dateProvider.formatDate(rs.getDate(1));
                break;

            case TIMESTAMP:
                result = dateTimeProvider.formatDate(rs.getTimestamp(1));
                break;

            case BOOLEAN:
                /*
                 * paramvalue.setValue(
                            ;*/
                result = rs.getBoolean(result)?"true":"false";
                break;

        }
        }catch(SQLException sqe){
            throw new ParameterProviderException(sqe);

        }catch(NumberFormatException nfe){
            throw new ParameterProviderException(nfe);
        }


        return result;
    }



    private Collection<ParameterValue> parseListParameters(Parameter parameter) {

        Collection<ParameterValue> paramValues = new ArrayList<ParameterValue>();

        StringTokenizer tokenizer = new StringTokenizer(parameter.getData(),"|");

        
        while(tokenizer.hasMoreTokens()){

            String token =  tokenizer.nextToken();
            String value = token;
            String description = token;

            StringTokenizer paramValue =  new StringTokenizer(token,":");
            if(paramValue.countTokens() == 2){
                value = paramValue.nextToken();
                description = paramValue.nextToken();
            }


            ParameterValue _paramValue = new ParameterValue();
            _paramValue.setValue(value);
            _paramValue.setDescription(description);
            paramValues.add(_paramValue);


        }
                


        return paramValues;
    }

    private Collection<ParameterValue> getParamValuesFromDataBase(
            Parameter parameter, Map<String, Object> filledUpParams)
            throws ParameterProviderException {
      
        
        String paramQuery = parameter.getData();
        Collection<String> inlineParamNames =
                getQueryInlineProvidedParameterNames(paramQuery);

        Collection<String> extraParamsInQuery =  new ArrayList<String>();
        for(String paramStringInQuery : inlineParamNames){
            if(!filledUpParams.containsKey(paramStringInQuery)){
                extraParamsInQuery.add(paramStringInQuery);
            }
        }

        for(String extraParamName : extraParamsInQuery){
           Collection<ParameterValue> extraParamValues =
                   getParameterValues(extraParamName,filledUpParams);
                   Parameter _parameter = paramService.getParameterByName(extraParamName);
                   _parameter.setParameterValues(extraParamValues);
                   filledUpParams.putAll(toMapFromFilledParameter(_parameter));
        }

        Connection con = null;
        PreparedStatement pStmt=null;
        ResultSet rs =  null;

        Collection<ParameterValue> resultValues = new ArrayList<ParameterValue>();

        try{
        con = new ConnectionProvider().getConnection();
        if(filledUpParams== null || filledUpParams.isEmpty()){
            pStmt =  con.prepareStatement(paramQuery);
        }else{
            JRDesignQuery query =  new JRDesignQuery();
            query.setText(paramQuery);
            Map<String,JRDesignParameter> jrParameters =
                JasperQueryExecuter.buildJRDesignParameters(filledUpParams);

           pStmt = JasperQueryExecuter.getStatement(query, jrParameters, filledUpParams, con);
        }

        rs = pStmt.executeQuery();

        ResultSetMetaData rsmd =  rs.getMetaData();

        boolean multipleColumns = false;
	if (rsmd.getColumnCount() > 1)
            multipleColumns = true;

        while(rs.next()){

            ParameterValue value = new ParameterValue();

            value.setValue(parseParameterValue(parameter.getValueClassType(), rs));
            if(multipleColumns)
                value.setDescription(rs.getString(2));

            resultValues.add(value);
        }
        rs.close();
        pStmt.close();

        }catch(SQLException ex){
              Logger.getLogger(ParameterParserProviderImpl.class.getName()).log(Level.SEVERE, null, ex);
              throw new ParameterProviderException(ex);
        }catch(JRException ex){
            Logger.getLogger(ParameterParserProviderImpl.class.getName()).log(Level.SEVERE, null, ex);
              throw new ParameterProviderException(ex);
        }finally{
            if(con!=null){
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ParameterParserProviderImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        return resultValues;
    }



    









}
