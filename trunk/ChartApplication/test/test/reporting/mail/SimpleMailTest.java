/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package test.reporting.mail;

import com.flytxt.commons.reporting.mail.MailException;
import com.flytxt.commons.reporting.mail.MailMessage;
import com.flytxt.commons.reporting.mail.MailSenderFactory;
import java.util.ArrayList;
import java.util.List;
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
public class SimpleMailTest {

    public SimpleMailTest() {
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
    @Test
    public void sendMail() {

        MailMessage message =
                new MailMessage();
        message.setSubject("RICODILE REPORTS: Test reports");
        message.setText("Conversion Column report Generated ");
       // message.setSender("merril.george@gmail.com");
        List<String> receivers =  new ArrayList<String>();
        receivers.add("merrill.paul@flytxt.com");
        //receivers.add("vishnu.sankar@flytxt.com");
       // receivers.add("anoop.nair@flytxt.com");
       // receivers.add("merrill.george@gmail.com");
        message.addAttachment("C:/Merrill/circuit_housing.jpg");
        message.setRecipients(receivers);

        try {
            MailSenderFactory.getSender().sendMail(message);
        } catch (MailException ex) {
            Logger.getLogger(SimpleMailTest.class.getName()).log(Level.SEVERE, null, ex);
            fail(ex.getMessage());
        }

    }

}