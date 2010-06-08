package com.flytxt.commons.reporting.chart.generator.fusioncharts;

import com.flytxt.commons.chart.fusion.api.line.Line;
import com.flytxt.commons.chart.fusion.api.line.Set;


import com.flytxt.commons.reporting.chart.ChartOutput; 
import com.flytxt.commons.reporting.chart.dataset.base.DataSet; 
import com.flytxt.commons.reporting.chart.dataset.base.pie.PieDataSet;
import com.flytxt.commons.reporting.chart.dataset.base.pie.PieSet;
import com.flytxt.commons.reporting.chart.entity.ChartConfig;
/**
 * 
 * @author vishnu.sankar
 */
public class FusionLineChartGenerator extends AbstractFusionChartGenerator {

    public FusionLineChartGenerator () {
    }

    public void prepareFusionChart (ChartOutput chartoutput, DataSet dataset) {

        PieDataSet pieDataSet = (PieDataSet)dataset;

        Line fusionPie =  new Line();
        fusionPie.setAnimation("1");
          fusionPie.setSlantLabels("1");
        fusionPie.setLabelDisplay("Rotate");
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

