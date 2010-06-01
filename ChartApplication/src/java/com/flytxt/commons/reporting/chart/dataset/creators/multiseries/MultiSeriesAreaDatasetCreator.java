package com.flytxt.commons.reporting.chart.dataset.creators.multiseries;


import com.flytxt.commons.reporting.chart.dataset.base.multiseries.area.MultiseriesAreaDataSet;

import com.flytxt.commons.reporting.chart.dataset.creators.AbstractDatasetCreator; 
/**
 * 
 * @author vishnu.sankar
 */
public abstract class MultiSeriesAreaDatasetCreator 
        extends AbstractDatasetCreator<MultiseriesAreaDataSet> {

   

    @Override
    protected MultiseriesAreaDataSet initializeDataset() {
       MultiseriesAreaDataSet p =  new MultiseriesAreaDataSet();
       return p;
    }




}

