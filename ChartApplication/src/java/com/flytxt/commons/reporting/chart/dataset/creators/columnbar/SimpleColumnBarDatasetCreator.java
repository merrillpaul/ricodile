package com.flytxt.commons.reporting.chart.dataset.creators.columnbar;


import com.flytxt.commons.reporting.chart.dataset.base.columnbar.ColumnBarDataSet;
import com.flytxt.commons.reporting.chart.dataset.creators.AbstractDatasetCreator; 

public abstract class SimpleColumnBarDatasetCreator extends AbstractDatasetCreator<ColumnBarDataSet> {

  

     /**
     *  <p style="margin-top: 0">
     *    should initialize appropriate dataset object for chart types
     *      </p>
     */
    @Override
    protected ColumnBarDataSet initializeDataset() {
       ColumnBarDataSet p =  new ColumnBarDataSet();
       return p;
    }

   
   

}

