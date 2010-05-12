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
package com.flytxt.commons.reporting.web.chart.context;

import com.flytxt.commons.reporting.chart.ChartContext;
import com.flytxt.commons.reporting.chart.ChartRunMap;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * TODO use shareddata
 * @author Merrill George (merrill.george@gmail.com)
 */
public class HttpSessionAwareChartRunMap implements ChartRunMap {

    private static final String CHART_RUN_SESSION_MAP_KEY=
            "com.flytxt.commons.reporting.web.chart.context.CHART_RUN_MAP";

    private final HttpSession session;

    public HttpSessionAwareChartRunMap(HttpServletRequest request) {
        this.session = request.getSession();
    }

    public void addContext(String chartRunSessionId, ChartContext context) {
        this.getSessionMap().put(chartRunSessionId, context);
    }

    public ChartContext getContext(String chartRunSessionId) {
       return this.getSessionMap().get(chartRunSessionId);
    }

    public void removeContext(String chartRunSessionId) {
        Map<String,ChartContext> map =this.getSessionMap();
        map.remove(chartRunSessionId);
        if(map.isEmpty()){
            this.session.removeAttribute(CHART_RUN_SESSION_MAP_KEY);
        }

    }
    
    private Map<String,ChartContext> getSessionMap(){
        Map<String,ChartContext> map=null;
        if(this.session.getAttribute(CHART_RUN_SESSION_MAP_KEY)!=null)
            map= (Map<String,ChartContext>)
                    (this.session.getAttribute(CHART_RUN_SESSION_MAP_KEY));
        else{
            map = new HashMap<String, ChartContext>();
            this.session.setAttribute(CHART_RUN_SESSION_MAP_KEY, map);
        }
        return map;
    }

}
