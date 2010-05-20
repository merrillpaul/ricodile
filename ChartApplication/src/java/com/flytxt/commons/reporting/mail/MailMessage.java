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
package com.flytxt.commons.reporting.mail;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 *
 * @author Merrill George (merrill.george@gmail.com)
 */
public class MailMessage implements Serializable {


    private String sender;
    private String subject;
    private String text;


    private List<String> recipients = new ArrayList<String>();
    private List<String> attachments = new ArrayList<String>();

    private List<ByteArrayDataSource> htmlImageDataSources = new ArrayList<ByteArrayDataSource>();

    private ByteArrayDataSource byteArrayDataSource;

	public MailMessage()
	{
	}

	public String getSender()
	{
		return sender==null?MailSessionPropertiesProvider.getReportsSender():sender;
	}

	public void setSender(String sender)
	{
		this.sender = sender;
	}

	public List<String> getRecipients()
	{
		return recipients;
	}

	public void setRecipients(List<String> recipients)
	{
		this.recipients = recipients;
	}

	public String getSubject()
	{
		return subject;
	}

	public void setSubject(String subject)
	{
		this.subject = subject;
	}

	public String getText()
	{
		return text;
	}

	public void setText(String text)
	{
		this.text = text;
	}

	public ByteArrayDataSource getByteArrayDataSource()
	{
		return byteArrayDataSource;
	}

	public void setByteArrayDataSource(ByteArrayDataSource byteArrayDataSource)
	{
		this.byteArrayDataSource = byteArrayDataSource;
	}

	public List<String> getAttachments()
	{
		return attachments;
	}

	public void addAttachment(String fileName)
	{
		attachments.add(fileName);
	}

	public void addRecepient(String recipient)
	{
		recipients.add(recipient);
	}

	public String formatRecipients(String delimiter)
	{
		String addresses = "";

		for (int i = 0; i < recipients.size(); i++)
		{
			addresses += recipients.get(i) + delimiter;
		}

		return addresses.substring(0, addresses.length() - 1);
	}

	public void parseRecipients(String value)
	{
		StringTokenizer st = new StringTokenizer(value, "\t\n\r\f;,|");

		recipients = new ArrayList<String>();

		while (st.hasMoreElements())
		{
			recipients.add(st.nextToken());
		}
	}

	public List<ByteArrayDataSource> getHtmlImageDataSources()
	{
		return htmlImageDataSources;
	}

	public void addHtmlImageDataSources(ArrayList<ByteArrayDataSource> htmlImageDataSources)
	{
		this.htmlImageDataSources.addAll(htmlImageDataSources);
	}


}
