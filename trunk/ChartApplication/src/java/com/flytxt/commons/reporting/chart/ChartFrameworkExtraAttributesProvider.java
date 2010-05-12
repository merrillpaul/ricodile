/*
 * @(#) $Id:
 * Copyright Flytxt Technologies Pvt Limited. All Rights Reserved.
 *
 * This Software is the proprietary information of Flytxt technologies Pvt Limited.
 * Use is subject to License terms.
 *
 */
package com.flytxt.commons.reporting.chart;

import java.util.Map;

/**
 * This contract gets extra framework atributes for a chart
 * @author Merrill George (merrill.george@gmail.com)
 */
public interface ChartFrameworkExtraAttributesProvider {
    Map<String,String> getExtraParameters(ChartContext ctx);
}
