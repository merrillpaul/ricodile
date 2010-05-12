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
package com.flytxt.commons.reporting.web.json.charts;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Merrill George (merrill.george@gmail.com)
 */
public abstract class AbstractChartJsonMap implements ChartJsonMap {
    protected Map<String,Object> data;

    public AbstractChartJsonMap() {
        this.data = new HashMap<String, Object>();
       
    }

    public Map<String, Object> getMap() {
        return data;
    }

}
