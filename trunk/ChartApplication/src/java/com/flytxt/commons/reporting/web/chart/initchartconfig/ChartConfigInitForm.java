/*
 * @(#) $Id:
 * Copyright Flytxt Technologies Pvt Limited. All Rights Reserved.
 *
 * This Software is the proprietary information of Flytxt technologies Pvt Limited.
 * Use is subject to License terms.
 *
 */
package com.flytxt.commons.reporting.web.chart.initchartconfig;


import com.flytxt.commons.reporting.parameter.vo.InitParamVO;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.validator.LazyValidatorForm;

/**
 *
 * @author Merrill George Paul(merrill.george@gmail.com)
 */
public class ChartConfigInitForm extends LazyValidatorForm {
    
 

   

    public InitParamVO[] getChartInitParams() {
        return (InitParamVO[])this.get("initParams");
    }

   
   

    public Long getChartId() {
        return (Long)this.get("chartId");
    }

   
   
    /**
     *
     */
    public ChartConfigInitForm() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * This is the action called from the Struts framework.
     * @param mapping The ActionMapping used to select this instance.
     * @param request The HTTP Request we are processing.
     * @return
     */
    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
        ActionErrors errors = new ActionErrors();
        if (getChartId()==0) {
            errors.add("name", new ActionMessage("error.name.required"));
            // TODO: add 'error.name.required' key to your resources
        }
        return errors;
    }
}
