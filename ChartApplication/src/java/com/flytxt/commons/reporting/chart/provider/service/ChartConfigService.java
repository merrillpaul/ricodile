/*
 * @(#) $Id:
 * Copyright Flytxt Technologies Pvt Limited. All Rights Reserved.
 *
 * This Software is the proprietary information of Flytxt technologies Pvt Limited.
 * Use is subject to License terms.
 *
 */
package com.flytxt.commons.reporting.chart.provider.service;


import com.flytxt.commons.facade.BusinessService;
import com.flytxt.commons.facade.ServiceImpl;
import com.flytxt.commons.reporting.chart.entity.ChartConfig;
import com.flytxt.commons.reporting.chart.provider.ChartConfigException;
import com.flytxt.commons.reporting.parameter.objects.Parameter;
import com.flytxt.commons.reporting.parameter.vo.ParamComboVO;
import java.util.Collection;
/**
 *
 * @author Merrill George (merrill.george@gmail.com)
 */
 @ServiceImpl("com.flytxt.commons.reporting.chart.provider.service.impl.ChartConfigServiceImpl")
public interface ChartConfigService extends BusinessService {

    ChartConfig getChartConfig(Long chartId);

    ChartConfig getChartConfig(String chartName);

    ChartConfig saveChartConfig(ChartConfig chart) throws ChartConfigException;

    void deleteChartConfig(Long chartId) throws ChartConfigException;

    void addChartParameter(Parameter p,Long chartId) throws ChartConfigException;

    void updateChartParameters(Collection<Parameter> parameters,Long chartId) throws ChartConfigException;

    Collection<ParamComboVO> getUnAssignedParameters(Long chartId);

}
