package com.flytxt.commons.reporting.chart.dataset.creators;

import com.flytxt.commons.reporting.chart.ChartContext; 
import com.flytxt.commons.reporting.chart.ChartException;
import com.flytxt.commons.reporting.chart.dataset.base.Category; 
import com.flytxt.commons.reporting.chart.dataset.base.DataSet; 
import com.flytxt.commons.reporting.chart.dataset.base.Set; 

/**
 *  <p style="margin-top: 0">
 *        Creates flytxt Dataset objetcs for different chart types. Should be 
 *        aware of the context in which the chart is run
 *      </p>
 */
public interface DatasetCreator<T extends DataSet> {

    void prepareDataset () throws ChartException;

    void setChartContext (ChartContext context);

    void addTooltip (Category category, String tooltipText);

    void addTooltip (Set set, String tooltipText);

    void addChartDrilldownParameter (String name, String value);

    void addReportDrilldownParameter (String name, String value);

    void createDataset ();

    T getDataSet();

}

