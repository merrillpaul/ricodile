package com.flytxt.commons.reporting.chart.generator.fusioncharts;

import com.flytxt.commons.chart.fusion.api.area2d.Area2D;
import com.flytxt.commons.chart.fusion.api.area2d.Set;


import com.flytxt.commons.reporting.chart.ChartOutput; 
import com.flytxt.commons.reporting.chart.dataset.base.DataSet; 
import com.flytxt.commons.reporting.chart.dataset.base.pie.PieDataSet;
import com.flytxt.commons.reporting.chart.dataset.base.pie.PieSet;
import com.flytxt.commons.reporting.chart.entity.ChartConfig;
/**
 * 
 * @author vishnu.sankar
 */
public class FusionAreaChartGenerator extends AbstractFusionChartGenerator {

    public FusionAreaChartGenerator () {
    }

    public void prepareFusionChart (ChartOutput chartoutput, DataSet dataset) {

        PieDataSet pieDataSet = (PieDataSet)dataset;

        Area2D fusionPie =  new Area2D();
        fusionPie.setAnimation("1");
        ChartConfig cfg =  this.ctx.getChart();
        if(cfg.isShowTitle())
        fusionPie.setCaption(this.ctx.getChart().getDescription());
        else
            fusionPie.setCaption(null);     

        if(cfg.isShowLegend())
        fusionPie.setShowLabels("1");
        else
            fusionPie.setShowLabels("0");
        for(PieSet pieSet: pieDataSet){
            Set fusionPieSet =  fusionPie.createSetNode();
            fusionPieSet.setLabel(pieSet.getLabel());
            fusionPieSet.setValue(pieSet.getValue());
        }
        chartoutput.setContent(fusionPie.toString().getBytes());


        
    }

}

