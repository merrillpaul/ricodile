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
package com.flytxt.commons.reporting.chart.engine;

import com.flytxt.commons.reporting.chart.ChartContext;
import com.flytxt.commons.reporting.chart.ChartEngine;
import com.flytxt.commons.reporting.chart.ChartException;
import com.flytxt.commons.reporting.chart.ChartOutput;

/**
 *
 * @author Merrill George (merrill.george@gmail.com)
 */
public abstract class AbstractChartEngine implements ChartEngine {


    protected abstract ChartOutput createChartOutput(ChartContext ctx) throws
            ChartException;

    public ChartOutput fillUp(ChartContext ctx) throws ChartException {

        beforeFillUp(ctx);
        ChartOutput chartOutput = createChartOutput(ctx);
        afterFillUp(chartOutput,ctx);
        return chartOutput;
    }

    protected abstract void beforeFillUp(ChartContext ctx)
            throws ChartException;

    protected abstract void afterFillUp(
            ChartOutput chartOutput, ChartContext ctx ) throws ChartException;

}
