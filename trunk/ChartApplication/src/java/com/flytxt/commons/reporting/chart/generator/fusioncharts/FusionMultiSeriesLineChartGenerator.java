package com.flytxt.commons.reporting.chart.generator.fusioncharts;

import com.flytxt.commons.chart.fusion.api.msline.Set;
import com.flytxt.commons.chart.fusion.api.msline.Dataset;
import com.flytxt.commons.chart.fusion.api.msline.MSLine;
import com.flytxt.commons.chart.fusion.api.msline.Categories;

import com.flytxt.commons.reporting.chart.ChartOutput; 
import com.flytxt.commons.reporting.chart.dataset.base.DataSet; 
import com.flytxt.commons.reporting.chart.dataset.base.multiseries.line.MultiSeriesCategory;
import com.flytxt.commons.reporting.chart.dataset.base.multiseries.line.MultiseriesLineDataSet;
import com.flytxt.commons.reporting.chart.entity.ChartConfig;
import java.util.HashMap;
import java.util.Map;
/**
 * 
 * @author vishnu.sankar
 */
public class FusionMultiSeriesLineChartGenerator extends AbstractFusionChartGenerator {

    public FusionMultiSeriesLineChartGenerator () {
    }

    public void prepareFusionChart (ChartOutput chartoutput, DataSet dataset) {

        MultiseriesLineDataSet  _dataSet =    (MultiseriesLineDataSet)dataset;

        MSLine column3d =    new MSLine();
        column3d.setAnimation("1");
         column3d.setSlantLabels("1");
        column3d.setLabelDisplay("Rotate");
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
            Dataset ds =
            column3d.createDatasetNode();
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

