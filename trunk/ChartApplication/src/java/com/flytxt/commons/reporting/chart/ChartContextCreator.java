package com.flytxt.commons.reporting.chart;

import com.flytxt.commons.reporting.chart.entity.ChartConfig;


public interface ChartContextCreator  {

   ChartContext createChartContext(ChartConfig chart, Integer userId);

}

