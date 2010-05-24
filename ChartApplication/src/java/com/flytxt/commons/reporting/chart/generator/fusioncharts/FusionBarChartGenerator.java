package com.flytxt.commons.reporting.chart.generator.fusioncharts;


import com.flytxt.commons.chart.fusion.api.msbar3d.Categories;
import com.flytxt.commons.chart.fusion.api.msbar3d.Dataset;
import com.flytxt.commons.chart.fusion.api.msbar3d.MSBar3D;
import com.flytxt.commons.chart.fusion.api.msbar3d.Set;


import com.flytxt.commons.reporting.chart.ChartOutput; 
import com.flytxt.commons.reporting.chart.dataset.base.DataSet; 
import com.flytxt.commons.reporting.chart.dataset.base.multiseries.columnbar.MultiSeriesCategory;
import com.flytxt.commons.reporting.chart.dataset.base.multiseries.columnbar.MultiseriesColumnBarDataSet;
import com.flytxt.commons.reporting.chart.entity.ChartConfig;
import java.util.HashMap;
import java.util.Map;

public class FusionBarChartGenerator extends AbstractFusionChartGenerator {

    public FusionBarChartGenerator () {
    }

    public void prepareFusionChart (ChartOutput chartoutput, DataSet dataset) {

         MultiseriesColumnBarDataSet _dataSet =
                (MultiseriesColumnBarDataSet)dataset;

        MSBar3D column3d =
                new MSBar3D();

        column3d.setAnimation("1");
        ChartConfig cfg =  this.ctx.getChart();
        if(cfg.isShowTitle())
        column3d.setCaption(this.ctx.getChart().getDescription());
        else
            column3d.setCaption(null);

        column3d.setXAxisName(cfg.getXAxisLabel());
        column3d.setYAxisName(cfg.getYAxisLabel());


        Categories categoriesNode =
            column3d.createCategoriesNode();

        Map<String, Dataset> dataSets = new HashMap<String, Dataset>();
        for(String sereisName : _dataSet.getSeriesNames()){
            Dataset ds =  column3d.createDatasetNode();
            ds.setSeriesName(sereisName);
            dataSets.put(sereisName, ds);
        }

        for(MultiSeriesCategory category : _dataSet){

            categoriesNode.createCategoryNode().setLabel(category.getLabel());

            for(String seriesName: _dataSet.getSeriesNames()){

                Set set =  dataSets.get(seriesName).createSetNode();
                 if(category.getDataSet(seriesName)!=null)
                 set.setValue(category.getDataSet(seriesName).getValue());
                else
                    set.setValue(null);

            }
        }


         chartoutput.setContent(column3d.toString().getBytes());

    }



}

