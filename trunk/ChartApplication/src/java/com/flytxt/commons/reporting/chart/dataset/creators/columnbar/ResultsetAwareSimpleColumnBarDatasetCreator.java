package com.flytxt.commons.reporting.chart.dataset.creators.columnbar;


import com.flytxt.commons.reporting.chart.ChartException;
import com.flytxt.commons.reporting.chart.dataset.base.multiseries.columnbar.MultiSeriesCategory;
import com.flytxt.commons.reporting.chart.dataset.base.multiseries.columnbar.MultiSeriesDataSet;
import com.flytxt.commons.reporting.chart.dataset.base.multiseries.columnbar.MultiseriesColumnBarDataSet;
import com.flytxt.commons.reporting.chart.dataset.creators.JDBCResultsetAwareCreator; 
import com.flytxt.commons.reporting.chart.dataset.jdbc.ChartResultSetPreparer;
import com.flytxt.commons.reporting.parameter.ParameterProviderException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ResultsetAwareSimpleColumnBarDatasetCreator
        extends SimpleColumnBarDatasetCreator implements JDBCResultsetAwareCreator {
    private ChartResultSetPreparer resultSetPreparer;

    public ResultsetAwareSimpleColumnBarDatasetCreator () {
    }

   

    /**
     *  <p style="margin-top: 0">
     *    setter for the JDBC resultset which is the result of a query
     *      </p>
     */
  
     /**
      * The query might be with a series name or without one
      * with one eg
      * select name,value,'Jan2007' from soemtable
      * wihtout one
      * select name,value from sometable
      * @throws ChartException
      */
    public void prepareDataset() throws ChartException {
        try {
            MultiseriesColumnBarDataSet dataSet = this.getDataSet();
            ResultSet resultSet = this.resultSetPreparer.getResultSet(this, chartContext);
            ResultSetMetaData rsmds = resultSet.getMetaData();
            int noColumns = rsmds.getColumnCount();
            int seriesColumnIndex = -1;
            if(noColumns==3){
                seriesColumnIndex=3;
            }else if(noColumns>3){

                for(int i=0;i<noColumns;i++){
                 if("seriesname".equalsIgnoreCase( rsmds.getColumnName((i+1)) ))
                 {
                     seriesColumnIndex = (i+1);
                     break;
                 }
                }
            }



            while (resultSet.next()) {
                MultiSeriesCategory msc =
                dataSet.addCategory(resultSet.getString(1));
                
                MultiSeriesDataSet msds=
                dataSet.addDataSet(msc.getLabel(),seriesColumnIndex==-1?null:resultSet.getString(seriesColumnIndex), resultSet.getString(2));



            }
        } catch (SQLException ex) {
            Logger.getLogger(ResultsetAwareSimpleColumnBarDatasetCreator.class.getName()).log(Level.SEVERE, null, ex);
             throw new ChartException(ex);
        } catch (ParameterProviderException ex) {
            Logger.getLogger(ResultsetAwareSimpleColumnBarDatasetCreator.class.getName()).log(Level.SEVERE, null, ex);
             throw new ChartException(ex);
        }


    }

    public void setResultSetPreparer(ChartResultSetPreparer resultSetPreparer) {
        this.resultSetPreparer =  resultSetPreparer;
    }

}

