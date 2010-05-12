/*
 * @(#) $Id:
 * Copyright Flytxt Technologies Pvt Limited. All Rights Reserved.
 *
 * This Software is the proprietary information of Flytxt technologies Pvt Limited.
 * Use is subject to License terms.
 *
 */
package com.flytxt.commons.reporting.web.json.charts;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONObject;

/**
 *
 * @author Merrill George (merrill.george@gmail.com)
 */
public final class ChartJsonEmitter {
    public static <T extends Object> T emitJson(ChartJsonMap json,
            HttpServletRequest request,HttpServletResponse response) throws
            IOException{
        json.buildMap();
        JSONObject object =  new JSONObject();
        object.put("result", json.getMap());
        response.setContentType("application/javascript");
        response.setCharacterEncoding("UTF-8");
        String jsonString  = object.getString("result");
        PrintWriter pw = response.getWriter();
        pw.write(jsonString);
        pw.flush();
        return null;

    }
}
