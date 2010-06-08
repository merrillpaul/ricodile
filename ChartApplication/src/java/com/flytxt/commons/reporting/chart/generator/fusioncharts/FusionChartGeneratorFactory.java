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
package com.flytxt.commons.reporting.chart.generator.fusioncharts;

import com.flytxt.commons.reporting.chart.ChartContext;
import com.flytxt.commons.reporting.chart.generator.ChartGenerator;
import com.flytxt.commons.reporting.chart.generator.ChartGeneratorFactory;
import com.flytxt.commons.reporting.constants.ChartConstants.ChartType;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Merrill George (merrill.george@gmail.com)
 */
public class FusionChartGeneratorFactory implements ChartGeneratorFactory {

    public ChartGenerator getGenerator(ChartContext ctx) {

        ChartGenerator generator = null;
        try {
            generator = generatorMappings.get(ctx.getChart().getChartTypeAsEnum()).newInstance();
        } catch (InstantiationException ex) {
            Logger.getLogger(FusionChartGeneratorFactory.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
            throw new RuntimeException(ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(FusionChartGeneratorFactory.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }

        generator.setChartContext(ctx);
        return generator;
    }

    private static Map<ChartType, Class<? extends AbstractFusionChartGenerator>>
            generatorMappings = null;


    static {
        generatorMappings =
                new HashMap<ChartType,
                Class<? extends AbstractFusionChartGenerator>>();

        generatorMappings.put(ChartType.PIE, FusionPieChartGenerator.class);
        generatorMappings.put(ChartType.RING, FusionRingChartGenerator.class);
        generatorMappings.put(ChartType.LINE, FusionLineChartGenerator.class);
        generatorMappings.put(ChartType.AREA, FusionAreaChartGenerator.class);
        generatorMappings.put(ChartType.COLUMN, FusionSimpleColumnChartGenerator.class);
        generatorMappings.put(ChartType.BAR, FusionSimpleBarChartGenerator.class);
        generatorMappings.put(ChartType.MULTI_SERIES_BAR, FusionBarChartGenerator.class);
        generatorMappings.put(ChartType.MULTI_SERIES_COLUMN, FusionColumnChartGenerator.class);
        generatorMappings.put(ChartType.MULTI_SERIES_LINE, FusionMultiSeriesLineChartGenerator.class);
        generatorMappings.put(ChartType.MULTI_SERIES_AREA, FusionMultiSeriesAreaChartGenerator.class);
        
    }

}
