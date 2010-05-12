/*
 * @(#) $Id:
 * Copyright Flytxt Technologies Pvt Limited. All Rights Reserved.
 *
 * This Software is the proprietary information of Flytxt technologies Pvt Limited.
 * Use is subject to License terms.
 *
 */
package com.flytxt.commons.reporting.web.json.charts;


import java.util.Map;

/**
 *
 * @author Merrill George (merrill.george@gmail.com)
 */
public interface ChartJsonMap {

    Map<String,Object> getMap();

    /**
     * Should return the current object itslef
     * This is for ease of code and shoerthand
     * Similar to StringBuffer.append()
     * @return
     */
    ChartJsonMap buildMap();
}
