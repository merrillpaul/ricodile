/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package test.reporting.chart.run;

import com.flytxt.commons.reporting.chart.ChartContext;
import com.flytxt.commons.reporting.chart.ChartContextCreatorFactory;
import com.flytxt.commons.reporting.chart.ChartEngine;
import com.flytxt.commons.reporting.chart.ChartEngineFactory;
import com.flytxt.commons.reporting.chart.ChartException;
import com.flytxt.commons.reporting.chart.ChartOutput;
import com.flytxt.commons.reporting.chart.entity.ChartConfig;
import com.flytxt.commons.reporting.chart.provider.ChartConfigException;
import com.flytxt.commons.reporting.chart.provider.service.ChartConfigService;
import com.flytxt.commons.reporting.constants.ChartConstants.ChartRendererType;
import com.flytxt.commons.reporting.constants.ChartConstants.ChartType;
import com.flytxt.commons.reporting.context.ReportUser;
import com.flytxt.commons.reporting.factory.ServiceFactory;
import com.flytxt.commons.reporting.parameter.ParameterConstants.Type;
import com.flytxt.commons.reporting.parameter.ParameterConstants.ValueClassType;
import com.flytxt.commons.reporting.parameter.ParameterProviderException;
import com.flytxt.commons.reporting.parameter.business.ParameterMasterService;
import com.flytxt.commons.reporting.parameter.objects.Parameter;
import com.flytxt.commons.reporting.parameter.vo.InitParamVO;
import com.flytxt.commons.util.RandomStringGenerator;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author merrill.paul
 */
public class LineSeriesChartRunTest {


    private static Long firstChartId;
    private static Long secondChartId;
    private static Long _3ChartId;
    private static Long _4ChartId;
    private static Long _5ChartId;
  
    public LineSeriesChartRunTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {

        
        ChartConfig cfg =  new ChartConfig();
        cfg.setChartQuery("SELECT   to_char(the_date,'dd-Mon-yyyy hh24:mi:ss') , 'Males' \"seriesName1\", 'Females' \"seriesName2\", 'Eunuchs' \"seriesName3\", male_value \"seriesValue1\", female_value \"seriesValue2\" , (male_value+female_value) \"seriesValue3\" from bar_live_demo where to_date(to_char(the_date,'dd-Mon-yyyy'),'dd-Mon-yyyy')= to_date('26-May-2010','dd-Mon-yyyy')");
        cfg.setChartTypeAsEnum(ChartType.MULTI_SERIES_LINE);
        cfg.setDescription("MS-LINE CHART1");
        cfg.setHeight(400);
        cfg.setWidth(500);
        cfg.setXAxisLabel("Date");
        cfg.setYAxisLabel("Population");
        cfg.setName("MS COLUMN CHART1");
        cfg.setShowLegend(true);
        cfg.setShowTitle(true);

        ChartConfigService service = null;
        try {
            service = ServiceFactory.getChartConfigService();
        } catch (Exception ex) {
            Logger.getLogger(LineSeriesChartRunTest.class.getName()).log(Level.SEVERE, null, ex);
        }

        assertNotNull(service);
        try {
            cfg = service.saveChartConfig(cfg);
        } catch (ChartConfigException ex) {
            Logger.getLogger(LineSeriesChartRunTest.class.getName()).log(Level.SEVERE, null, ex);
            fail(ex.getMessage());
        }

        assertNotNull(cfg.getChartId());
        assertNotSame(cfg.getChartId(),0);
        firstChartId = cfg.getChartId();



        /////////////// SECOND SIMPLE CHART
        cfg =  new ChartConfig();
        cfg.setChartQuery("select to_char(the_date,'dd-Mon-yyyy hh24:mi:ss'), male_value from bar_live_demo where to_date(to_char(the_date,'dd-Mon-yyyy'),'dd-Mon-yyyy')= to_date('26-May-2010','dd-Mon-yyyy')");
        cfg.setChartTypeAsEnum(ChartType.MULTI_SERIES_LINE);
        cfg.setDescription("LINE AREA SIMPLE CHART1");
        cfg.setHeight(400);
        cfg.setWidth(500);
        cfg.setXAxisLabel("Date");
        cfg.setYAxisLabel("Population");
        cfg.setName("AREA SIMPLE CHART1");
        cfg.setShowLegend(true);
        cfg.setShowTitle(true);


        try {
            cfg = service.saveChartConfig(cfg);
        } catch (ChartConfigException ex) {
            Logger.getLogger(LineSeriesChartRunTest.class.getName()).log(Level.SEVERE, null, ex);
            fail(ex.getMessage());
        }

        assertNotNull(cfg.getChartId());
        assertNotSame(cfg.getChartId(),0);
       secondChartId = cfg.getChartId();



       /////////////// SECOND SIMPLE CHART
        cfg =  new ChartConfig();
        cfg.setChartQuery("select to_char(the_date,'dd-Mon-yyyy hh24:mi:ss') , male_value, 'Males'  from bar_live_demo where to_date(to_char(the_date,'dd-Mon-yyyy'),'dd-Mon-yyyy')= to_date('26-May-2010','dd-Mon-yyyy') union select to_char(the_date,'dd-Mon-yyyy hh24:mi:ss') , female_value, 'Females'  from bar_live_demo where to_date(to_char(the_date,'dd-Mon-yyyy'),'dd-Mon-yyyy')= to_date('26-May-2010','dd-Mon-yyyy') union select to_char(the_date,'dd-Mon-yyyy hh24:mi:ss') , female_value+male_value, 'Hijras'  from bar_live_demo where to_date(to_char(the_date,'dd-Mon-yyyy'),'dd-Mon-yyyy')= to_date('26-May-2010','dd-Mon-yyyy') ");
        cfg.setChartTypeAsEnum(ChartType.MULTI_SERIES_LINE);
        cfg.setDescription("MS LINE TUPLE CHART");
        cfg.setHeight(400);
        cfg.setWidth(500);
        cfg.setXAxisLabel("Date");
        cfg.setYAxisLabel("Population");
        cfg.setName("MS LINE BAR TUPLE CHART");
        cfg.setShowLegend(true);
        cfg.setShowTitle(true);


        try {
            cfg = service.saveChartConfig(cfg);
        } catch (ChartConfigException ex) {
            Logger.getLogger(LineSeriesChartRunTest.class.getName()).log(Level.SEVERE, null, ex);
            fail(ex.getMessage());
        }

        assertNotNull(cfg.getChartId());
        assertNotSame(cfg.getChartId(),0);
       _3ChartId = cfg.getChartId();


              /////////////// SECOND DRILL DOWN CHART
 cfg.setChartQuery("SELECT   to_char(the_date,'dd-Mon-yyyy hh24:mi:ss') ,'DRILLDOWN' \"DRILLDOWN\", 'Males' \"seriesName1\", 'Females' \"seriesName2\", 'Eunuchs' \"seriesName3\", male_value \"seriesValue1\", female_value \"seriesValue2\" , (male_value+female_value) \"seriesValue3\" from bar_live_demo where to_date(to_char(the_date,'dd-Mon-yyyy'),'dd-Mon-yyyy')= to_date('26-May-2010','dd-Mon-yyyy')");
        cfg.setChartTypeAsEnum(ChartType.MULTI_SERIES_LINE);
        cfg.setDescription("LINE DRILL DOWN COLUMN CHART1");
        cfg.setHeight(400);
        cfg.setWidth(500);
        cfg.setXAxisLabel("Date");
        cfg.setYAxisLabel("Population");
        cfg.setName("MS LINE DRILL DOWN COLUMN CHART1");
        cfg.setShowLegend(true);
        cfg.setShowTitle(true);

        try {
            service = ServiceFactory.getChartConfigService();
        } catch (Exception ex) {
            Logger.getLogger(LineSeriesChartRunTest.class.getName()).log(Level.SEVERE, null, ex);
        }

        assertNotNull(service);
        try {
            cfg = service.saveChartConfig(cfg);
        } catch (ChartConfigException ex) {
            Logger.getLogger(LineSeriesChartRunTest.class.getName()).log(Level.SEVERE, null, ex);
            fail(ex.getMessage());
        }

        assertNotNull(cfg.getChartId());
        assertNotSame(cfg.getChartId(),0);
        _4ChartId = cfg.getChartId();
          /////////////// SINGLE SERIES  DOWN CHART
 cfg.setChartQuery("SELECT   to_char(the_date,'dd-Mon-yyyy hh24:mi:ss') ,male_value \"seriesValue\",'DRILLDOWN' \"DRILLDOWN\", 'Males' \"seriesName\" from bar_live_demo where to_date(to_char(the_date,'dd-Mon-yyyy'),'dd-Mon-yyyy')= to_date('26-May-2010','dd-Mon-yyyy')");
        cfg.setChartTypeAsEnum(ChartType.MULTI_SERIES_LINE);
        cfg.setDescription("LINE DRILL DOWN  SINGLE SERIES COLUMN CHART1");
        cfg.setHeight(400);
        cfg.setWidth(500);
        cfg.setXAxisLabel("Date");
        cfg.setYAxisLabel("Population");
        cfg.setName("MS LINE DOWN  SINGLE SERIES COLUMN CHART1");
        cfg.setShowLegend(true);
        cfg.setShowTitle(true);

        try {
            service = ServiceFactory.getChartConfigService();
        } catch (Exception ex) {
            Logger.getLogger(LineSeriesChartRunTest.class.getName()).log(Level.SEVERE, null, ex);
        }

        assertNotNull(service);
        try {
            cfg = service.saveChartConfig(cfg);
        } catch (ChartConfigException ex) {
            Logger.getLogger(LineSeriesChartRunTest.class.getName()).log(Level.SEVERE, null, ex);
            fail(ex.getMessage());
        }

        assertNotNull(cfg.getChartId());
        assertNotSame(cfg.getChartId(),0);
        _5ChartId = cfg.getChartId();

       
    }

    @AfterClass
    public static void tearDownClass() throws Exception {

       ChartConfigService service = null;
        try {
            service = ServiceFactory.getChartConfigService();
        } catch (Exception ex) {
            Logger.getLogger(LineSeriesChartRunTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
          //  service.deleteChartConfig(firstChartId);
           //  service.deleteChartConfig(secondChartId);
             service.deleteChartConfig(_3ChartId);

        } catch (ChartConfigException ex) {
            Logger.getLogger(LineSeriesChartRunTest.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Before
    public void setUp() {

       
    }

    @After
    public void tearDown() {
    
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void testMultiSeriesColumnChart() {

         ChartConfigService service = null;
        try {
            service = ServiceFactory.getChartConfigService();
        } catch (Exception ex) {
            Logger.getLogger(LineSeriesChartRunTest.class.getName()).log(Level.SEVERE, null, ex);
        }

        ChartConfig cfg = service.getChartConfig(firstChartId);

        ChartContext ctx =   ChartContextCreatorFactory .getCreator(ChartRendererType.FUSION).createChartContext(cfg, new ReportUser(1, 1, "Merrill"));
         ChartEngine engine =   ChartEngineFactory.getEngine(ctx);
        try {
            ChartOutput output = engine.fillUp(ctx);
            System.out.println(new String(output.getContent()));
        } catch (ChartException ex) {
            Logger.getLogger(LineSeriesChartRunTest.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
            fail(ex.getMessage());
        }


    }



    @Test
    public void testSimpleBarChart() {

         ChartConfigService service = null;
        try {
            service = ServiceFactory.getChartConfigService();
        } catch (Exception ex) {
            Logger.getLogger(LineSeriesChartRunTest.class.getName()).log(Level.SEVERE, null, ex);
        }

        ChartConfig cfg = service.getChartConfig(secondChartId);

        ChartContext ctx =   ChartContextCreatorFactory .getCreator(ChartRendererType.FUSION).createChartContext(cfg, new ReportUser(1, 1, "Merrill"));
         ChartEngine engine =   ChartEngineFactory.getEngine(ctx);
        try {
            ChartOutput output = engine.fillUp(ctx);
            System.out.println(new String(output.getContent()));
        } catch (ChartException ex) {
            Logger.getLogger(LineSeriesChartRunTest.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
            fail(ex.getMessage());
        }


    }



    @Test
    public void testTupleBarChart() {

         ChartConfigService service = null;
        try {
            service = ServiceFactory.getChartConfigService();
        } catch (Exception ex) {
            Logger.getLogger(LineSeriesChartRunTest.class.getName()).log(Level.SEVERE, null, ex);
        }

        ChartConfig cfg = service.getChartConfig(_3ChartId);

        ChartContext ctx =   ChartContextCreatorFactory .getCreator(ChartRendererType.FUSION).createChartContext(cfg, new ReportUser(1, 1, "Merrill"));
         ChartEngine engine =   ChartEngineFactory.getEngine(ctx);
        try {
            ChartOutput output = engine.fillUp(ctx);
            System.out.println(new String(output.getContent()));
        } catch (ChartException ex) {
            Logger.getLogger(LineSeriesChartRunTest.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
            fail(ex.getMessage());
        }


    }
  @Test
    public void testDrillBarChart() {

         ChartConfigService service = null;
        try {
            service = ServiceFactory.getChartConfigService();
        } catch (Exception ex) {
            Logger.getLogger(LineSeriesChartRunTest.class.getName()).log(Level.SEVERE, null, ex);
        }

        ChartConfig cfg = service.getChartConfig(_4ChartId);

        ChartContext ctx =   ChartContextCreatorFactory .getCreator(ChartRendererType.FUSION).createChartContext(cfg, new ReportUser(1, 1, "Merrill"));
         ChartEngine engine =   ChartEngineFactory.getEngine(ctx);
        try {
            ChartOutput output = engine.fillUp(ctx);
            System.out.println(new String(output.getContent()));
        } catch (ChartException ex) {
            Logger.getLogger(LineSeriesChartRunTest.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
            fail(ex.getMessage());
        }


    }
  @Test
    public void testDrillSingleSeriesBarChart() {

         ChartConfigService service = null;
        try {
            service = ServiceFactory.getChartConfigService();
        } catch (Exception ex) {
            Logger.getLogger(LineSeriesChartRunTest.class.getName()).log(Level.SEVERE, null, ex);
        }

        ChartConfig cfg = service.getChartConfig(_5ChartId);

        ChartContext ctx =   ChartContextCreatorFactory .getCreator(ChartRendererType.FUSION).createChartContext(cfg, new ReportUser(1, 1, "Merrill"));
         ChartEngine engine =   ChartEngineFactory.getEngine(ctx);
        try {
            ChartOutput output = engine.fillUp(ctx);
            System.out.println(new String(output.getContent()));
        } catch (ChartException ex) {
            Logger.getLogger(LineSeriesChartRunTest.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
            fail(ex.getMessage());
        }


    }
}