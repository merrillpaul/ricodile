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
public class DelegatingChartEngine extends AbstractChartEngine implements ChartEngine {

    private AbstractChartEngine engine;

    @Override
    protected ChartOutput createChartOutput(ChartContext ctx) throws ChartException {
        return engine.fillUp(ctx);
    }

    public DelegatingChartEngine(AbstractChartEngine engine) {
        this.engine = engine;
    }

    @Override
    protected void beforeFillUp(ChartContext ctx) throws ChartException {
       engine.beforeFillUp( ctx);
    }

    @Override
    protected void afterFillUp(ChartOutput chartOutput, ChartContext ctx) throws ChartException {
        engine.afterFillUp(chartOutput, ctx);
    }







}
