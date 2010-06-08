package com.flytxt.commons.reporting.chart.generator.fusioncharts;



import com.flytxt.commons.chart.fusion.api.column3d.Column3D;
import com.flytxt.commons.chart.fusion.api.column3d.Set;
import com.flytxt.commons.reporting.chart.ChartOutput;
import com.flytxt.commons.reporting.chart.dataset.base.DataSet;
import com.flytxt.commons.reporting.chart.dataset.base.multiseries.columnbar.MultiSeriesCategory;
import com.flytxt.commons.reporting.chart.dataset.base.multiseries.columnbar.MultiseriesColumnBarDataSet;
import com.flytxt.commons.reporting.chart.entity.ChartConfig;

public class FusionSimpleColumnChartGenerator extends AbstractFusionChartGenerator {

    public FusionSimpleColumnChartGenerator () {
    }

    public void prepareFusionChart (ChartOutput chartoutput, DataSet dataset) {

        MultiseriesColumnBarDataSet _dataSet =
                (MultiseriesColumnBarDataSet)dataset;

        Column3D column3d =
                new Column3D();

        column3d.setSlantLabels("1");
        column3d.setLabelDisplay("Rotate");
        column3d.setAnimation("1");
        ChartConfig cfg =  this.ctx.getChart();
        if(cfg.isShowTitle())
        column3d.setCaption(this.ctx.getChart().getDescription());
        else
            column3d.setCaption(null);

        column3d.setXAxisName(cfg.getXAxisLabel());
        column3d.setYAxisName(cfg.getYAxisLabel());

      
      
        for(MultiSeriesCategory category : _dataSet){

            //categoriesNode.createCategoryNode().setLabel(category.getLabel());
        	 Set set = column3d.createSetNode();
        	 set.setLabel(category.getLabel());
            
        	 int i=0;
        	 for(String seriesName: _dataSet.getSeriesNames()){

            	if(i>0)
            		break;
            	
                if(category.getDataSet(seriesName)!=null)
                		set.setValue(category.getDataSet(seriesName).getValue());
                else
                    set.setValue(null);
                
               
                i++;
                
                
            }
        }


         chartoutput.setContent(column3d.toString().getBytes());


    }

}

