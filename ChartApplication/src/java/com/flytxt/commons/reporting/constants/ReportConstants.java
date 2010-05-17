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
package com.flytxt.commons.reporting.constants;

/**
 *
 * @author Merrill George (merrill.george@gmail.com)
 */
public class ReportConstants {

    public enum SystemParameters{
        FLYTXT_REPORT_USER_ID,
        FLYTXT_REPORT_USER_NAME,
        FLYTXT_REPORT_USER_PARTNER_ID;
    }


    public interface DateFormats{
        public static final String DATE_FORMAT="dd-MMM-yyyy";
        public static final String TIME_FORMAT="dd-MMM-yyyy HH:mm:ss";
    }
}
