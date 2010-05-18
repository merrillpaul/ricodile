/*
 * @(#) $Id:
 * Copyright Flytxt Technologies Pvt Limited. All Rights Reserved.
 *
 * This Software is the proprietary information of Flytxt technologies Pvt Limited.
 * Use is subject to License terms.
 *
 */
package com.flytxt.commons.reporting.web.chart.initchartconfig;



import com.flytxt.commons.reporting.chart.ChartContextCreator;
import com.flytxt.commons.reporting.chart.ChartContext;
import com.flytxt.commons.reporting.factory.ServiceFactory;
import com.flytxt.commons.util.RandomStringGenerator;
import com.flytxt.commons.reporting.chart.entity.ChartConfig;
import com.flytxt.commons.reporting.context.ReportUser;
import com.flytxt.commons.reporting.parameter.vo.InitParamVO;
import com.flytxt.commons.reporting.web.chart.ChartBaseAction;
import com.flytxt.commons.reporting.web.chart.initchartconfig.json.ChartInitJsonMap;
import com.flytxt.commons.reporting.web.json.charts.ChartJsonEmitter;
import com.flytxt.commons.reporting.web.json.charts.ChartJsonMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * This is the struts action class which expects a chartId and returns a JSOn
 * containing the  parameters of a chart
 * 
 *
 * This class creates the chartContext  for this request, creates a unique
 * chartrunsessionid and puts this chartcontext into the session. this chart context
 * is available until the chart is cleaned up
 * @author Merrill George Paul (merrill.george@gmail.com)
 */
public class ChartConfigInitAction extends ChartBaseAction {
    
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    
    /**
     * This is the struts action class which expects a chartId and returns a JSOn
     * containing the  parameters of a chart
     *
     * The workflow is this:
     * <ul>
     *  <li>
     *      A random chart run session id is generated
     *  </li>
     *  <li>
     *      The chart config is retrieved based on the chart id
     *  </li>
     *  <li>
     *      A chartcontext is created and this is placed in a chart session map
     *      with chart session id as key
     *  </li>
     *  <li>
     *      Initial request parameters are parsed and applied to the chart context
     *  </li>
     *  <li>
     *      The chart details are written into the response stream as JSON
     *  </li>
     * </ul>
     * @param mapping The ActionMapping used to select this instance.
     * @param form The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return The return JSON would contain the chart attributes, optional attributes
     * specific for the actual charting framework . For eg if its fusioncharts,
     * the UI expects the name of the SWF file for the charttype
     * <br/>
     * <pre>
     * {
     *  chartId:<i>theChartId</i>,
     *  chartRunSessionId:<i>runSessionId</i>,
     *  promptParameters:{
     *      <i>promptableParameterMap ( this can be null)</i>
     *  },
     *  chartInfo:{
     *      <i>chart details such as title width and height etc</i>
     *  },
     *  frameworkAttrs:{
     *      <i> framework attributes depending on the framework
     *      if dfusion chart is used, then the fusion chart SWF based on the chart
     *      mapping
     *      </i>
     *  }
     *
     * }
     * </pre>
     * <br/>
     */   
    public ActionForward initChart(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        
        ChartConfigInitForm initForm = (ChartConfigInitForm)form;
       

        //Long chartId = initForm.getChartId();
        String chartName =(String)initForm.get("chartName");
        InitParamVO[] initParams = initForm.getChartInitParams();
        
        String chartRunSessionId =
                "ChartRun_"+new RandomStringGenerator().getRandomString(10);

        ChartConfig chart =  ServiceFactory.getChartConfigService()
                .getChartConfig(chartName);

        ChartContext context =  null;
        if(chart!=null){
            ChartContextCreator creator = getChartContextCreator(request);
            context = creator.createChartContext(chart, null/*new ReportUser(userId, partnerId, userName)*/);
            prepareInitialParameterValues(context,initParams);
            getChartRunSessionMapper(request).addContext(chartRunSessionId, context);
        }

        ChartJsonMap json = new ChartInitJsonMap(chartRunSessionId, context);
        return ChartJsonEmitter.emitJson(json, request,response);
               
        
    }



   
    
}
