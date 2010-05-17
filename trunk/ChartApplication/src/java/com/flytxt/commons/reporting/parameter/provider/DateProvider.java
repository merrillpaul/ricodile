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
package com.flytxt.commons.reporting.parameter.provider;

import static com.flytxt.commons.reporting.constants.ReportConstants.DateFormats.DATE_FORMAT;
import static com.flytxt.commons.reporting.constants.ReportConstants.DateFormats.TIME_FORMAT;

import com.flytxt.commons.reporting.parameter.ParameterProviderException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Merrill George (merrill.george@gmail.com)
 */
public class DateProvider {

    private SimpleDateFormat dateFormat;

    private DateProvider() {
    }

    public SimpleDateFormat getDateFormat() {
        return dateFormat;
    }

    private void setDateFormat(String format) {
        this.dateFormat = new SimpleDateFormat(format);
    }

    public Date parseDate(String date) throws ParameterProviderException {
        try {
            return dateFormat.parse(date);
        } catch (Exception e) {
            throw new ParameterProviderException("Use " + dateFormat.toPattern());
        }
    }

    public String formatDate(Date date) {
        return dateFormat.format(date);
    }

    public static DateProvider getDateProvider(){
        DateProvider d = new DateProvider();
        d.setDateFormat(DATE_FORMAT);
        return d;
    }


     public static DateProvider getDateTimeProvider(){
        DateProvider d = new DateProvider();
        d.setDateFormat(TIME_FORMAT);
        return d;
    }
}
