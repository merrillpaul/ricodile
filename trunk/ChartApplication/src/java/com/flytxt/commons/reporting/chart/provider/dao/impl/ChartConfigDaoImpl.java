/*
 * @(#) $Id:
 * Copyright Flytxt Technologies Pvt Limited. All Rights Reserved.
 *
 * This Software is the proprietary information of Flytxt technologies Pvt Limited.
 * Use is subject to License terms.
 *
 */
package com.flytxt.commons.reporting.chart.provider.dao.impl;

import com.flytxt.commons.reporting.ReportHibernateUtil;
import com.flytxt.commons.reporting.chart.entity.ChartConfig;
import com.flytxt.commons.reporting.chart.entity.ChartPromptParameters;
import com.flytxt.commons.reporting.chart.provider.dao.ChartConfigDao;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Merrill George (merrill.george@gmail.com)
 */
public class ChartConfigDaoImpl implements ChartConfigDao {
    private final Session session;



    public ChartConfigDaoImpl() {
          this.session = ReportHibernateUtil.getSession();
    }




    public ChartConfig getChartConfig(Long chartId) {
        
        ChartConfig chart = (ChartConfig)
                this.session.get(ChartConfig.class, chartId);

     /*   List<ChartPromptParameters> params =
                this.session.getNamedQuery(
                "ChartPromptParameters.findByChartId")
                .setParameter("chartId", chartId).list();
        chart.setChartPromptParameters(params);*/

        return chart;
    }

    public ChartConfig saveChartConfig(ChartConfig chart) {

        Collection<ChartPromptParameters> params  =  new ArrayList<ChartPromptParameters>();
        if(chart.getChartPromptParameters()!=null && !chart.getChartPromptParameters().isEmpty()){
            params.addAll(chart.getChartPromptParameters());
            chart.setChartPromptParameters(new ArrayList<ChartPromptParameters>());
        }
        if(chart.getChartId()==null || chart.getChartId()==0)
        this.session.persist(chart);
        else
            this.session.merge(chart);

        for(ChartPromptParameters param : params){
            param.setChartConfig(chart);
            this.session.saveOrUpdate(param);
        }
       
        

        return chart;
    }

    public void deleteChart(Long chartId) {

        ChartConfig cfg = (ChartConfig)
                this.session.get(ChartConfig.class, chartId);
        this.session.delete(cfg);
    }

    public boolean isParameterUsed(Long parameterId) {

        Query q =
                this.session.createQuery(
                "select count(distinct prompt) from ChartPromptParameters prompt " +
                "where prompt.parameter.id = :id");
        int result =0 ;

        q.setParameter("id", parameterId);
        result = (Integer)q.uniqueResult();

        return result==0?false:true;
    }

    public ChartConfig getChartConfig(String chartName) {

        return
               (ChartConfig) this.session.getNamedQuery("ChartConfig.findByName")
                .setParameter("name", chartName).uniqueResult();
    }

}
