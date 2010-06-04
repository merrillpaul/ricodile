/*
 * @(#) $Id:
 * Copyright Flytxt Technologies Pvt Limited. All Rights Reserved.
 *
 * This Software is the proprietary information of Flytxt technologies Pvt Limited.
 * Use is subject to License terms.
 *
 */
package com.flytxt.commons.reporting.parameter.provider.impl;

import com.flytxt.commons.reporting.chart.ChartContext;
import com.flytxt.commons.reporting.connection.ConnectionProvider;
import com.flytxt.commons.reporting.constants.ReportConstants.SystemParameters;
import com.flytxt.commons.reporting.factory.ServiceFactory;
import com.flytxt.commons.reporting.parameter.ParameterConstants;
import com.flytxt.commons.reporting.parameter.objects.Parameter;
import com.flytxt.commons.reporting.parameter.objects.ParameterValue;
import com.flytxt.commons.reporting.parameter.provider.DateProvider;
import com.flytxt.commons.reporting.parameter.provider.InitialParameterProvider;
import com.flytxt.commons.reporting.parameter.vo.InitParamVO;
import com.flytxt.commons.util.SQLUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.TimeZone;

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
            paramvalue.setValue(val);
            paramValues.add(paramvalue);

        }
        if(paramValues.size()>1)
            p.setType(ParameterConstants.Type.LIST);
        else
            p.setType(ParameterConstants.Type.TEXT);
        p.setParameterValues(paramValues);

        return p;
    }


    public void prepareInitialParameterValues(ChartContext context, InitParamVO[] initParams) {

        InitialParameterProvider provider =
                ServiceFactory.getInitialParameterProvider();
        Collection<Parameter> initialParameters =  new ArrayList<Parameter>();
        for(InitParamVO initParam : initParams){
            initialParameters.add(provider.prepareParameter(initParam));
        }

        // REQUESTED TIME
        InitParamVO requestedParam =  new InitParamVO();
        requestedParam.setName(SystemParameters.FLYTXT_REQUESTED_TIME.name());
        requestedParam.setType(ParameterConstants.ValueClassType.TIMESTAMP.getKey());
        requestedParam.setValue(DateProvider.getDateTimeProvider().formatDate(new Date()));
        initialParameters.add(provider.prepareParameter(requestedParam));

        // SQL Offset
        String userJavaTimeZoneId = getJavaTimeZoneId(context.getUserId());
        requestedParam =  new InitParamVO();
        requestedParam.setName(SystemParameters.FLYTXT_SQL_TZOFFSET_FOR_USER.name());
        requestedParam.setType(ParameterConstants.ValueClassType.DOUBLE.getKey());
        requestedParam.setValue(new Double(SQLUtil.getTimeZoneDifferenceInNumber((userJavaTimeZoneId))).toString());
        initialParameters.add(provider.prepareParameter(requestedParam));



        context.setInitialParameters(initialParameters);
    }



	private String getJavaTimeZoneId(int userId) {
		String userJavaTimeZoneId=TimeZone.getDefault().getID();


		Connection con =  null;

		try {
			con = new ConnectionProvider().getConnection();
			PreparedStatement pstr =
				con.prepareStatement("select tz.JAVA_TIME_ZONE_ID from static_time_zone tz, web_user wu where wu.web_user_id=? and tz.TIME_ZONE_ID= wu.TIMEZONE_ID");
			pstr.setInt(1, userId);
			ResultSet rs =  pstr.executeQuery();
			if(rs.next()){
				String _tz=  rs.getString(1);
				if(_tz!=null)
					userJavaTimeZoneId = _tz;
			}
			rs.close();
			pstr.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(con!=null){
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return userJavaTimeZoneId;
	}

}
