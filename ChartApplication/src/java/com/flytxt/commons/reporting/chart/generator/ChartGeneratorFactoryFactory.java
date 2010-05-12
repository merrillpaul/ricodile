package com.flytxt.commons.reporting.chart.generator;

import com.flytxt.commons.reporting.constants.ChartConstants.ChartRendererType;
import java.util.logging.Level;
import java.util.logging.Logger;


public final class ChartGeneratorFactoryFactory {

   public static ChartGeneratorFactory
           getChartGeneratorFactory(ChartRendererType renderType){
       ChartGeneratorFactory generator = null;
        try {
            Class<? extends ChartGeneratorFactory> generatorClass = (Class<? extends ChartGeneratorFactory>) Class.forName(renderType.getGeneratorClassName());
            generator = generatorClass.newInstance();
        } catch (InstantiationException ex) {
            Logger.getLogger(ChartGeneratorFactoryFactory.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
            throw new RuntimeException(ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(ChartGeneratorFactoryFactory.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
            throw new RuntimeException(ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ChartGeneratorFactoryFactory.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
       return generator;

   }



 

 
   

}

