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
package com.flytxt.commons.reporting.chart.generator;

import com.flytxt.commons.reporting.chart.ChartContext;

/**
 *
 * @author Merrill George (merrill.george@gmail.com)
 */
public interface ChartGeneratorFactory {
    ChartGenerator getGenerator(ChartContext ctx);
}
