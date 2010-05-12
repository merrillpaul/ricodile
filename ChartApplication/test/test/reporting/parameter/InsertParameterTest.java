/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package test.reporting.parameter;


import com.flytxt.commons.facade.BusinessServiceFactory;
import com.flytxt.commons.reporting.parameter.ParameterConstants.Type;
import com.flytxt.commons.reporting.parameter.ParameterConstants.ValueClassType;
import com.flytxt.commons.reporting.parameter.ParameterProviderException;
import com.flytxt.commons.reporting.parameter.business.ParameterMasterService;
import com.flytxt.commons.reporting.parameter.objects.Parameter;
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
public class InsertParameterTest {

    public InsertParameterTest() {
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
    public void createSimpleStringParameter(){
        Parameter p = new Parameter();
        p.setValueClassType(ValueClassType.STRING);
        p.setType(Type.TEXT);
        p.setParameterName("TEST_STRING");
        p.setDefaultValue("Me");
        p.setMultiselect(true);
        p.setDescription("This is a simple string value parameter");
        p.setData("Test");
        ParameterMasterService service = null;
        try {
            service = BusinessServiceFactory.getBusinessService(ParameterMasterService.class);
        } catch (Exception ex) {
            Logger.getLogger(InsertParameterTest.class.getName()).log(Level.SEVERE, null, ex);
        }

        assertNotNull(service);
        try {
            p = service.insertParameter(p);
        } catch (ParameterProviderException ex) {
            Logger.getLogger(InsertParameterTest.class.getName()).log(Level.SEVERE, null, ex);

        }
        assertNotNull(p);
        assertNotNull(p.getId());
        assertNotSame(p.getId(), 0);
        

    }

    @Test
    public void createQueryParameter(){
        Parameter p = new Parameter();
        p.setValueClassType(ValueClassType.INTEGER);
        p.setType(Type.QUERY);
        p.setParameterName("STATUS_IDS");

        p.setDescription("Status Id");
        p.setData("select status_id,status_name from static_status where 1 = $P{FLY_REPORT_USER}");
        ParameterMasterService service = null;
        try {
            service = BusinessServiceFactory.getBusinessService(ParameterMasterService.class);
        } catch (Exception ex) {
            Logger.getLogger(InsertParameterTest.class.getName()).log(Level.SEVERE, null, ex);
        }

        assertNotNull(service);
        try {
            p = service.insertParameter(p);
        } catch (ParameterProviderException ex) {
            Logger.getLogger(InsertParameterTest.class.getName()).log(Level.SEVERE, null, ex);

        }
        assertNotNull(p);
        assertNotNull(p.getId());
        assertNotSame(p.getId(), 0);
    }

}