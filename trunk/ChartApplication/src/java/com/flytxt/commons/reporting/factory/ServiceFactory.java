/*
 * @(#) $Id:
 * Copyright Flytxt Technologies Pvt Limited. All Rights Reserved.
 *
 * This Software is the proprietary information of Flytxt technologies Pvt Limited.
 * Use is subject to License terms.
 *
 */
package com.flytxt.commons.reporting.factory;

import com.flytxt.commons.facade.BusinessServiceFactory;
import com.flytxt.commons.reporting.ReportServiceResolverException;
import com.flytxt.commons.reporting.chart.ChartContext;
import com.flytxt.commons.reporting.chart.ChartFrameworkExtraAttributesProvider;
import com.flytxt.commons.reporting.chart.generator.fusioncharts.FusionChartExtraAttributesProvider;
import com.flytxt.commons.reporting.chart.provider.service.ChartConfigService;
import com.flytxt.commons.reporting.chart.provider.service.ChartConstantsProviderService;
import com.flytxt.commons.reporting.constants.ChartConstants;
import com.flytxt.commons.reporting.parameter.business.ParameterMasterService;
import com.flytxt.commons.reporting.parameter.provider.InitialParameterProvider;
import com.flytxt.commons.reporting.parameter.provider.impl.InitialParameterProviderImpl;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * A factory class to get all service objects used in the report
 * framework
 * @author Merrill George (merrill.george@gmail.com)
 */
public final class ServiceFactory {

    public static ChartConfigService getChartConfigService() throws ReportServiceResolverException{
        try {
            return BusinessServiceFactory.getBusinessService(ChartConfigService.class);
        } catch (Exception ex) {
            Logger.getLogger(ServiceFactory.class.getName()).log(Level.SEVERE, null, ex);
            throw new ReportServiceResolverException(ex);
        }
    }

    public static ChartConstantsProviderService getChartConstantsService() throws ReportServiceResolverException{
        try {
            return BusinessServiceFactory.getBusinessService(ChartConstantsProviderService.class);
        } catch (Exception ex) {
            Logger.getLogger(ServiceFactory.class.getName()).log(Level.SEVERE, null, ex);
             throw new ReportServiceResolverException(ex);
        }
    }

    public static ChartFrameworkExtraAttributesProvider
            getChartFrameworkAttributesProvider(ChartConstants.ChartRendererType rendererType){
        ChartFrameworkExtraAttributesProvider provider =  null;

        if(rendererType==ChartConstants.ChartRendererType.FUSION)
            provider = new FusionChartExtraAttributesProvider();
        else throw new UnsupportedOperationException("Not implemented for JFREE or others");
        return provider;
    }

    public static InitialParameterProvider getInitialParameterProvider(){
        return new InitialParameterProviderImpl();
    }



    public static ParameterMasterService getParameterMasterService()
            throws ReportServiceResolverException{
         try {
            return BusinessServiceFactory.getBusinessService(ParameterMasterService.class);
        } catch (Exception ex) {
            Logger.getLogger(ServiceFactory.class.getName()).log(Level.SEVERE, null, ex);
             throw new ReportServiceResolverException(ex);
        }
    }

}
