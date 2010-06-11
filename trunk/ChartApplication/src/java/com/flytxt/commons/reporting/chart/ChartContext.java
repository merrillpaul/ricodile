/*
 * @(#) $Id:
 * Copyright Flytxt Technologies Pvt Limited. All Rights Reserved.
 *
 * This Software is the proprietary information of Flytxt technologies Pvt Limited.
 * Use is subject to License terms.
 *
 */
package com.flytxt.commons.reporting.chart;

import com.flytxt.commons.reporting.chart.entity.ChartConfig; 
import com.flytxt.commons.reporting.constants.ChartConstants.ChartRendererType;
import com.flytxt.commons.reporting.constants.RenderTarget;
import com.flytxt.commons.reporting.context.BaseContext;


/**
 * Contains information on how , where and who invoked and rendered the 
 * chart. Also contains the chart configurations and initial parameters
 * The chartcontext is to be persisted in a chart run context, so that
 * the chartcontext can be retrieved at a later stage. The chart context is created
 * when a chart is initially requested for its attributes.
 * Typically the chart context in a web environment is kep in a chartrun map
 * where the chartcontext is mapped to a chartRunSessionId
 *
 * @author Merrill George Paul (merrill.george@gmail.com)
 * 
 */
public class ChartContext extends BaseContext {

    /**
     *  <p style="margin-top: 0">
     *        Typically logged on user id or chart run userid
     *      </p>
     */
   

    /**
     * the chartRenderTarget
     */
    private RenderTarget renderTarget;


   

    public ChartRendererType getRenderType() {
        return renderType;
    }

    /**
     * the acutal chart configuraiton. This might be either from a DB or from a
     * test class
     */
    private ChartConfig chart;
    private ChartRendererType renderType;

    public ChartContext () {
    }

    public ChartConfig getChart() {
        return chart;
    }

    public void setChart(ChartConfig chart) {
        this.chart = chart;
    }

   

    public RenderTarget getRenderTarget() {
        return renderTarget;
    }

    public void setRenderTarget(RenderTarget renderTarget) {
        this.renderTarget = renderTarget;
    }

   

    public void setRendererType(ChartRendererType renderType) {
       this.renderType =  renderType;
    }


   

   

}

