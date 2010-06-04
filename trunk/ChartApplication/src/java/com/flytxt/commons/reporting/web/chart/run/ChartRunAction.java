/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.flytxt.commons.reporting.web.chart.run;

import com.flytxt.commons.reporting.chart.ChartContext;
import com.flytxt.commons.reporting.chart.ChartEngine;
import com.flytxt.commons.reporting.chart.ChartEngineFactory;
import com.flytxt.commons.reporting.chart.ChartOutput;
import com.flytxt.commons.reporting.parameter.vo.InitParamVO;
import com.flytxt.commons.reporting.web.chart.ChartBaseAction;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;

/**
 *
 * @author merrill.paul
 */
public class ChartRunAction extends ChartBaseAction {
    
    /* forward name="success" path="" */
    private final static String SUCCESS = "success";
    
    /**
     * This is the Struts action method called on
     * http://.../actionPath?method=myAction1,
     * where "method" is the value specified in <action> element : 
     * ( <action parameter="method" .../> )
     */
    public ActionForward run(ActionMapping mapping, ActionForm  form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        
        ChartRunForm chartForm = (ChartRunForm) form;
        ChartContext ctx =
        getChartRunSessionMapper(request).getContext(chartForm.getChartRunId());

         /* in case on running someinit params are changed*/
        InitParamVO[] initParams = chartForm.getChartInitParams();
        if(initParams!=null && initParams.length>0){
        	 prepareInitialParameterValues(ctx,initParams);
        }

        ChartEngine engine = ChartEngineFactory.getEngine(ctx);
        ChartOutput output = engine.fillUp(ctx);
        response.setContentType(output.getContentType());
        PrintWriter pw = response.getWriter();
        pw.write(new String(output.getContent()));
        pw.flush();
        return null;
    }

   
}