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
import com.flytxt.commons.reporting.parameter.provider.ParameterParserProvider;
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
public class ConvColumnBarChart {
 private Long chartId;
    private static ParameterMasterService service;

     private static Collection<Long> parameterIds;
    private String chartName;
    public ConvColumnBarChart() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
         try {
            service = ServiceFactory.getParameterMasterService();
        } catch (Exception ex) {
            Logger.getLogger(ConvColumnBarChart.class.getName()).log(Level.SEVERE, null, ex);
            fail(ex.getMessage());
        }



        parameterIds =  new ArrayList<Long>();

        Parameter p = new Parameter();
        p.setValueClassType(ValueClassType.INTEGER);
        p.setType(Type.TEXT);
        p.setParameterName("EVENT_TYPE_ID_1");
        p.setData("1");

        try {
            p = service.insertParameter(p);
        } catch (ParameterProviderException ex) {
            Logger.getLogger(ConvColumnBarChart.class.getName()).log(Level.SEVERE, null, ex);


        }
        assertNotNull(p);
        assertNotNull(p.getId());
        assertNotSame(p.getId(), 0);
        parameterIds.add(p.getId());


        p = new Parameter();
        p.setValueClassType(ValueClassType.INTEGER);
        p.setType(Type.QUERY);
        p.setParameterName("EVENT_INSTANCE_ID_FOR_ARG");
        p.setData("select event_instance_id from track_event_instance where external_system_id=$P{INIT_EVENT_SYSTEM_ID}");

        try {
            p = service.insertParameter(p);
        } catch (ParameterProviderException ex) {
            Logger.getLogger(ConvColumnBarChart.class.getName()).log(Level.SEVERE, null, ex);


        }
        assertNotNull(p);
        assertNotNull(p.getId());
        assertNotSame(p.getId(), 0);



        parameterIds.add(p.getId());

    }

    @AfterClass
    public static void tearDownClass() throws Exception {

          for(Long id: parameterIds){
            try {
                service.deleteParameter(id);
            } catch (ParameterProviderException ex) {
                Logger.getLogger(ConvColumnBarChart.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Before
    public void setUp() {

         ChartConfig cfg =  new ChartConfig();
        cfg.setChartQuery("SELECT   to_char(b.interval_start , 'DD/Mon/YYYY hh24:mi:ss') \"label\",   nvl(a.event_count,0) \"value\",'t' \"test\", $P{SERIES_NAME_PREFIX}||' and '||$P{FLYTXT_REPORT_USER_NAME}||' Conv Now' \"SeriesName\"    FROM              (    select interval_start, sum (event_count) event_count     from track_partner_event_summary     where     partner_id  =$P{FLYTXT_REPORT_USER_PARTNER_ID}                and event_instance_id  in $P{EVENT_INSTANCE_ID_FOR_ARG}              and event_type_id=$P{EVENT_TYPE_ID_1} group by interval_start order by interval_start  ) a,         (    SELECT c.interval interval_start         FROM                            (                    select min(interval_start) min_is, max(interval_start) max_is                     FROM track_partner_event_summary                     where partner_id=$P{FLYTXT_REPORT_USER_PARTNER_ID}                           and event_instance_id in $P{EVENT_INSTANCE_ID_FOR_ARG}                                 and event_type_id  =$P{EVENT_TYPE_ID_1}               ) b,                           UTIL_CALENDAR_HOURS c                     WHERE                            c.interval between b.min_is AND b.max_is    ) b        WHERE a.interval_start(+) = b.interval_start     and nvl(a.event_count,0) >0    ORDER by b.interval_start");
        cfg.setChartTypeAsEnum(ChartType.BAR);
        cfg.setDescription("Status Id Pie Stupid Chart");
        cfg.setHeight(400);
        cfg.setWidth(500);
        cfg.setXAxisLabel("Date");
        cfg.setYAxisLabel("Conversions");
        cfg.setName("Conv Curve BAr"+new RandomStringGenerator().getRandomString(3));
        cfg.setShowLegend(true);
        cfg.setShowTitle(true);

        ChartConfigService service = null;
        try {
            service = ServiceFactory.getChartConfigService();
        } catch (Exception ex) {
            Logger.getLogger(ConvCurveColumnChart.class.getName()).log(Level.SEVERE, null, ex);
        }

        assertNotNull(service);
        try {
            cfg = service.saveChartConfig(cfg);
        } catch (ChartConfigException ex) {
            Logger.getLogger(ConvCurveColumnChart.class.getName()).log(Level.SEVERE, null, ex);
            fail(ex.getMessage());
        }

        assertNotNull(cfg.getChartId());
        assertNotSame(cfg.getChartId(),0);
        this.chartId = cfg.getChartId();
        this.chartName = cfg.getName();
    }

    @After
    public void tearDown() {

          ChartConfigService service = null;
        try {
            service = ServiceFactory.getChartConfigService();
        } catch (Exception ex) {
            Logger.getLogger(ConvCurveColumnChart.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            service.deleteChartConfig(chartId);
        } catch (ChartConfigException ex) {
            Logger.getLogger(ConvCurveColumnChart.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
     @Test
     public void testConvBarChart() {

           ChartConfigService service = null;
        try {
            service = ServiceFactory.getChartConfigService();
        } catch (Exception ex) {
            Logger.getLogger(ConvCurveColumnChart.class.getName()).log(Level.SEVERE, null, ex);
        }

        ChartConfig cfg = service.getChartConfig(chartName);

        ChartContext ctx =
                ChartContextCreatorFactory
                .getCreator(ChartRendererType.FUSION)
                .createChartContext(cfg, new ReportUser(1, 1, "Merrill"));

         InitParamVO  INIT_EVENT_SYSTEM_ID =  new InitParamVO();
         INIT_EVENT_SYSTEM_ID.setName("INIT_EVENT_SYSTEM_ID");
         INIT_EVENT_SYSTEM_ID.setValue("16522");
         INIT_EVENT_SYSTEM_ID.setType(ValueClassType.INTEGER.getKey());

         Parameter initParam =
         ServiceFactory.getInitialParameterProvider().prepareParameter(INIT_EVENT_SYSTEM_ID);

          Collection<Parameter> initialParameters =  new ArrayList<Parameter>();
           initialParameters.add(initParam);

           INIT_EVENT_SYSTEM_ID =  new InitParamVO();
         INIT_EVENT_SYSTEM_ID.setName("SERIES_NAME_PREFIX");
         INIT_EVENT_SYSTEM_ID.setValue("Vishnu");
         INIT_EVENT_SYSTEM_ID.setType(ValueClassType.STRING.getKey());

         initParam =
         ServiceFactory.getInitialParameterProvider().prepareParameter(INIT_EVENT_SYSTEM_ID);

           initialParameters.add(initParam);

        ctx.setInitialParameters(initialParameters);

        ChartEngine engine =
        ChartEngineFactory.getEngine(ctx);
        try {
            ChartOutput output = engine.fillUp(ctx);
            System.out.println(new String(output.getContent()));
        } catch (ChartException ex) {
            Logger.getLogger(ConvCurveColumnChart.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
            fail(ex.getMessage());
        }


     }

}