package com.flytxt.commons.reporting.chart.dataset.creators.pie;


import com.flytxt.commons.reporting.chart.ChartException;
import com.flytxt.commons.reporting.chart.dataset.base.pie.PieSet;
import com.flytxt.commons.reporting.chart.dataset.creators.JDBCResultsetAwareCreator; 
import com.flytxt.commons.reporting.chart.dataset.jdbc.ChartResultSetPreparer;
import com.flytxt.commons.reporting.parameter.ParameterProviderException;
import java.sql.ResultSet; 
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ResultsetAwarePieDatasetCreator extends
        PieDatasetCreator implements JDBCResultsetAwareCreator {

    private ChartResultSetPreparer resultSetPreparer;

    public ResultsetAwarePieDatasetCreator () {

    }

    public void prepareDataset () throws ChartException{
        try {



            ResultSet resultSet = this.resultSetPreparer.getResultSet(this, chartContext);
            while (resultSet.next()) {
                
                String value = resultSet.getString("VALUE");
                String label =resultSet.getString("LABEL");
                PieSet pieset =
                        this.getDataSet()
                        .addPiece(
                        label,
                        value);
                //pieset.setTooltipText(label+" : "+value);
            }
            resultSet.close();
        } catch (ParameterProviderException ex) {
            Logger.getLogger(ResultsetAwarePieDatasetCreator.class.getName()).log(Level.SEVERE, null, ex);
            throw new ChartException(ex);
        } catch (SQLException ex) {
            Logger.getLogger(ResultsetAwarePieDatasetCreator.class.getName()).log(Level.SEVERE, null, ex);
            throw new ChartException(ex);
        }finally{
            this.resultSetPreparer.cleanUp();
        }
    }

    public void setResultSetPreparer(ChartResultSetPreparer resultSetPreparer) {
        this.resultSetPreparer =  resultSetPreparer;
    }

    /**
     *  <p style="margin-top: 0">
     *    setter for the JDBC resultset which is the result of a query
     *      </p>
     */


}

