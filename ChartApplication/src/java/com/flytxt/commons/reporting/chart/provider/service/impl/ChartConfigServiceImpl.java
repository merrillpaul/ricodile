/*
 * @(#) $Id:
 * Copyright Flytxt Technologies Pvt Limited. All Rights Reserved.
 *
 * This Software is the proprietary information of Flytxt technologies Pvt Limited.
 * Use is subject to License terms.
 *
 */
package com.flytxt.commons.reporting.chart.provider.service.impl;

import com.flytxt.commons.dao.DaoFactory;
import com.flytxt.commons.reporting.ReportHibernateUtil;
import com.flytxt.commons.reporting.chart.entity.ChartConfig;
import com.flytxt.commons.reporting.chart.entity.ChartPromptParameters;
import com.flytxt.commons.reporting.chart.provider.ChartConfigException;
import com.flytxt.commons.reporting.chart.provider.dao.ChartConfigDao;
import com.flytxt.commons.reporting.chart.provider.service.ChartConfigService;
import com.flytxt.commons.reporting.parameter.dao.ParameterDao;
import com.flytxt.commons.reporting.parameter.objects.Parameter;
import com.flytxt.commons.reporting.parameter.vo.ParamComboVO;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Session;

/**
 *
 * @author Merrill George (merrill.george@gmail.com)
 */
public class ChartConfigServiceImpl implements ChartConfigService {

    public ChartConfig getChartConfig(Long chartId) {

        ChartConfig config = null;
        Session session = ReportHibernateUtil.getSession();
        session.beginTransaction();
        try {
            config = getChartConfigDao().getChartConfig(chartId);
           
             config =  ChartConfig.cloneForAll(config);
            session.getTransaction().commit();
           
        } catch (Exception ex) {
            ex.printStackTrace();
            session.getTransaction().rollback();
        }finally{
            ReportHibernateUtil.closeSession();
        }
        return config;
    }

    public ChartConfig saveChartConfig(ChartConfig chart) throws ChartConfigException {

        Session session = ReportHibernateUtil.getSession();
        session.beginTransaction();
         try {
           chart = getChartConfigDao().saveChartConfig(chart);
           
            session.getTransaction().commit();
            chart =  ChartConfig.cloneForAll(chart);
        } catch (Exception ex) {
            ex.printStackTrace();
            
            session.getTransaction().rollback();
            throw new ChartConfigException(ex);
        }finally{
            ReportHibernateUtil.closeSession();
        }

        return chart;
    }

    public void deleteChartConfig(Long chartId) throws ChartConfigException {
        Session session = ReportHibernateUtil.getSession();
        session.beginTransaction();
         try {
           getChartConfigDao().deleteChart(chartId);
            session.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            session.getTransaction().rollback();
            throw new ChartConfigException(ex);
        }finally{
            ReportHibernateUtil.closeSession();
        }
    }


    public void addChartParameter(Parameter p,Long chartId) throws ChartConfigException {
        Session session = ReportHibernateUtil.getSession();

        ChartConfigDao dao = null;
        dao =getChartConfigDao() ;
        session.beginTransaction();

         try {
            ChartConfig config = getChartConfigDao().getChartConfig(chartId);
            Collection<ChartPromptParameters> promptParameters =
                    config.getChartPromptParameters();
            ChartPromptParameters prompt =
                    new ChartPromptParameters();
            prompt.setChartConfig(config);
            prompt.setParameter(p);
            prompt.setOrder( (short)promptParameters.size());
            promptParameters.add(prompt);
            config.setChartPromptParameters(promptParameters);
            dao.saveChartConfig(config);
            session.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            session.getTransaction().rollback();
            throw new ChartConfigException(ex);
        }finally{
            ReportHibernateUtil.closeSession();
        }
    }

    public void updateChartParameters(Collection<Parameter> parameters,Long chartId) throws ChartConfigException {

         Session session = ReportHibernateUtil.getSession();

        ChartConfigDao dao = null;
        dao =getChartConfigDao() ;
        session.beginTransaction();

         try {
            ChartConfig config = getChartConfigDao().getChartConfig(chartId);
            Collection<ChartPromptParameters> promptParameters =
                   new ArrayList<ChartPromptParameters>();
            short order = 0;
            for(Parameter p: parameters){
                ChartPromptParameters prompt =
                        new ChartPromptParameters();
                prompt.setChartConfig(config);
                prompt.setParameter(p);
                prompt.setOrder( order);
                promptParameters.add(prompt);
                order++;
            }
            config.setChartPromptParameters(promptParameters);
            dao.saveChartConfig(config);
            session.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            session.getTransaction().rollback();
            throw new  ChartConfigException(ex);
        }finally{
            ReportHibernateUtil.closeSession();
        }
    }




    public Collection<ParamComboVO> getUnAssignedParameters(Long chartId) {

        Session session = ReportHibernateUtil.getSession();
        Collection<ParamComboVO> result = null;
        ChartConfigDao dao = null;
        dao =getChartConfigDao() ;
        session.beginTransaction();
         try {
            ChartConfig config = dao.getChartConfig(chartId);

            Collection<ChartPromptParameters> params =
                    config.getChartPromptParameters();
            Collection<Long> paramIds = new ArrayList<Long>();
            if(params!=null){
                for(ChartPromptParameters param : params){
                    paramIds.add(param.getParameter().getId());
                }
            }
            ParameterDao paramDao= DaoFactory.getDao(ParameterDao.class);
            result = paramDao.getParametersAfterExlusion(paramIds);
            session.getTransaction().commit();

        } catch (Exception ex) {
            ex.printStackTrace();
            session.getTransaction().rollback();
        }finally{
            ReportHibernateUtil.closeSession();
        }
        return result;
       
    }





    private ChartConfigDao getChartConfigDao() {
        try {
            return DaoFactory.getDao(ChartConfigDao.class);
        } catch (Exception ex) {
            Logger.getLogger(ChartConfigServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException(ex);
        }
    }



    


}
