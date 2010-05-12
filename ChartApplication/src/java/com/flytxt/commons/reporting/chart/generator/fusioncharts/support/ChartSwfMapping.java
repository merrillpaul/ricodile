/*
 * @(#) $Id:
 * Copyright Flytxt Technologies Pvt Limited. All Rights Reserved.
 *
 * This Software is the proprietary information of Flytxt technologies Pvt Limited.
 * Use is subject to License terms.
 *
 */
package com.flytxt.commons.reporting.chart.generator.fusioncharts.support;

import com.flytxt.commons.reporting.constants.ChartConstants;
import java.util.HashMap;
import java.util.Map;


/**
 * Fusion chart mapping to get the swf file for the chartType
 * The actual location or the prefix url of the swf will be
 * made availbale while a chartContext is created
 * @author Merrill George Paul (merrill.george@gmail.com)
 */
public class ChartSwfMapping {

    
    
   // internal map to store the actual swf file name to the chartType
   private static Map<ChartConstants.ChartType,String> swfMap =
            new HashMap<ChartConstants.ChartType, String>();

   static{

       swfMap.put(ChartConstants.ChartType.PIE, "Pie3D");
       swfMap.put(ChartConstants.ChartType.AREA, "Area2D");
       swfMap.put(ChartConstants.ChartType.BAR, "MSBar3D");
       swfMap.put(ChartConstants.ChartType.BUBBLE, "Bubble");
       swfMap.put(ChartConstants.ChartType.COLUMN, "Column3D");

       swfMap.put(ChartConstants.ChartType.COLUMN_LINE_DUAL_Y_COMBO, "MSColumn3DLineDY");
       swfMap.put(ChartConstants.ChartType.COLUMN_LINE_SINGLE_Y_COMBO, "MSColumnLine3D");
       swfMap.put(ChartConstants.ChartType.LINE, "Line");
       swfMap.put(ChartConstants.ChartType.MULTI_SERIES_AREA, "MSArea");


       swfMap.put(ChartConstants.ChartType.MULTI_SERIES_BAR, "MSBar3D");
       swfMap.put(ChartConstants.ChartType.MULTI_SERIES_COLUMN, "MSColumn3D");
       swfMap.put(ChartConstants.ChartType.MULTI_SERIES_LINE, "MSLine");
       swfMap.put(ChartConstants.ChartType.RING, "Doughnut3D");


       swfMap.put(ChartConstants.ChartType.SINGLE_Y_ANY_COMBO, "MSCombi3D");
       swfMap.put(ChartConstants.ChartType.STACKED_COLUMN_DUAL_Y_COMBO, "StackedColumn3DLineDY");
       swfMap.put(ChartConstants.ChartType.STACKED_SERIES_AREA, "StackedArea2D");
       swfMap.put(ChartConstants.ChartType.STACKED_SERIES_BAR, "StackedBar3D");

       swfMap.put(ChartConstants.ChartType.STACKED_SERIES_COLUMN, "StackedColumn3D");
       swfMap.put(ChartConstants.ChartType.XY_SCATTER, "Scatter");



   }


   public static String getSwfFileName(ChartConstants.ChartType chartType){

       return swfMap.get(chartType);
   }


}

