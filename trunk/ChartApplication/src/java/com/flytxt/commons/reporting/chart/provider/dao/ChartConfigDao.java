/*
 * @(#) $Id:
 * Copyright Flytxt Technologies Pvt Limited. All Rights Reserved.
 *
 * This Software is the proprietary information of Flytxt technologies Pvt Limited.
 * Use is subject to License terms.
 *
 */
package com.flytxt.commons.reporting.chart.provider.dao;


import com.flytxt.commons.dao.Dao;
import com.flytxt.commons.dao.DaoImpl;
import com.flytxt.commons.reporting.chart.entity.ChartConfig;
/**
 *
 * @author Merrill George (merrill.george@gmail.com)
 */
@DaoImpl("com.flytxt.commons.reporting.chart.provider.dao.impl.ChartConfigDaoImpl")
public interface ChartConfigDao extends Dao {
    ChartConfig getChartConfig(Long chartId);

    public ChartConfig saveChartConfig(ChartConfig chart);

    void deleteChart(Long chartId);

    boolean isParameterUsed(Long parameterId);
}
