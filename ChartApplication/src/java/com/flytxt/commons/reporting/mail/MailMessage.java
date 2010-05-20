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
import static com.flytxt.commons.reporting.mail.MailConstants.RecipientType;

/**
 *
 * @author Merrill George (merrill.george@gmail.com)
 */
public class MailMessage implements Serializable {

    private String sender;
    private String subject;
    private String text;
    private List<String> attachments = new ArrayList<String>();
    private List<ByteArrayDataSource> htmlImageDataSources = new ArrayList<ByteArrayDataSource>();
    private ByteArrayDataSource byteArrayDataSource;
    private List<String> toRecipients = new ArrayList<String>();
    private List<String> ccRecipients = new ArrayList<String>();
    private List<String> bccRecipients = new ArrayList<String>();

    public MailMessage() {
    }

    public String getSender() {
        return sender == null ? MailSessionPropertiesProvider.getReportsSender() : sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public List<String> getToRecipients() {
        return toRecipients;
    }

    public void setToRecipients(List<String> recipients) {
        this.toRecipients = recipients;
    }

    public List<String> getCcRecipients() {
        return ccRecipients;
    }

    public void setCcRecipients(List<String> recipients) {
        this.ccRecipients = recipients;
    }

    public List<String> getBccRecipients() {
        return bccRecipients;
    }

    public void setBccRecipients(List<String> recipients) {
        this.bccRecipients = recipients;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public ByteArrayDataSource getByteArrayDataSource() {
        return byteArrayDataSource;
    }

    public void setByteArrayDataSource(ByteArrayDataSource byteArrayDataSource) {
        this.byteArrayDataSource = byteArrayDataSource;
    }

    public List<String> getAttachments() {
        return attachments;
    }

    public void addAttachment(String fileName) {
        attachments.add(fileName);
    }

    public void addRecepient(String recipient, RecipientType recipientType) {

        switch (recipientType) {
            case TO:
                toRecipients.add(recipient);
                break;
            case CC:
                ccRecipients.add(recipient);
                break;
            case BCC:
                bccRecipients.add(recipient);
                break;
        }


    }

    public String formatRecipients(String delimiter, RecipientType recipientType) {
        String addresses = "";
        List<String> targetList = null;
        switch (recipientType) {
            case TO:
                targetList = toRecipients;
                break;
            case CC:
                targetList = ccRecipients;
                break;
            case BCC:
                targetList = bccRecipients;
                break;
        }

        for (int i = 0; i < targetList.size(); i++) {
            addresses += targetList.get(i) + delimiter;
        }

        return addresses.substring(0, addresses.length() - 1);
    }

    public void parseRecipients(String value, RecipientType recipientType) {

        List<String> targetList = new ArrayList<String>();

        switch (recipientType) {
            case TO:
                toRecipients = targetList;
                break;
            case CC:
                ccRecipients = targetList;
                break;
            case BCC:
                bccRecipients = targetList;
                break;
        }


        StringTokenizer st = new StringTokenizer(value, "\t\n\r\f;,|");



        while (st.hasMoreElements()) {
            targetList.add(st.nextToken());
        }
    }

    public List<ByteArrayDataSource> getHtmlImageDataSources() {
        return htmlImageDataSources;
    }

    public void addHtmlImageDataSources(ArrayList<ByteArrayDataSource> htmlImageDataSources) {
        this.htmlImageDataSources.addAll(htmlImageDataSources);
    }
}
