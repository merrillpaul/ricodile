package com.flytxt.commons.reporting.chart.dataset.creators.pie;

 


import com.flytxt.commons.reporting.chart.dataset.base.pie.PieDataSet;
import com.flytxt.commons.reporting.chart.dataset.creators.AbstractDatasetCreator; 

public abstract class PieDatasetCreator extends AbstractDatasetCreator<PieDataSet> {

   



    @Override
    protected PieDataSet initializeDataset() {
        PieDataSet p = new PieDataSet();
        return p;
    }



  
}

