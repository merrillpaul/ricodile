package com.flytxt.commons.reporting.chart.dataset.creators.multiseries;


import com.flytxt.commons.reporting.chart.dataset.base.multiseries.columnbar.MultiseriesColumnBarDataSet;
import com.flytxt.commons.reporting.chart.dataset.creators.AbstractDatasetCreator; 

public abstract class MultiSeriesColumnBarDatasetCreator 
        extends AbstractDatasetCreator<MultiseriesColumnBarDataSet> {

   

    @Override
    protected MultiseriesColumnBarDataSet initializeDataset() {
       MultiseriesColumnBarDataSet p =  new MultiseriesColumnBarDataSet();
       return p;
    }




}

