/*
 * @(#) $Id:
 * Copyright Flytxt Technologies Pvt Limited. All Rights Reserved.
 *
 * This Software is the proprietary information of Flytxt technologies Pvt Limited.
 * Use is subject to License terms.
 *
 */
package com.flytxt.commons.reporting.parameter.provider.impl;

import com.flytxt.commons.reporting.parameter.ParameterConstants;
import com.flytxt.commons.reporting.parameter.objects.Parameter;
import com.flytxt.commons.reporting.parameter.objects.ParameterValue;
import com.flytxt.commons.reporting.parameter.provider.InitialParameterProvider;
import com.flytxt.commons.reporting.parameter.vo.InitParamVO;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author Merrill George (merrill.george@gmail.com)
 */
public class InitialParameterProviderImpl implements InitialParameterProvider {

    public Parameter prepareParameter(InitParamVO initParam) {

        Parameter p = new Parameter();
        p.setParameterName(initParam.getName());
        ParameterConstants.ValueClassType classType =
                null;
        if(initParam.getType()!=null || initParam.getType().trim().length()>0){
            classType =
                    ParameterConstants.ValueClassType.findByKey(initParam.getType());

        }
        if(classType==null)
            classType = ParameterConstants.ValueClassType.STRING;

        p.setValueClassType(classType);
        String value = initParam.getValue();
        String[] vals = null;
        if(value.indexOf(",")!=-1){
                 vals    = value.split(",");
         }else{
            vals = new String[]{value};
         }

        Collection<ParameterValue> paramValues = new ArrayList<ParameterValue>();
        for(String val:vals){
            ParameterValue paramvalue = new ParameterValue();
           /* switch(classType){
                case STRING:
                    paramvalue.setValue(val);
                    break;
                case INTEGER:
                    paramvalue.setValue(new Integer(val));
                    break;
                case BIGDECIMAL:
                    paramvalue.setValue(new BigDecimal(val));
                    break;
                case BOOLEAN:
                    paramvalue.setValue(
                            new Boolean(
                            (
                                val.equals("1") ||
                                val.equalsIgnoreCase("on") ||
                                val.equalsIgnoreCase("true") ||
                                val.equalsIgnoreCase("yes"))
                                ?
                                    true:false
                             ));
                    break;
                case DATE:
                    // TODO set
                    break;
                case TIMESTAMP:
                    // TODO
                    break;
                case LONG:
                    paramvalue.setValue(Long.valueOf(val));
                    break;
                case DOUBLE:
                    paramvalue.setValue(Double.valueOf(val));
                    break;
            }*/
            paramvalue.setValue(value);
            paramValues.add(paramvalue);

        }
        if(paramValues.size()>1)
            p.setType(ParameterConstants.Type.LIST);
        else
            p.setType(ParameterConstants.Type.TEXT);
        p.setParameterValues(paramValues);

        return p;
    }

}
