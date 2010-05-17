/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package test.reporting.parameter;

import com.flytxt.commons.facade.BusinessService;
import com.flytxt.commons.facade.BusinessServiceFactory;
import com.flytxt.commons.reporting.factory.ServiceFactory;
import com.flytxt.commons.reporting.parameter.ParameterConstants.Type;
import com.flytxt.commons.reporting.parameter.ParameterConstants.ValueClassType;
import com.flytxt.commons.reporting.parameter.ParameterProviderException;
import com.flytxt.commons.reporting.parameter.business.ParameterMasterService;
import com.flytxt.commons.reporting.parameter.objects.Parameter;
import com.flytxt.commons.reporting.parameter.objects.ParameterValue;
import com.flytxt.commons.reporting.parameter.provider.ParameterParserProvider;
import com.flytxt.commons.reporting.parameter.vo.InitParamVO;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
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
public class ParameterParserTest {
    private static ParameterMasterService service;
    private static ParameterParserProvider paramParser;
    private static Collection<Long> parameterIds;

    public ParameterParserTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {

        try {
            service = BusinessServiceFactory.getBusinessService(ParameterMasterService.class);
        } catch (Exception ex) {
            Logger.getLogger(ParameterMasterService.class.getName()).log(Level.SEVERE, null, ex);
            fail(ex.getMessage());
        }

           try {
           paramParser =  ServiceFactory.getParameterParser();
        } catch (Exception ex) {
            Logger.getLogger(ParameterMasterService.class.getName()).log(Level.SEVERE, null, ex);
            fail(ex.getMessage());
        }


         parameterIds =  new ArrayList<Long>();

        Parameter p = new Parameter();
        p.setValueClassType(ValueClassType.INTEGER);
        p.setType(Type.QUERY);
        p.setParameterName("SELECTED_PARTNER_ID");
        p.setData("select partner_id, partner_name from partner where partner_id = $P{TARGET_PARTNER_ID}");

        try {
            p = service.insertParameter(p);
        } catch (ParameterProviderException ex) {
            Logger.getLogger(InsertParameterTest.class.getName()).log(Level.SEVERE, null, ex);


        }
        assertNotNull(p);
        assertNotNull(p.getId());
        assertNotSame(p.getId(), 0);

        parameterIds.add(p.getId());

        p = new Parameter();
        p.setValueClassType(ValueClassType.INTEGER);
        p.setType(Type.TEXT);
        p.setParameterName("ACTIVE_STATE");
        p.setData("18");

        try {
            p = service.insertParameter(p);
        } catch (ParameterProviderException ex) {
            Logger.getLogger(InsertParameterTest.class.getName()).log(Level.SEVERE, null, ex);


        }
        assertNotNull(p);
        assertNotNull(p.getId());
        assertNotSame(p.getId(), 0);
        parameterIds.add(p.getId());

        p = new Parameter();
        p.setValueClassType(ValueClassType.INTEGER);
        p.setType(Type.QUERY);
        p.setParameterName("PARTNER_USER_IDS");
        p.setData("select web_user_id, email_address from web_user where partner_id = $P{SELECTED_PARTNER_ID} and status_id=$P{ACTIVE_STATE}");

        try {
            p = service.insertParameter(p);
        } catch (ParameterProviderException ex) {
            Logger.getLogger(InsertParameterTest.class.getName()).log(Level.SEVERE, null, ex);


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
                Logger.getLogger(ParameterParserTest.class.getName()).log(Level.SEVERE, null, ex);
            }
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
     public void getParameterValues() {
     
         InitParamVO  TARGET_PARTNER_ID =  new InitParamVO();
         TARGET_PARTNER_ID.setName("TARGET_PARTNER_ID");
         TARGET_PARTNER_ID.setValue("10040");
         TARGET_PARTNER_ID.setType(ValueClassType.INTEGER.getKey());

         Parameter initialParameter=
         ServiceFactory.getInitialParameterProvider().prepareParameter(TARGET_PARTNER_ID);

         Map<String,Object> initMap = null;
        try {
            initMap = paramParser.toMapFromFilledParameter(initialParameter);
        } catch (ParameterProviderException ex) {
            Logger.getLogger(ParameterParserTest.class.getName()).log(Level.SEVERE, null, ex);
            fail(ex.getMessage());
        }

        Collection<ParameterValue> params = null;
        try {
            params = paramParser.getParameterValues("PARTNER_USER_IDS", initMap);
        } catch (ParameterProviderException ex) {
            Logger.getLogger(ParameterParserTest.class.getName()).log(Level.SEVERE, null, ex);
            fail(ex.getMessage());
        }

        assertNotNull(params);
         
         
     }



}