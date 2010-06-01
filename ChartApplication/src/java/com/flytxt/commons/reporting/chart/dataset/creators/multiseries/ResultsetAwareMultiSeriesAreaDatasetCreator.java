package com.flytxt.commons.reporting.chart.dataset.creators.multiseries;


import com.flytxt.commons.reporting.chart.ChartException;
import com.flytxt.commons.reporting.chart.dataset.base.multiseries.area.MultiseriesAreaDataSet;
import com.flytxt.commons.reporting.chart.dataset.base.multiseries.area.MultiSeriesCategory;
import com.flytxt.commons.reporting.chart.dataset.base.multiseries.area.MultiSeriesDataSet;
import com.flytxt.commons.reporting.chart.dataset.creators.JDBCResultsetAwareCreator; 
import com.flytxt.commons.reporting.chart.dataset.jdbc.ChartResultSetPreparer;
import com.flytxt.commons.reporting.parameter.ParameterProviderException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 * @author vishnu.sankar
 */
public class ResultsetAwareMultiSeriesAreaDatasetCreator
        extends MultiSeriesAreaDatasetCreator implements JDBCResultsetAwareCreator {
    private ChartResultSetPreparer resultSetPreparer;

    public ResultsetAwareMultiSeriesAreaDatasetCreator () {
    }

   

   

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
            MultiseriesAreaDataSet dataSet = this.getDataSet();
            ResultSet resultSet = this.resultSetPreparer.getResultSet(this, chartContext);
            ResultSetMetaData rsmds = resultSet.getMetaData();
            ResultSetColumnInfo columnInfo = this.resultSetPreparer.getColumnInfo(rsmds);
            while (resultSet.next()) {
                MultiSeriesCategory msc =
                dataSet.addCategory(resultSet.getString(1));
                for(SeriesColumnNameMapping mapping :columnInfo.getSeriesColumns()){
                   MultiSeriesDataSet msds=
                         dataSet.addDataSet(msc.getLabel(),mapping.seriesColumnName==null?null:resultSet.getString(mapping.seriesColumnName),resultSet.getString(mapping.valueColumnName));
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ResultsetAwareMultiSeriesColumnBarDatasetCreator.class.getName()).log(Level.SEVERE, null, ex);
             throw new ChartException(ex);
        } catch (ParameterProviderException ex) {
            Logger.getLogger(ResultsetAwareMultiSeriesColumnBarDatasetCreator.class.getName()).log(Level.SEVERE, null, ex);
             throw new ChartException(ex);
        }
    }

    public void setResultSetPreparer(ChartResultSetPreparer resultSetPreparer) {
        this.resultSetPreparer =  resultSetPreparer;
    }

   

}

