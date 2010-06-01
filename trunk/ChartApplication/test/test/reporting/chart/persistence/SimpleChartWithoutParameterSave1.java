/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package test.reporting.chart.persistence;


import com.flytxt.commons.reporting.chart.entity.ChartConfig;
import com.flytxt.commons.reporting.chart.provider.ChartConfigException;
import com.flytxt.commons.reporting.chart.provider.service.ChartConfigService;
import com.flytxt.commons.reporting.constants.ChartConstants.ChartType;
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
public class SimpleChartWithoutParameterSave1 {

    public SimpleChartWithoutParameterSave1() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
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
    // @Test
    // public void hello() {}

    @Test
    public void saveQueryChartConfig(){

        ChartConfig cfg =  new ChartConfig();
        cfg.setChartQuery("select to_char(the_date,'dd-Mon-yyyy hh24:mi:ss') \"label\", female_value" +
                " \"label\" , 'Female' from bar_live_demo union " +
                " select to_char(the_date,'dd-Mon-yyyy hh24:mi:ss') \"label\", male_value \"label\" , 'Male' from bar_live_demo" );
        cfg.setChartTypeAsEnum(ChartType.MULTI_SERIES_COLUMN);
        cfg.setDescription("Male/Female Population");
        cfg.setHeight(400);
        cfg.setWidth(500);
        cfg.setName("MALE FEMALE");
        cfg.setShowLegend(true);
        cfg.setShowTitle(true);

        ChartConfigService service = null;
        try {
            service = ServiceFactory.getChartConfigService();
        } catch (Exception ex) {
            Logger.getLogger(SimpleChartWithoutParameterSave1.class.getName()).log(Level.SEVERE, null, ex);
        }

        assertNotNull(service);
        try {
            cfg = service.saveChartConfig(cfg);
        } catch (ChartConfigException ex) {
            Logger.getLogger(SimpleChartWithoutParameterSave1.class.getName()).log(Level.SEVERE, null, ex);
            fail(ex.getMessage());
        }

        assertNotNull(cfg.getChartId());
        assertNotSame(cfg.getChartId(),0);


    }

   

}