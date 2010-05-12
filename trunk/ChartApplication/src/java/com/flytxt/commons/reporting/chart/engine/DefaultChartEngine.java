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
package com.flytxt.commons.reporting.chart.engine;

import com.flytxt.commons.reporting.chart.ChartContext;
import com.flytxt.commons.reporting.chart.ChartException;
import com.flytxt.commons.reporting.chart.ChartOutput;
import com.flytxt.commons.reporting.chart.dataset.base.DataSet;
import com.flytxt.commons.reporting.chart.dataset.creators.DatasetCreator;
import com.flytxt.commons.reporting.chart.dataset.creators.DatasetCreatorFactory;
import com.flytxt.commons.reporting.chart.generator.ChartGenerator;
import com.flytxt.commons.reporting.chart.generator.ChartGeneratorFactoryFactory;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Merrill George (merrill.george@gmail.com)
 */
public class DefaultChartEngine extends AbstractChartEngine {

    @Override
    protected ChartOutput createChartOutput(ChartContext ctx) {

        DatasetCreator creator = DatasetCreatorFactory.getDatasetCreator(ctx);
        creator.createDataset();
        DataSet dataset =  creator.getDataSet();
        ChartGenerator generator = ChartGeneratorFactoryFactory
                .getChartGeneratorFactory(ctx.getRenderType())
                .getGenerator(ctx);
        ChartOutput output = generator.generateChart(dataset);
        return output;
    }

    @Override
    protected void beforeFillUp(ChartContext ctx) throws ChartException {
         Logger
                 .getLogger(DefaultChartEngine.class.getName())
                 .log(Level.INFO, "Default before Fill");
    }

    @Override
    protected void afterFillUp(ChartOutput chartOutput, ChartContext ctx) throws ChartException {
        Logger
                 .getLogger(DefaultChartEngine.class.getName())
                 .log(Level.INFO, "Default after Fill");
    }

}
