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
package com.flytxt.commons.reporting.util.query;


import com.flytxt.commons.reporting.ReportServiceResolverException;
import com.flytxt.commons.reporting.context.BaseContext;
import com.flytxt.commons.reporting.factory.ServiceFactory;
import com.flytxt.commons.reporting.parameter.ParameterProviderException;
import com.flytxt.commons.reporting.parameter.business.ParameterMasterService;
import com.flytxt.commons.reporting.parameter.objects.Parameter;
import com.flytxt.commons.reporting.parameter.objects.ParameterValue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
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
public class QueryReportPreparedStatement extends  ReportParameterParserUtil{


    private String initialQuery;
   

    private Connection connection;
    private ParameterMasterService paramService;

    public QueryReportPreparedStatement(String initialQuery, BaseContext ctx, Connection connection) {
        super(ctx);
        this.initialQuery = initialQuery;
        this.ctx = ctx;
        this.connection = connection;

         try {
            paramService = ServiceFactory.getParameterMasterService();
        } catch (ReportServiceResolverException ex) {
            Logger.getLogger(QueryReportPreparedStatement.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
        
    }

    
    public final PreparedStatement getStatement(Collection<Parameter> filledParameters) throws ParameterProviderException{


        Map<String,Object> reportFwkParams =
                prepareFwkParams();

        Map<String,Object> initialParams =
                prepareInitialParams();

        Map<String,Object> filledParams =
                prepareFilledParams(filledParameters);

        Collection<String> inlineQueryParams =
                this.provider.getQueryInlineProvidedParameterNames(initialQuery);


        Map<String,Object> filledUpParams
                = new HashMap<String, Object>();
        filledUpParams.putAll(reportFwkParams);
        filledUpParams.putAll(initialParams);
        filledUpParams.putAll(filledParams);
        
        
        Collection<String> extraParamsInQuery =  new ArrayList<String>();
        for(String paramStringInQuery : inlineQueryParams){
            if(!filledUpParams.containsKey(paramStringInQuery)){
                extraParamsInQuery.add(paramStringInQuery);
            }
        }

        for(String extraParamName : extraParamsInQuery){
           Collection<ParameterValue> extraParamValues =
                   this.provider.getParameterValues(extraParamName,filledUpParams);
                   Parameter _parameter = paramService.getParameterByName(extraParamName);
                   _parameter.setParameterValues(extraParamValues);
                   filledUpParams.putAll(this.provider.toMapFromFilledParameter(_parameter));
        }
        
        
        Map<String,JRDesignParameter> jrParameters =
                JasperQueryExecuter.buildJRDesignParameters(filledUpParams);
        JRDesignQuery jrQuery = new JRDesignQuery();
        jrQuery.setText(initialQuery);
        try {
            return JasperQueryExecuter.getStatement(jrQuery, jrParameters, filledUpParams, this.connection);
        } catch (JRException ex) {
            Logger.getLogger(QueryReportPreparedStatement.class.getName()).log(Level.SEVERE, null, ex);
            throw new ParameterProviderException(ex);
        }
        
        
    }

}
