/*
 * @(#) $Id:
 * Copyright Flytxt Technologies Pvt Limited. All Rights Reserved.
 *
 * This Software is the proprietary information of Flytxt technologies Pvt Limited.
 * Use is subject to License terms.
 *
 */
package com.flytxt.commons.reporting.web.chart.initchartconfig.json;

import com.flytxt.commons.reporting.chart.ChartContext;
import static com.flytxt.commons.reporting.constants.ChartConstants.ChartJsonContstants.CHART_RUN_SESSION_ID;
import static com.flytxt.commons.reporting.constants.ChartConstants.ChartJsonContstants.CHART_ID;
import static com.flytxt.commons.reporting.constants.ChartConstants.ChartJsonContstants.CHART_FOUND;
import com.flytxt.commons.reporting.web.json.charts.AbstractChartJsonMap;
import com.flytxt.commons.reporting.web.json.charts.ChartInfoJsonMap;
import com.flytxt.commons.reporting.web.json.charts.ChartJsonMap;
import com.flytxt.commons.reporting.web.json.charts.FrameworkAttributesJsonMap;



/**
 *
 * @author Merrill George (merrill.george@gmail.com)
 */
public class ChartInitJsonMap extends AbstractChartJsonMap {
    private final String chartRunSessionId;
    private final ChartContext context;



    public ChartInitJsonMap(String chartRunSessionId, ChartContext context) {
      
        super();
        this.chartRunSessionId = chartRunSessionId;
        this.context =  context;
      
    }




  
    public ChartJsonMap buildMap() {
       this.data.put(CHART_RUN_SESSION_ID, this.chartRunSessionId);
       if(this.context.getChart()!=null){
           this.data.put(CHART_ID, this.context.getChart().getChartId());
           this.data.putAll(new FrameworkAttributesJsonMap(context).buildMap().getMap());
           this.data.putAll(new ChartInfoJsonMap(context.getChart()).buildMap().getMap());
           this.data.put(CHART_FOUND, true);
       }else{
            this.data.put(CHART_FOUND, false);
       }
       return this;
    }

}
