package com.flytxt.commons.reporting.chart.generator.fusioncharts;

import com.flytxt.commons.reporting.chart.ChartOutput; 
import com.flytxt.commons.reporting.chart.dataset.base.DataSet; 
import com.flytxt.commons.reporting.chart.generator.AbstractChartGenerator; 
import com.flytxt.commons.reporting.constants.ChartConstants.ChartRendererType;

public abstract class AbstractFusionChartGenerator extends AbstractChartGenerator {

    public void prepareChartOuput (ChartOutput chartouput, DataSet dataset) {
        prepareFusionChart(chartouput, dataset);
    }

    public abstract void prepareFusionChart (ChartOutput chartoutput, DataSet dataset);

    @Override
    protected ChartOutput initializeChartOuput() {

        ChartOutput output = new ChartOutput();
        output.setContentType(ChartRendererType.FUSION.getContentType());
        return output;

    }




}

