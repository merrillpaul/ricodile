/*
 * @(#) $Id:
 * Copyright Flytxt Technologies Pvt Limited. All Rights Reserved.
 *
 * This Software is the proprietary information of Flytxt technologies Pvt Limited.
 * Use is subject to License terms.
 *
 */
package com.flytxt.commons.reporting.chart;

/**
 *
 * @author Merrill George (merrill.george@gmail.com)
 */
public class ChartException extends Exception {

    public ChartException(Throwable cause) {
        super(cause);
    }

    public ChartException(String message, Throwable cause) {
        super(message, cause);
    }

    public ChartException(String message) {
        super(message);
    }

    public ChartException() {
    }

}
