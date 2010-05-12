package com.flytxt.commons.reporting.chart.dataset.creators.columnbar;


import com.flytxt.commons.reporting.chart.ChartException;
import com.flytxt.commons.reporting.chart.dataset.creators.JDBCResultsetAwareCreator; 
import com.flytxt.commons.reporting.chart.dataset.jdbc.ChartResultSetPreparer;

public class ResultsetAwareSimpleColumnBarDatasetCreator
        extends SimpleColumnBarDatasetCreator implements JDBCResultsetAwareCreator {

    public ResultsetAwareSimpleColumnBarDatasetCreator () {
    }

   

    /**
     *  <p style="margin-top: 0">
     *    setter for the JDBC resultset which is the result of a query
     *      </p>
     */
  

    public void prepareDataset() throws ChartException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setResultSetPreparer(ChartResultSetPreparer resultSetPreparer) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}

