package com.flytxt.commons.reporting.chart.generator.fusioncharts;

import com.flytxt.commons.chart.fusion.api.doughnut3d.Doughnut3D;
import com.flytxt.commons.chart.fusion.api.doughnut3d.Set;
import com.flytxt.commons.reporting.chart.ChartOutput; 
import com.flytxt.commons.reporting.chart.dataset.base.DataSet; 
import com.flytxt.commons.reporting.chart.dataset.base.pie.PieDataSet;
import com.flytxt.commons.reporting.chart.dataset.base.pie.PieSet;
import com.flytxt.commons.reporting.chart.entity.ChartConfig;
/**
 * 
 * @author vishnu.sankar
 */
public class FusionRingChartGenerator extends AbstractFusionChartGenerator {

    public FusionRingChartGenerator () {
    }

    public void prepareFusionChart (ChartOutput chartoutput, DataSet dataset) {

        PieDataSet pieDataSet = (PieDataSet)dataset;

        Doughnut3D fusionPie =  new Doughnut3D();
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

