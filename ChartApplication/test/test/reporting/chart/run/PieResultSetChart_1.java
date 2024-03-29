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
import com.flytxt.commons.util.RandomStringGenerator;
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
public class PieResultSetChart_1 {
    private Long chartId;

    public PieResultSetChart_1() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {

         ChartConfig cfg =  new ChartConfig();
        cfg.setChartQuery("select partner_id \"VALUE\", partner_name" +
                " \"LABEL\" from partner " +
                "where partner_id IN $P{FLYTXT_REPORT_USER_PARTNER_ID} and 1=$P{FLYTXT_REPORT_USER_ID}");
        cfg.setChartTypeAsEnum(ChartType.PIE);
        cfg.setDescription("Status Id Pie Stupid Chart");
        cfg.setHeight(400);
        cfg.setWidth(500);
        cfg.setName("Stupid PIE"+new RandomStringGenerator().getRandomString(3));
        cfg.setShowLegend(true);
        cfg.setShowTitle(true);

        ChartConfigService service = null;
        try {
            service = ServiceFactory.getChartConfigService();
        } catch (Exception ex) {
            Logger.getLogger(PieResultSetChart.class.getName()).log(Level.SEVERE, null, ex);
        }

        assertNotNull(service);
        try {
            cfg = service.saveChartConfig(cfg);
        } catch (ChartConfigException ex) {
            Logger.getLogger(PieResultSetChart.class.getName()).log(Level.SEVERE, null, ex);
            fail(ex.getMessage());
        }

        assertNotNull(cfg.getChartId());
        assertNotSame(cfg.getChartId(),0);
        this.chartId = cfg.getChartId();
    }

    @After
    public void tearDown() {
         ChartConfigService service = null;
        try {
            service = ServiceFactory.getChartConfigService();
        } catch (Exception ex) {
            Logger.getLogger(PieResultSetChart.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            service.deleteChartConfig(chartId);
        } catch (ChartConfigException ex) {
            Logger.getLogger(PieResultSetChart_1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
     @Test
     public void runChart() {

       ChartConfigService service = null;
        try {
            service = ServiceFactory.getChartConfigService();
        } catch (Exception ex) {
            Logger.getLogger(PieResultSetChart.class.getName()).log(Level.SEVERE, null, ex);
        }

        ChartConfig cfg = service.getChartConfig(chartId);

        ChartContext ctx =
                ChartContextCreatorFactory
                .getCreator(ChartRendererType.FUSION)
                .createChartContext(cfg, new ReportUser(1, 1, "Merrill"));
        ChartEngine engine =
        ChartEngineFactory.getEngine(ctx);
        try {
            ChartOutput output = engine.fillUp(ctx);
            System.out.println(new String(output.getContent()));
        } catch (ChartException ex) {
            Logger.getLogger(PieResultSetChart.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
            fail(ex.getMessage());
        }

     }

}