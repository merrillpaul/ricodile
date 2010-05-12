/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package test.reporting.chart.persistence;

import com.flytxt.commons.facade.BusinessServiceFactory;
import com.flytxt.commons.reporting.ReportServiceResolverException;
import com.flytxt.commons.reporting.chart.entity.ChartConfig;
import com.flytxt.commons.reporting.chart.entity.ChartPromptParameters;
import com.flytxt.commons.reporting.chart.provider.ChartConfigException;
import com.flytxt.commons.reporting.chart.provider.service.ChartConfigService;
import com.flytxt.commons.reporting.constants.ChartConstants.ChartType;
import com.flytxt.commons.reporting.factory.ServiceFactory;
import com.flytxt.commons.reporting.parameter.ParameterConstants.Type;
import com.flytxt.commons.reporting.parameter.ParameterConstants.ValueClassType;
import com.flytxt.commons.reporting.parameter.ParameterProviderException;
import com.flytxt.commons.reporting.parameter.business.ParameterMasterService;
import com.flytxt.commons.reporting.parameter.objects.Parameter;
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
public class CreateQueryChartWithParameter {


    protected Collection<Long> paramIds = new ArrayList<Long>();
    private Long chartId;

    public CreateQueryChartWithParameter() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
        Parameter p = new Parameter();
        p.setValueClassType(ValueClassType.STRING);
        p.setType(Type.TEXT);
        p.setParameterName("TEST_STRING"+new RandomStringGenerator().getRandomString(2));
        p.setDefaultValue("Me");
        p.setMultiselect(true);
        p.setDescription("This is a simple string value parameter");
        p.setData("Test");
        ParameterMasterService service = null;
        try {
            service = ServiceFactory.getParameterMasterService();
        } catch (ReportServiceResolverException ex) {
            Logger.getLogger(CreateQueryChartWithParameter.class.getName()).log(Level.SEVERE, null, ex);
            fail(ex.getMessage());
            
        }

        try {
            p = service.insertParameter(p);
          
        } catch (ParameterProviderException ex) {
            Logger.getLogger(CreateQueryChartWithParameter.class.getName()).log(Level.SEVERE, null, ex);
            fail(ex.getMessage());

        }
        assertNotNull(p);
        assertNotNull(p.getId());
        assertNotSame(p.getId(), 0);


        paramIds.add(p.getId());

        p = new Parameter();
        p.setValueClassType(ValueClassType.INTEGER);
        p.setType(Type.QUERY);
        p.setParameterName("STATUS_IDS"+new RandomStringGenerator().getRandomString(2));
        p.setData("select status_id, status_name from static_status");
        p.setMultiselect(true);
        p.setDescription("This is a list of status ids parameter");
       
         try {
            p = service.insertParameter(p);

        } catch (ParameterProviderException ex) {
            Logger.getLogger(CreateQueryChartWithParameter.class.getName()).log(Level.SEVERE, null, ex);
            fail(ex.getMessage());

        }
        assertNotNull(p);
        assertNotNull(p.getId());
        assertNotSame(p.getId(), 0);


        paramIds.add(p.getId());
        

    }

    @After
    public void tearDown() {




        ChartConfigService service = null;
        try {
            service = ServiceFactory.getChartConfigService();
        } catch (Exception ex) {
            Logger.getLogger(CreateQueryChartWithParameter.class.getName()).log(Level.SEVERE, null, ex);
            fail(ex.getMessage());
        }
        try {
            service.deleteChartConfig(chartId);
        } catch (ChartConfigException ex) {
            Logger.getLogger(CreateQueryChartWithParameter.class.getName()).log(Level.SEVERE, null, ex);
            fail(ex.getMessage());
        }

       

    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}

     @Test
    public void saveQueryChartConfig(){



        assertFalse(paramIds.isEmpty());

        ChartConfig cfg =  new ChartConfig();
        cfg.setChartQuery("select status_id \"value\", status_name" +
                " \"label\" from static_status " +
                "where status_id >31 and status_id <42");
        cfg.setChartTypeAsEnum(ChartType.COLUMN);
        cfg.setDescription("Status Id COlumn Stupid Chart");
        cfg.setHeight(400);
        cfg.setWidth(500);
        cfg.setName("Stupid COLUMN"+new RandomStringGenerator().getRandomString(3));
        cfg.setShowLegend(true);
        cfg.setShowTitle(true);

        ChartConfigService service = null;
        try {
            service = ServiceFactory.getChartConfigService();
        } catch (Exception ex) {
            Logger.getLogger(CreateQueryChartWithParameter.class.getName()).log(Level.SEVERE, null, ex);
            fail(ex.getMessage());
        }


        assertNotNull(service);


        ParameterMasterService paramService = null;
        try {
            paramService = ServiceFactory.getParameterMasterService();
        } catch (ReportServiceResolverException ex) {
            Logger.getLogger(CreateQueryChartWithParameter.class.getName()).log(Level.SEVERE, null, ex);
            fail(ex.getMessage());
        }

        Collection<ChartPromptParameters> list = new ArrayList<ChartPromptParameters>();
        short order = 0;
        for(Long id: paramIds){
            try {
                Parameter pp = paramService.getParameter(id);
                ChartPromptParameters p = new ChartPromptParameters();
               //p.setChartConfig(cfg);
                p.setParameter(pp);
                p.setOrder(order);
                order++;
                list.add(p);
            } catch (ParameterProviderException ex) {
                Logger.getLogger(CreateQueryChartWithParameter.class.getName()).log(Level.SEVERE, null, ex);
                fail(ex.getMessage());
            }


        }
        cfg.setChartPromptParameters(list);
        try {
            cfg = service.saveChartConfig(cfg);
        } catch (ChartConfigException ex) {
            Logger.getLogger(CreateQueryChartWithParameter.class.getName()).log(Level.SEVERE, null, ex);
            fail(ex.getMessage());
        }

        assertNotNull(cfg);
        assertNotNull(cfg.getChartId());
        assertNotSame(cfg.getChartId(),0);

        cfg = service.getChartConfig(cfg.getChartId());
        Collection<ChartPromptParameters> promptParams =
                cfg.getChartPromptParameters();



        assertNotNull(promptParams);
        assertNotSame(promptParams.size(), 0);
        assertEquals(promptParams.size(), this.paramIds.size());

        this.chartId = cfg.getChartId();


        


    }

}