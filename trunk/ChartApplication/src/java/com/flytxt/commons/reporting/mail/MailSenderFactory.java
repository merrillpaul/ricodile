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

import com.flytxt.commons.reporting.mail.impl.MailSenderImpl;

/**
 *
 * @author Merrill George (merrill.george@gmail.com)
 */
public final class MailSenderFactory {
    public static final MailSender getSender(){
        final MailSender sender = new MailSenderImpl();
        return sender;
    }
}
