/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package test.reporting.mail;

import com.flytxt.commons.reporting.mail.ByteArrayDataSource;
import com.flytxt.commons.reporting.mail.MailConstants.RecipientType;
import com.flytxt.commons.reporting.mail.MailException;
import com.flytxt.commons.reporting.mail.MailMessage;
import com.flytxt.commons.reporting.mail.MailSenderFactory;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
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
        message.addAttachment("C:/Merrill/circuit_housing.jpg");
        message.addRecepient("merrill.paul@flytxt.com", RecipientType.TO);
        message.addRecepient("merrillmatrix@yahoo.com", RecipientType.CC);
        message.addRecepient("merrill.george@gmail.com", RecipientType.BCC);

        message.setByteArrayDataSource(getSampleByteArray());


        try {
            MailSenderFactory.getSender().sendMail(message);
        } catch (MailException ex) {
            Logger.getLogger(SimpleMailTest.class.getName()).log(Level.SEVERE, null, ex);
            fail(ex.getMessage());
        }

    }

     @Test
    public void sendMail1() {

        MailMessage message =
                new MailMessage();
        message.setSubject("RICODILE REPORTS: Test reports11");
        message.setText("Conversion11111 Column111111 report Generated ");

        message.addRecepient("merrill.paul@flytxt.com", RecipientType.TO);


       


        try {
            MailSenderFactory.getSender().sendMail(message);
        } catch (MailException ex) {
            Logger.getLogger(SimpleMailTest.class.getName()).log(Level.SEVERE, null, ex);
            fail(ex.getMessage());
        }

    }

    private ByteArrayDataSource getSampleByteArray(){
        
        File fPath =  new File("C:/Merrill/MerrillGeorgePaul20092010Employeeinsurancedatasheet.xls");
        if (fPath.exists()) {
            long size = fPath.length();
            if(size==0)
                return null;

            ByteArrayOutputStream bout = new ByteArrayOutputStream();
            try{
            FileInputStream fis = new FileInputStream(fPath);
            while (size > 0) {
					byte[] data;
					int thisRead = 0;
					if (size > 2048) {
						thisRead = 2048;
						data = new byte[2048];
					} else {
						thisRead = (int) size;
						data = new byte[thisRead];
					}
					fis.read(data);
					size -= thisRead;
					bout.write(data);
					//bout.flush();


				}
            fis.close();
            ByteArrayDataSource bds =  new ByteArrayDataSource(bout.toByteArray(), "application/vnd.ms-excel");
            bds.setName("Merrill's Insurance.xls");
            return bds;
            }catch(Exception e){
                e.printStackTrace();
                return null;
            }


        }
        return null;
    }

}