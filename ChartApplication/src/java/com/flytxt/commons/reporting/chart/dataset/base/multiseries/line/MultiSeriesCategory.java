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
package com.flytxt.commons.reporting.chart.dataset.base.multiseries.line;

import com.flytxt.commons.reporting.chart.dataset.base.Category;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author vishnu sankar
 */
public class MultiSeriesCategory  extends Category{

    private Map<String,MultiSeriesDataSet> seriesValues;

    public MultiSeriesCategory(String label) {
        super();
        this.setLabel(label);
        this.seriesValues = new HashMap<String, MultiSeriesDataSet>();
    }

   public MultiSeriesDataSet addDataSet(String seriesName,String value){
        MultiSeriesDataSet ds = new MultiSeriesDataSet();
        ds.setValue(value);
        seriesValues.put(seriesName,ds);
        return ds;
    }


public MultiSeriesDataSet getDataSet(String seriesName){
    return seriesValues.get(seriesName);
}
   



}
