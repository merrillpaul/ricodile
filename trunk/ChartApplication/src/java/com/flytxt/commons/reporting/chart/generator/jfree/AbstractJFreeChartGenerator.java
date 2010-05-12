package com.flytxt.commons.reporting.chart.generator.jfree;

import com.flytxt.commons.reporting.chart.ChartOutput; 
import com.flytxt.commons.reporting.chart.dataset.base.DataSet;
import com.flytxt.commons.reporting.chart.generator.AbstractChartGenerator; 
import com.flytxt.commons.reporting.constants.ChartConstants.ChartRendererType;

public abstract class AbstractJFreeChartGenerator extends AbstractChartGenerator {

     @Override
    protected ChartOutput initializeChartOuput() {

        ChartOutput output = new ChartOutput();
        output.setContentType(ChartRendererType.JFREE.getContentType());

        
        return output;

    }

    public void prepareChartOuput (ChartOutput chartouput, DataSet dataset) {
        prepareJFreeChart(chartouput, dataset);
    }

    public abstract void prepareJFreeChart (ChartOutput chartoutput, DataSet dataset);

}

