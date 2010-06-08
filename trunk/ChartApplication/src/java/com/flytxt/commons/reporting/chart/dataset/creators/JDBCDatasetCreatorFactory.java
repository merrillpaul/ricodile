/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/*
 * @(#) $Id:
 * Copyright Flytxt Technologies Pvt Limited. All Rights Reserved.
 *
 * This Software is the proprietary information of Flytxt technologies Pvt Limited.
 * Use is subject to License terms.
 *
 */
package com.flytxt.commons.reporting.chart.dataset.creators;

import com.flytxt.commons.reporting.chart.dataset.creators.columnbar.ResultsetAwareSimpleColumnBarDatasetCreator;
import com.flytxt.commons.reporting.chart.dataset.creators.multiseries.ResultsetAwareMultiSeriesAreaDatasetCreator;
import com.flytxt.commons.reporting.chart.dataset.creators.multiseries.ResultsetAwareMultiSeriesColumnBarDatasetCreator;
import com.flytxt.commons.reporting.chart.dataset.creators.multiseries.ResultsetAwareMultiSeriesLineDatasetCreator;
import com.flytxt.commons.reporting.chart.dataset.creators.pie.ResultsetAwarePieDatasetCreator;
import com.flytxt.commons.reporting.constants.ChartConstants.ChartType;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Merrill George (merrill.george@gmail.com)
 */
public final class JDBCDatasetCreatorFactory {

    static JDBCResultsetAwareCreator lookup(ChartType chartTypeAsEnum) throws InstantiationException, IllegalAccessException {
        Class<? extends JDBCResultsetAwareCreator> creatorClass =
                lookupJDBCCreatorClass(chartTypeAsEnum);
        return creatorClass.newInstance();
    }



    private static Map<ChartType,Class<? extends JDBCResultsetAwareCreator>>
           chartTypeCreatorMap = null;

   static{
        chartTypeCreatorMap =
                new HashMap<ChartType, Class<? extends JDBCResultsetAwareCreator>>();
       chartTypeCreatorMap.put(ChartType.PIE, ResultsetAwarePieDatasetCreator.class);
        chartTypeCreatorMap.put(ChartType.RING, ResultsetAwarePieDatasetCreator.class);

        chartTypeCreatorMap.put(ChartType.LINE, ResultsetAwarePieDatasetCreator.class);
        chartTypeCreatorMap.put(ChartType.AREA, ResultsetAwarePieDatasetCreator.class);
        chartTypeCreatorMap.put(ChartType.BAR, ResultsetAwareSimpleColumnBarDatasetCreator.class);
        chartTypeCreatorMap.put(ChartType.COLUMN, ResultsetAwareSimpleColumnBarDatasetCreator.class);

        chartTypeCreatorMap.put(ChartType.MULTI_SERIES_BAR, ResultsetAwareMultiSeriesColumnBarDatasetCreator.class);
        chartTypeCreatorMap.put(ChartType.MULTI_SERIES_COLUMN, ResultsetAwareMultiSeriesColumnBarDatasetCreator.class);
        chartTypeCreatorMap.put(ChartType.MULTI_SERIES_LINE, ResultsetAwareMultiSeriesLineDatasetCreator.class);
        chartTypeCreatorMap.put(ChartType.MULTI_SERIES_AREA, ResultsetAwareMultiSeriesAreaDatasetCreator.class);


   }


   private static Class<? extends JDBCResultsetAwareCreator> lookupJDBCCreatorClass(ChartType chartType){
       return chartTypeCreatorMap.get(chartType);
   }
}
