/*
 * @(#) $Id:
 * Copyright Flytxt Technologies Pvt Limited. All Rights Reserved.
 *
 * This Software is the proprietary information of Flytxt technologies Pvt Limited.
 * Use is subject to License terms.
 *
 */
package com.flytxt.commons.reporting.chart.generator.fusioncharts;

import com.flytxt.commons.reporting.chart.ChartContext;
import com.flytxt.commons.reporting.chart.ChartFrameworkExtraAttributesProvider;
import com.flytxt.commons.reporting.chart.generator.fusioncharts.support.ChartSwfMapping;
import java.util.HashMap;
import java.util.Map;

/**
 * Provides the SWFFile name for the chartType and the endPoint url for the
 *
 * @author Merrill George (merrill.george@gmail.com)
 */
public class FusionChartExtraAttributesProvider
        implements ChartFrameworkExtraAttributesProvider {

    private static final String FUSION_SWF_JSON_NAME="fusionSWFFile";

    public Map<String, String> getExtraParameters(ChartContext ctx) {

        Map<String,String> map =  new HashMap<String, String>();
        map.put(FUSION_SWF_JSON_NAME,
                ChartSwfMapping.getSwfFileName(
                ctx.getChart().getChartTypeAsEnum())
                );
        return map;
    }

}
