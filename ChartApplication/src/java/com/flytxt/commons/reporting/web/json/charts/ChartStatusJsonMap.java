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

/**
 * typically used for async call with return js as ok
 * @author Merrill George (merrill.george@gmail.com)
 */
public class ChartStatusJsonMap extends AbstractChartJsonMap {

    public ChartJsonMap buildMap() {
       this.data.put("ok", true);
       return this;
    }

}
