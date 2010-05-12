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
package com.flytxt.commons.reporting.chart;

import com.flytxt.commons.reporting.chart.generator.fusioncharts.support.FusionChartContextCreator;
import com.flytxt.commons.reporting.constants.ChartConstants;

/**
 *
 * @author Merrill George (merrill.george@gmail.com)
 */
public final class ChartContextCreatorFactory {

    public static ChartContextCreator getCreator(ChartConstants.ChartRendererType type){
       ChartContextCreator creator = null;
        if(type==ChartConstants.ChartRendererType.FUSION)
            creator =  new FusionChartContextCreator();
        else
            throw new UnsupportedOperationException("Not yet supoorted");
       return creator;
    }
}
