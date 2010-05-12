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
 * This is a map which keeps a bag of chartcontexts alive in the platform.
 * This is beacsue we can have same charts (same id) with diferenmt context running
 * together at a point in time
 *
 * Every context is assigned an id has a lifecycle from ceration, retrieval ,
 * usage and destruction
 * @author Merrill George (merrill.george@gmail.com)
 */
public interface ChartRunMap {
    void addContext(String chartRunSessionId, ChartContext context);

    ChartContext getContext(String chartRunSessionId);

    void removeContext(String chartRunSessionId);
}
