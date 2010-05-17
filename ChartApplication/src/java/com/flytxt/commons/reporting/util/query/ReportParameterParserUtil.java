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

import static com.flytxt.commons.reporting.constants.ReportConstants.SystemParameters.FLYTXT_REPORT_USER_ID;
import static com.flytxt.commons.reporting.constants.ReportConstants.SystemParameters.FLYTXT_REPORT_USER_NAME;
import static com.flytxt.commons.reporting.constants.ReportConstants.SystemParameters.FLYTXT_REPORT_USER_PARTNER_ID;
import com.flytxt.commons.reporting.context.BaseContext;
import com.flytxt.commons.reporting.factory.ServiceFactory;
import com.flytxt.commons.reporting.parameter.ParameterProviderException;
import com.flytxt.commons.reporting.parameter.objects.Parameter;
import com.flytxt.commons.reporting.parameter.provider.ParameterParserProvider;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Merrill George (merrill.george@gmail.com)
 */
public abstract class ReportParameterParserUtil {
     protected ParameterParserProvider provider;

    public ReportParameterParserUtil(BaseContext ctx) {
        this.ctx = ctx;
        this.provider =  ServiceFactory.getParameterParser();
    }

     protected BaseContext ctx;



  protected final Map<String, Object> prepareFwkParams() {

        Map<String, Object> params=  new HashMap<String, Object>();

        params.put(FLYTXT_REPORT_USER_ID.name(), ctx.getUserId());
        params.put(FLYTXT_REPORT_USER_NAME.name(), ctx.getUserName());
        params.put(FLYTXT_REPORT_USER_PARTNER_ID.name(), ctx.getPartnerId());

        return params;
    }



  protected Map<String, Object> prepareInitialParams() throws ParameterProviderException  {

       Map<String, Object> params=  new HashMap<String, Object>();

       if(this.ctx.getInitialParameters()!=null)
       for(Parameter parameter : this.ctx.getInitialParameters()){
           params.putAll(getFilledParamMap(parameter));
       }

       return params;

    }




   protected Map<String, Object> prepareFilledParams(Collection<Parameter> filledParameters)
   throws ParameterProviderException{
        Map<String, Object> params=  new HashMap<String, Object>();
        if(filledParameters!=null)
        for(Parameter parameter : filledParameters){
               params.putAll(getFilledParamMap(parameter));
        }
        return params;
    }




  protected Map<String, Object> getFilledParamMap(Parameter parameter)
  throws ParameterProviderException{
      return this.provider.toMapFromFilledParameter(parameter);
  }



}
