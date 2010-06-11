/*
 * @(#) $Id:
 * Copyright Flytxt Technologies Pvt Limited. All Rights Reserved.
 *
 * This Software is the proprietary information of Flytxt technologies Pvt Limited.
 * Use is subject to License terms.
 *
 */
package com.flytxt.commons.reporting.chart.generator.fusioncharts.support;

import com.flytxt.commons.reporting.chart.ChartContext;
import com.flytxt.commons.reporting.chart.ChartContextCreator;
import com.flytxt.commons.reporting.chart.entity.ChartConfig;
import com.flytxt.commons.reporting.constants.ChartConstants;
import com.flytxt.commons.reporting.constants.RenderTarget;
import com.flytxt.commons.reporting.context.ReportUser;

/**
 *
 * @author Merrill George (merrill.george@gmail.com)
 */
public class FusionChartContextCreator implements ChartContextCreator {
    
    public ChartContext createChartContext(ChartConfig chart, ReportUser user) {
        ChartContext ctx =  new ChartContext();
        ctx.setRenderTarget(RenderTarget.AT_CLIENT);
        ctx.setRendererType(ChartConstants.ChartRendererType.FUSION);
        ctx.setUser(user);
        ctx.setChart(chart);
        
        return ctx;
    }

}
