package com.flytxt.commons.reporting.chart.dataset.creators;

import com.flytxt.commons.reporting.chart.dataset.jdbc.ChartResultSetPreparer;
import java.sql.ResultSet; 

public interface JDBCResultsetAwareCreator {

    /**
     *  <p style="margin-top: 0">
     *        setter for the JDBC resultset which is the result of a query
     *      </p>
     */
    public void setResultSetPreparer (ChartResultSetPreparer resultSetPreparer);

}

