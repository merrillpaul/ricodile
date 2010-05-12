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
package com.flytxt.commons.reporting.chart.provider.service.impl;

import com.flytxt.commons.reporting.chart.provider.service.ChartConstantsProviderService;
import com.flytxt.commons.reporting.chart.provider.vo.ChartTypeVO;
import com.flytxt.commons.reporting.constants.ChartConstants;
import java.util.ArrayList;
import java.util.Collection;

/**
 * A service class which returns all static and constant objects and list to
 * various areas in hte reporting framework
 * @author Merrill George (merrill.george@gmail.com)
 */
public class ChartConstantsProviderServiceImpl implements ChartConstantsProviderService {

    public Collection<ChartTypeVO> getChartTypes() {
        Collection<ChartTypeVO> list =  new ArrayList<ChartTypeVO>();
        ChartTypeVO vo =  null;
        for(ChartConstants.ChartType chartType: ChartConstants.ChartType.values()){
            vo = new ChartTypeVO();
            vo.setId(chartType.name());
            vo.setDescription(chartType.getDescription());
            list.add(vo);
        }
        return list;
    }

}
