package com.flytxt.commons.reporting.chart.generator.fusioncharts;

import com.flytxt.commons.chart.fusion.api.pie3d.Pie3D;
import com.flytxt.commons.chart.fusion.api.pie3d.Set;
import com.flytxt.commons.reporting.chart.ChartOutput; 
import com.flytxt.commons.reporting.chart.dataset.base.DataSet; 
import com.flytxt.commons.reporting.chart.dataset.base.pie.PieDataSet;
import com.flytxt.commons.reporting.chart.dataset.base.pie.PieSet;
import com.flytxt.commons.reporting.chart.entity.ChartConfig;

public class FusionPieChartGenerator extends AbstractFusionChartGenerator {

    public FusionPieChartGenerator () {
    }

    public void prepareFusionChart (ChartOutput chartoutput, DataSet dataset) {

        PieDataSet pieDataSet = (PieDataSet)dataset;

        Pie3D fusionPie =  new Pie3D();
        fusionPie.setAnimation("1");
        ChartConfig cfg =  this.ctx.getChart();
        if(cfg.isShowTitle())
        fusionPie.setCaption(this.ctx.getChart().getDescription());
        else
            fusionPie.setCaption(null);

        fusionPie.setShowPercentValues("1");
        fusionPie.setShowPercentInToolTip("1");
        if(cfg.isShowLegend())
        fusionPie.setShowLabels("1");
        else
            fusionPie.setShowLabels("0");

        fusionPie.setUse3DLighting("1");

        for(PieSet pieSet: pieDataSet){
            Set fusionPieSet =  fusionPie.createSetNode();
            fusionPieSet.setLabel(pieSet.getLabel());
            fusionPieSet.setValue(pieSet.getValue());
        }
        chartoutput.setContent(fusionPie.toString().getBytes());


        
    }

}

