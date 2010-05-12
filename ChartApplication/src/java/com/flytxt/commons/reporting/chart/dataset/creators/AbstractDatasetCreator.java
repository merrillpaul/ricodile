package com.flytxt.commons.reporting.chart.dataset.creators;

import com.flytxt.commons.reporting.chart.ChartContext; 
import com.flytxt.commons.reporting.chart.ChartException;
import com.flytxt.commons.reporting.chart.dataset.base.Category; 
import com.flytxt.commons.reporting.chart.dataset.base.DataSet; 
import com.flytxt.commons.reporting.chart.dataset.base.Set; 
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class AbstractDatasetCreator<T extends DataSet> implements DatasetCreator {

    protected T dataset;
    protected ChartContext chartContext;

    public AbstractDatasetCreator () {
    }

   
    public void setChartContext (ChartContext context) {
        this.chartContext =  context;
    }

    public void addTooltip (Category category, String tooltipText) {
    }

    public void addTooltip (Set set, String tooltipText) {
    }

    public void addChartDrilldownParameter (String name, String value) {
    }

    public void addReportDrilldownParameter (String name, String value) {
    }

    public void createDataset() {
        this.dataset = initializeDataset();
        try {
            beforeDatasetPreparation();
            prepareDataset();
            afterDatasetPreparation();
        } catch (ChartException ex) {
            Logger.getLogger(AbstractDatasetCreator.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException(ex);
        }

    }

    public final T  getDataSet(){
        return this.dataset;
    }
    

    /**
     *  <p style="margin-top: 0">
     *        should initialize appropriate dataset object for chart types
     *      </p>
     */
    protected abstract T initializeDataset ();

    protected void beforeDatasetPreparation() {
        //empty
    }

    protected void afterDatasetPreparation() {
      // empty
    }

}

