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

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

/**
 *
 * @author Merrill George (merrill.george@gmail.com)
 */
public final class SMTPAuthenticator extends Authenticator {
    private final String userName;
    private final String password;

    public SMTPAuthenticator(String userName, String password) {
        this.userName =  userName;
        this.password = password;
    }

    @Override
    protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(userName, password);
    }



}
