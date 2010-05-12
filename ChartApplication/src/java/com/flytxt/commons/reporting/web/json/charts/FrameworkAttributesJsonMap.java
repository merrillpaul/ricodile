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
package com.flytxt.commons.reporting.web.json.charts;

import com.flytxt.commons.reporting.chart.ChartContext;
import com.flytxt.commons.reporting.factory.ServiceFactory;
import static com.flytxt.commons.reporting.constants.ChartConstants.ChartJsonContstants.CHART_FRAMEWORK_ATTRS;


/**
 *
 * @author Merrill George (merrill.george@gmail.com)
 */
public class FrameworkAttributesJsonMap extends AbstractChartJsonMap {

    private final ChartContext context;

    public FrameworkAttributesJsonMap(ChartContext context) {
        this.context = context;
    }

    public ChartJsonMap buildMap() {


        this.data.put(CHART_FRAMEWORK_ATTRS,
                ServiceFactory.getChartFrameworkAttributesProvider(
                context.getRenderType()).getExtraParameters(context));
        return this;
    }

}
