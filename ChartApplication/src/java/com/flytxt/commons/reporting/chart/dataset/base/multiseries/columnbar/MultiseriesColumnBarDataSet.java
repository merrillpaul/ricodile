package com.flytxt.commons.reporting.chart.dataset.base.multiseries.columnbar;

import com.flytxt.commons.reporting.chart.dataset.base.DataSet; 
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Iterator;
import java.util.Map;

public class MultiseriesColumnBarDataSet extends DataSet implements Iterable<MultiSeriesCategory> {
    private Map<String,MultiSeriesCategory> categories;
    private Collection<String> series;

    public MultiseriesColumnBarDataSet () {
        categories =  new LinkedHashMap<String, MultiSeriesCategory>();
        series =  new ArrayList<String>();
    }

    public MultiSeriesCategory addCategory (String category) {
      if(this.categories.get(category)==null){
        MultiSeriesCategory msc =   new MultiSeriesCategory(category);
        this.categories.put(category, msc);
      }

      return this.categories.get(category);
    }

  

    public Collection<String> getSeriesNames(){
        return this.series;
    }



    public MultiSeriesDataSet addDataSet(String categoryName,String seriesName,String value){
        if(seriesName==null
                )
            seriesName ="";
        
        if(!this.series.contains(seriesName))
            this.series.add(seriesName);

        return this.categories.get(categoryName).addDataSet(seriesName,value);
    }

    public Iterator<MultiSeriesCategory> iterator() {
       return this.categories.values().iterator();
    }

   
}

