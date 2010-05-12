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
import com.flytxt.commons.reporting.chart.provider.vo.ChartTypeVO;
import java.util.Collection;

/**
 *
 * @author Merrill George (merrill.george@gmail.com)
 */
@ServiceImpl("com.flytxt.commons.reporting.chart.provider.service.impl.ChartConstantsProviderServiceImpl")
public interface ChartConstantsProviderService extends BusinessService {

    Collection<ChartTypeVO> getChartTypes();

}
