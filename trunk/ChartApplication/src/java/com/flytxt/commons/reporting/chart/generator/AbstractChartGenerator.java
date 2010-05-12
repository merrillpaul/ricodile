package com.flytxt.commons.reporting.chart.generator;

import com.flytxt.commons.reporting.chart.ChartContext;
import com.flytxt.commons.reporting.chart.ChartOutput; 
import com.flytxt.commons.reporting.chart.dataset.base.DataSet; 

/**
 *  <p style="margin-top: 0">
 *        Any default beavhour to be implemented here
 *      </p>
 */
public abstract class AbstractChartGenerator implements ChartGenerator {

    public abstract void prepareChartOuput (ChartOutput chartouput, DataSet dataset);
    protected ChartContext ctx;
    public ChartOutput generateChart(DataSet dataset) {
        
        ChartOutput chartOutput = initializeChartOuput();
        
        prepareChartOuput(chartOutput, dataset);
        return chartOutput;
    }

    public void setChartContext(ChartContext ctx) {
       this.ctx = ctx;
    }



    protected abstract ChartOutput initializeChartOuput();

   

  

  

}

