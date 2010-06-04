/*
 * @(#) $Id:
 * Copyright Flytxt Technologies Pvt Limited. All Rights Reserved.
 *
 * This Software is the proprietary information of Flytxt technologies Pvt Limited.
 * Use is subject to License terms.
 *
 */
package com.flytxt.commons.reporting.web.chart;

import com.flytxt.commons.reporting.chart.ChartContext;
import com.flytxt.commons.reporting.chart.ChartContextCreator;
import com.flytxt.commons.reporting.chart.ChartContextCreatorFactory;
import com.flytxt.commons.reporting.chart.ChartRunMap;
import com.flytxt.commons.reporting.constants.ChartConstants.ChartRendererType;
import com.flytxt.commons.reporting.constants.ReportConstants.SystemParameters;
import com.flytxt.commons.reporting.factory.ServiceFactory;
import com.flytxt.commons.reporting.parameter.ParameterConstants.ValueClassType;
import com.flytxt.commons.reporting.parameter.objects.Parameter;
import com.flytxt.commons.reporting.parameter.provider.DateProvider;
import com.flytxt.commons.reporting.parameter.provider.InitialParameterProvider;
import com.flytxt.commons.reporting.parameter.vo.InitParamVO;
import com.flytxt.commons.reporting.web.chart.context.HttpSessionAwareChartRunMap;
import com.flytxt.commons.reporting.web.chart.context.WebChartContextCreator;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts.actions.DispatchAction;

/**
 *
 * @author Merrill George (merrill.george@gmail.com)
 */
public abstract class ChartBaseAction extends DispatchAction{

    protected ChartContextCreator getChartContextCreator(HttpServletRequest request) {

        return new WebChartContextCreator
                (ChartContextCreatorFactory.getCreator(ChartRendererType.FUSION), request);
    }


    protected ChartRunMap getChartRunSessionMapper(HttpServletRequest request){
        return new HttpSessionAwareChartRunMap(request);
    }

    protected void prepareInitialParameterValues(ChartContext context, InitParamVO[] initParams) {

         InitialParameterProvider provider =
                ServiceFactory.getInitialParameterProvider();
        provider.prepareInitialParameterValues(context, initParams);
    }


}
