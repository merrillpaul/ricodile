/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package test.reporting.chart.persistence;

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
public class AddChartParameterstoASimpleChart {


    private Long chartId;

    public AddChartParameterstoASimpleChart() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }


    protected Collection<Long> paramIds = null;
    @Before
    public void setUp() {
        paramIds = new ArrayList<Long>();
        ChartConfig cfg =  new ChartConfig();
        cfg.setChartQuery("select status_id \"value\", status_name" +
                " \"label\" from static_status " +
                "where status_id >31 and status_id <42");
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
            Logger.getLogger(SimpleChartWithoutParameterSave.class.getName()).log(Level.SEVERE, null, ex);
        }

        assertNotNull(service);
        try {
            cfg = service.saveChartConfig(cfg);
            this.chartId = cfg.getChartId();
        } catch (ChartConfigException ex) {
            Logger.getLogger(SimpleChartWithoutParameterSave.class.getName()).log(Level.SEVERE, null, ex);
            fail(ex.getMessage());
        }

        assertNotNull(cfg.getChartId());
        assertNotSame(cfg.getChartId(),0);



        Parameter p = new Parameter();
        p.setValueClassType(ValueClassType.STRING);
        p.setType(Type.TEXT);
        p.setParameterName("TEST_STRING"+new RandomStringGenerator().getRandomString(2));
        p.setDefaultValue("Me");
        p.setMultiselect(true);
        p.setDescription("This is a simple string value parameter");
        p.setData("Test");
        ParameterMasterService paramservice = null;
        try {
            paramservice = ServiceFactory.getParameterMasterService();
        } catch (ReportServiceResolverException ex) {
            Logger.getLogger(CreateQueryChartWithParameter.class.getName()).log(Level.SEVERE, null, ex);
            fail(ex.getMessage());

        }

        try {
            p = paramservice.insertParameter(p);

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
            p = paramservice.insertParameter(p);

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
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}

    @Test
    public void addChartParametertoAChart(){

        ChartConfigService service = null;
        try {
            service = ServiceFactory.getChartConfigService();
        } catch (Exception ex) {
            Logger.getLogger(SimpleChartWithoutParameterSave.class.getName()).log(Level.SEVERE, null, ex);
        }

        ChartConfig cfg = service.getChartConfig(chartId);

        ParameterMasterService paramService = null;
        try {
            paramService = ServiceFactory.getParameterMasterService();
        } catch (ReportServiceResolverException ex) {
            Logger.getLogger(CreateQueryChartWithParameter.class.getName()).log(Level.SEVERE, null, ex);
            fail(ex.getMessage());
        }

        Collection<Parameter> list = new ArrayList<Parameter>();
        short order = 0;
        for(Long id: paramIds){
            try {
                Parameter pp = paramService.getParameter(id);
               
                list.add(pp);
            } catch (ParameterProviderException ex) {
                Logger.getLogger(CreateQueryChartWithParameter.class.getName()).log(Level.SEVERE, null, ex);
                fail(ex.getMessage());
            }


        }
      
        try {
           service.updateChartParameters(list, chartId);
        } catch (ChartConfigException ex) {
            Logger.getLogger(CreateQueryChartWithParameter.class.getName()).log(Level.SEVERE, null, ex);
            fail(ex.getMessage());
        }


    }


    @Test
    public void addANewParameterToAChart() {

        ChartConfigService service = null;
        try {
            service = ServiceFactory.getChartConfigService();
        } catch (Exception ex) {
            Logger.getLogger(SimpleChartWithoutParameterSave.class.getName()).log(Level.SEVERE, null, ex);
        }

        ChartConfig cfg = service.getChartConfig(chartId);
         Parameter p = new Parameter();
        p.setValueClassType(ValueClassType.STRING);
        p.setType(Type.TEXT);
        p.setParameterName("TEST_STRING"+new RandomStringGenerator().getRandomString(2));
        p.setDefaultValue("Me");
        p.setMultiselect(true);
        p.setDescription("This is a simple string value parameter");
        p.setData("Test");
        ParameterMasterService paramservice = null;
        try {
            paramservice = ServiceFactory.getParameterMasterService();
        } catch (ReportServiceResolverException ex) {
            Logger.getLogger(CreateQueryChartWithParameter.class.getName()).log(Level.SEVERE, null, ex);
            fail(ex.getMessage());

        }

        try {
            p = paramservice.insertParameter(p);

        } catch (ParameterProviderException ex) {
            Logger.getLogger(CreateQueryChartWithParameter.class.getName()).log(Level.SEVERE, null, ex);
            fail(ex.getMessage());

        }
        assertNotNull(p);
        assertNotNull(p.getId());
        assertNotSame(p.getId(), 0);
        try {
            p = paramservice.getParameter(p.getId());
        } catch (ParameterProviderException ex) {
            Logger.getLogger(AddChartParameterstoASimpleChart.class.getName()).log(Level.SEVERE, null, ex);
            fail(ex.getMessage());
        }
        try {
            service.addChartParameter(p, chartId);
        } catch (ChartConfigException ex) {
            Logger.getLogger(AddChartParameterstoASimpleChart.class.getName()).log(Level.SEVERE, null, ex);
             fail(ex.getMessage());
        }

    }

    

}