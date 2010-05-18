package com.flytxt.commons.reporting.chart.dataset.creators.columnbar;



import com.flytxt.commons.reporting.chart.dataset.base.multiseries.columnbar.MultiseriesColumnBarDataSet;
import com.flytxt.commons.reporting.chart.dataset.creators.AbstractDatasetCreator; 

public abstract class SimpleColumnBarDatasetCreator extends AbstractDatasetCreator<MultiseriesColumnBarDataSet> {

  

     /**
     *  <p style="margin-top: 0">
     *    should initialize appropriate dataset object for chart types
     *      </p>
     */
    @Override
    protected /*ColumnBarDataSet*/MultiseriesColumnBarDataSet initializeDataset() {
       MultiseriesColumnBarDataSet p =  new MultiseriesColumnBarDataSet();
       return p;
    }

   
   

}

