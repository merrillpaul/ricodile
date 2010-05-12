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
package com.flytxt.commons.reporting.web.chart.context;

import com.flytxt.commons.reporting.chart.ChartContext;
import com.flytxt.commons.reporting.chart.ChartContextCreator;
import com.flytxt.commons.reporting.chart.entity.ChartConfig;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Merrill George (merrill.george@gmail.com)
 */
public class WebChartContextCreator implements ChartContextCreator {
    private final HttpServletRequest request;

    public WebChartContextCreator(ChartContextCreator creator, HttpServletRequest request) {
        this.creator = creator;
        this.request = request;
    }




    public ChartContext createChartContext(ChartConfig chart, Integer userId) {
        return creator.createChartContext(chart, userId);
    }

    private ChartContextCreator creator;


}
