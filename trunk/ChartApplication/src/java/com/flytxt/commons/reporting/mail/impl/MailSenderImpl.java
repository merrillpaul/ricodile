/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/*
 * @(#) $Id:
 * Copyright Flytxt Technologies Pvt Limited. All Rights Reserved.
 *
 * This Software is the proprietary information of Flytxt technologies Pvt Limited.
 * Use is subject to License terms.
 *
 */
package com.flytxt.commons.reporting.mail.impl;

import com.flytxt.commons.reporting.constants.ReportConstants.ContentTypes;
import com.flytxt.commons.reporting.mail.MailException;
import com.flytxt.commons.reporting.mail.MailMessage;
import com.flytxt.commons.reporting.mail.MailSender;
import com.flytxt.commons.reporting.mail.MailSessionProvider;
import java.util.Date;
import java.util.StringTokenizer;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 *
 * @author Merrill George (merrill.george@gmail.com)
 */
public class MailSenderImpl implements MailSender {

    public void sendMail(MailMessage mail) throws MailException {

        try{
        Session mailSession = MailSessionProvider.getReportSession();
        Multipart multipart = new MimeMultipart();

			// add text part
			 if (mail.getText() != null
					&& (mail.getByteArrayDataSource() == null || !mail.getByteArrayDataSource().getContentType().equals(ContentTypes.CONTENT_TYPE_PLAIN.getKey())))
			 {
				MimeBodyPart mbpText = new MimeBodyPart();
				mbpText.setText(mail.getText());
				multipart.addBodyPart(mbpText);
			}

			// add file attachments

			for (String fileAttachment : mail.getAttachments())
			{

				FileDataSource source = new FileDataSource(fileAttachment);

				MimeBodyPart mbpAttachment = new MimeBodyPart();
				mbpAttachment.setDataHandler(new DataHandler(source));
				mbpAttachment.setFileName(source.getName());
                                

				multipart.addBodyPart(mbpAttachment);
			}

			// add byteArrayAttachment
			if (mail.getByteArrayDataSource() != null)
			{
				String contentType = mail.getByteArrayDataSource().getContentType();
				if (contentType != null && (contentType.equals(ContentTypes.CONTENT_TYPE_HTML.getKey()) || contentType.equals(ContentTypes.CONTENT_TYPE_PLAIN.getKey())))
				{
					Multipart htmlMP = new MimeMultipart("related");
					MimeBodyPart htmlBP = new MimeBodyPart();
					htmlBP.setDataHandler(new DataHandler(mail.getByteArrayDataSource()));
					htmlMP.addBodyPart(htmlBP);

					// Add images

					for (DataSource imageDS:mail.getHtmlImageDataSources())
					{


						MimeBodyPart imageBodyPart = new MimeBodyPart();
						imageBodyPart.setFileName(imageDS.getName());
						imageBodyPart.setText(imageDS.getName());
						imageBodyPart.setDataHandler(new DataHandler(imageDS));
						imageBodyPart.setHeader("Content-ID", "<" + imageDS.getName() + ">");
						imageBodyPart.setDisposition(javax.mail.Part.INLINE);

						htmlMP.addBodyPart(imageBodyPart);
					}

					BodyPart completeHtmlBP = new MimeBodyPart();
					completeHtmlBP.setContent(htmlMP);
					multipart.addBodyPart(completeHtmlBP);
				}
				else
				{
					MimeBodyPart mbpAttachment = new MimeBodyPart();
					mbpAttachment.setDataHandler(new DataHandler(mail.getByteArrayDataSource()));
					mbpAttachment.setFileName(mail.getByteArrayDataSource().getName());

					multipart.addBodyPart(mbpAttachment);
				}
			}

			// create message
			Message msg = new MimeMessage(mailSession);
			msg.setFrom(new InternetAddress(mail.getSender()));
			msg.setSubject(mail.getSubject());
			msg.setContent(multipart);
			msg.setSentDate(new Date());

                        if( mail.getToRecipients()!=null)
			for (String recepient :  mail.getToRecipients())
			{
				Message.RecipientType recipientType = Message.RecipientType.TO;				

				msg.addRecipient(recipientType, new InternetAddress(recepient));
			}

                        if( mail.getCcRecipients()!=null)
			for (String recepient :  mail.getCcRecipients())
			{
				Message.RecipientType recipientType = Message.RecipientType.CC;

				msg.addRecipient(recipientType, new InternetAddress(recepient));
			}

                         if( mail.getBccRecipients()!=null)
			for (String recepient :  mail.getBccRecipients())
			{
				Message.RecipientType recipientType = Message.RecipientType.BCC;

				msg.addRecipient(recipientType, new InternetAddress(recepient));
			}

			Transport.send(msg);
		}
		catch (Exception e)
		{
			e.printStackTrace();

			throw new MailException(e);
		}
    }




}
