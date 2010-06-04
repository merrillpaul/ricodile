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
package com.flytxt.commons.reporting.web.chart.run;

import com.flytxt.commons.reporting.web.chart.initchartconfig.ChartConfigInitForm;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

/**
 *
 * @author Merrill George Paul(merrill.george@gmail.com)
 */
public class ChartRunForm extends ChartConfigInitForm {
    
   

    public String getChartRunId() {
      return (String)this.get("chartRunId");
    }

    
   

    /**
     *
     */
    public ChartRunForm() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * This is the action called from the Struts framework.
     * @param mapping The ActionMapping used to select this instance.
     * @param request The HTTP Request we are processing.
     * @return
     */
    @Override
    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
        ActionErrors errors = new ActionErrors();
        if (getChartRunId() == null || getChartRunId().length() < 1) {
            errors.add("chartRunId", new ActionMessage("error.chart.chartRunId.required"));
            // TODO: add 'error.name.required' key to your resources
        }
        return errors;
    }
}
