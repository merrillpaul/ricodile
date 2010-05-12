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

import com.flytxt.commons.reporting.chart.entity.ChartConfig;
import java.util.HashMap;
import java.util.Map;
import static com.flytxt.commons.reporting.constants.ChartConstants.ChartJsonContstants.CHART_INFO;

/**
 *
 * @author Merrill George (merrill.george@gmail.com)
 */
public class ChartInfoJsonMap extends AbstractChartJsonMap {

    public ChartInfoJsonMap(ChartConfig chart) {
        this.chart = chart;
    }


    private ChartConfig chart;
    

    public ChartJsonMap buildMap() {
        this.data.put(CHART_INFO, getChartJson());
        return this;
    }

    private Map<String,Object> getChartJson(){
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("chartName", chart.getName());
        map.put("width", chart.getWidth());
        map.put("height", chart.getHeight());
        map.put("title", chart.getDescription());
        return map;
    }

}
