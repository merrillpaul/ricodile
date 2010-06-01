package com.flytxt.commons.reporting.chart.dataset.creators;

import com.flytxt.commons.reporting.chart.dataset.jdbc.ChartResultSetPreparer;
import java.util.List;

public interface JDBCResultsetAwareCreator {

    /**
     *  <p style="margin-top: 0">
     *        setter for the JDBC resultset which is the result of a query
     *      </p>
     */
    public void setResultSetPreparer(ChartResultSetPreparer resultSetPreparer);

    /**
     * This inner class is a mappingfor
     * seriescolumn name and corresponding seriesvalue which is fethched using
     * resultsetmetadata. This will be used in case of multiseries or single series
     * line/bar/area/column chart types
     *
     * Here the SQLs i the framework can be categorised as the following :
     * <pre>
     * 1. select label "category name", value from sometable
     *  In this case this is a single series chart. The series name would be null
     *
     * 2. select label "category_name" , value, some_other_columnm from sometable
     *  In this case the third column would be the seriesName. Typically such queries
     * will have  a union ( separate tuple of selects) to demarcate different series.
     *
     *
     * 3. select label "category_name", value, some_drilldownabe_colun, someothercolumn "seriesname" from sometable
     * same as point 2, but the framework does not assume that the seriesname value will be the third column. instead
     * it checks against the columnname which is 'seriesname' to resolve the sreiesName string
     *
     * 4.
     * select label "category_name", series_name_column "seriesname1", series_name_value "seriesvalue1",
     * series_name_column2 "seriesname2" , series_name_value1 "seriesvalue2" ,someothercolumn, someotherdrilldownable_column
     * from sometable
     *
     * Here this is not a tuple anyomre. each row in the result gets the entire information for all the series
     * the framework needs to resolve the series name and corresponding values based on the
     * colun alias name
     *
     *
     * </pre>
     */
    public class SeriesColumnNameMapping {
        public String seriesColumnName;
        public String valueColumnName;
    }



    public class ResultSetColumnInfo{
        private List<SeriesColumnNameMapping> seriesColumns;
        private List<String> drillDownColumnNames;

        public ResultSetColumnInfo(List<SeriesColumnNameMapping> seriesColumns, List<String> drillDownColumnNames) {
            this.seriesColumns = seriesColumns;
            this.drillDownColumnNames = drillDownColumnNames;
        }

        public List<String> getDrillDownColumnNames() {
            return drillDownColumnNames;
        }

        public List<SeriesColumnNameMapping> getSeriesColumns() {
            return seriesColumns;
        }

        

    }
}

