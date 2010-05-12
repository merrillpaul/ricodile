/*
 * @(#) $Id:
 * Copyright Flytxt Technologies Pvt Limited. All Rights Reserved.
 *
 * This Software is the proprietary information of Flytxt technologies Pvt Limited.
 * Use is subject to License terms.
 *
 */
package com.flytxt.commons.reporting.constants;

/**
 * Annotates where the rendering occurs. This is set in chartContext to let the
 * chart framework know where the chart is to be rendreed and invoke appropriate
 * classes to do so
 * @author Merrill George (merrill.george@gmail.com)
 */
public enum ChartRenderTarget {

    /**
     * Ussually a browser or a screen. Typically flash or html charts using OFC
     * or fusion chars or even raphael
     */
    AT_CLIENT,

    /**
     * Typically and export job where the chart is embed in the report
     * or even a chart is send to a email or file as an image. Probably a JFREE
     * stuff
     */
    AT_SERVER;


}

