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
        FLYTXT_REPORT_USER_PARTNER_ID,
        FLYTXT_REQUESTED_TIME,
        FLYTXT_SQL_TZOFFSET_FOR_USER
        ;
    }


    public interface DateFormats{
        public static final String DATE_FORMAT="dd-MMM-yyyy";
        public static final String TIME_FORMAT="dd-MMM-yyyy HH:mm:ss";
    }


    public enum ContentTypes{
        CONTENT_TYPE_HTML("text/html"),
        CONTENT_TYPE_PLAIN("text/plain");
        private String contentType;

        private ContentTypes(String contentType){
            this.contentType = contentType;
        }

        public String getKey(){
            return this.contentType;
        }
    }
}
