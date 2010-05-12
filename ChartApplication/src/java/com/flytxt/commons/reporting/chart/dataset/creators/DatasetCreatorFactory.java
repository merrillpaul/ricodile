package com.flytxt.commons.reporting.chart.dataset.creators;

import com.flytxt.commons.reporting.chart.ChartContext;
import com.flytxt.commons.reporting.chart.dataset.jdbc.ChartResultSetPreparer;
import com.flytxt.commons.reporting.chart.entity.ChartConfig;
import java.util.logging.Level;
import java.util.logging.Logger;


public final class DatasetCreatorFactory {

   public static DatasetCreator getDatasetCreator(ChartContext ctx){

       ChartConfig chart = ctx.getChart();
       DatasetCreator creator = null;
       if(
               chart.getChartQuery()!=null && 
               chart.getChartQuery().trim().length()>0)
       {
            try {
                JDBCResultsetAwareCreator jdbccreator = JDBCDatasetCreatorFactory.lookup(chart.getChartTypeAsEnum());
                jdbccreator.setResultSetPreparer(new ChartResultSetPreparer());
                creator = (DatasetCreator) jdbccreator;
            } catch (InstantiationException ex) {
                Logger.getLogger(DatasetCreatorFactory.class.getName()).log(Level.SEVERE, null, ex);
                 throw new RuntimeException(ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(DatasetCreatorFactory.class.getName()).log(Level.SEVERE, null, ex);
                 throw new RuntimeException(ex);
            }catch(ClassCastException ex){
                Logger.getLogger(DatasetCreatorFactory.class.getName()).log(Level.SEVERE, null, ex);
                 throw new RuntimeException(ex);
            }
       }else{
            try {
                String creatorClass = chart.getChartClass();
                Class<? extends DatasetCreator> creatorClazz = (Class<? extends DatasetCreator>) Class.forName(creatorClass);
                creator = creatorClazz.newInstance();
            } catch (InstantiationException ex) {
                Logger.getLogger(DatasetCreatorFactory.class.getName()).log(Level.SEVERE, null, ex);
                throw new RuntimeException(ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(DatasetCreatorFactory.class.getName()).log(Level.SEVERE, null, ex);
                throw new RuntimeException(ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(DatasetCreatorFactory.class.getName()).log(Level.SEVERE, null, ex);
                throw new RuntimeException(ex);
            }
       }
       creator.setChartContext(ctx);

       return creator;
   }



  

}

