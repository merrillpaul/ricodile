package com.flytxt.commons.reporting.chart.dataset.creators.multiseries;


import com.flytxt.commons.reporting.chart.dataset.base.multiseries.line.MultiseriesLineDataSet;

import com.flytxt.commons.reporting.chart.dataset.creators.AbstractDatasetCreator; 
/**
 * 
 * @author vishnu.sankar
 */
public abstract class MultiSeriesLineDatasetCreator 
        extends AbstractDatasetCreator<MultiseriesLineDataSet> {

   

    @Override
    protected MultiseriesLineDataSet initializeDataset() {
       MultiseriesLineDataSet p =  new MultiseriesLineDataSet();
       return p;
    }




}

