package com.flytxt.commons.reporting.chart.generator;


import com.flytxt.commons.reporting.chart.ChartContext;
import com.flytxt.commons.reporting.chart.ChartOutput; 
import com.flytxt.commons.reporting.chart.dataset.base.DataSet; 

public interface ChartGenerator {

    public ChartOutput generateChart (DataSet dataset);

    void setChartContext(ChartContext ctx);

}

