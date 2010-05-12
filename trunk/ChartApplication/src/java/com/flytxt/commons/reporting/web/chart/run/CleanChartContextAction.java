/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.flytxt.commons.reporting.web.chart.run;

import com.flytxt.commons.reporting.web.chart.ChartBaseAction;
import com.flytxt.commons.reporting.web.json.charts.ChartJsonEmitter;
import com.flytxt.commons.reporting.web.json.charts.ChartStatusJsonMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;

/**
 *
 * @author merrill.paul
 */
public class CleanChartContextAction extends ChartBaseAction {
    
    /* forward name="success" path="" */
    private final static String SUCCESS = "success";
    
    /**
     * This is the Struts action method called on
     * http://.../actionPath?dispatch=myAction1,
     * where "dispatch" is the value specified in <action> element :
     * 
     * ( <action parameter="dispatch" .../> )
     * to be called aysncrynously
     */
    public ActionForward clean(ActionMapping mapping, ActionForm  form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        ChartRunForm chartForm = (ChartRunForm) form;
        getChartRunSessionMapper(request).removeContext(chartForm.getChartRunId());


        return ChartJsonEmitter.emitJson(new ChartStatusJsonMap(), request, response);
    }

   
}