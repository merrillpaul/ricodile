package com.flytxt.commons.reporting.chart;

import com.flytxt.commons.reporting.chart.entity.ChartConfig;
import com.flytxt.commons.reporting.context.ReportUser;


public interface ChartContextCreator  {

   ChartContext createChartContext(ChartConfig chart, ReportUser user);

}

