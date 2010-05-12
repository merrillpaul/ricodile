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
package com.flytxt.commons.reporting.parameter.business.impl;

import com.flytxt.commons.dao.DaoFactory;
import com.flytxt.commons.reporting.ReportHibernateUtil;
import com.flytxt.commons.reporting.chart.provider.dao.ChartConfigDao;
import com.flytxt.commons.reporting.parameter.ParameterProviderException;
import com.flytxt.commons.reporting.parameter.business.ParameterMasterService;
import com.flytxt.commons.reporting.parameter.dao.ParameterDao;
import com.flytxt.commons.reporting.parameter.objects.Parameter;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 *
 * @author Merrill George (merrill.george@gmail.com)
 */
public class ParameterMasterServiceImpl implements ParameterMasterService {

    public Parameter insertParameter(Parameter parameter) throws ParameterProviderException {

        ParameterDao dao= null;
        
        Session session = ReportHibernateUtil.getSession();
       
        dao = getDao();
        
        session.beginTransaction();
        try {
            parameter =dao.insertParameter(parameter);
            session.getTransaction().commit();
            return parameter;
        }
        catch (Exception ex) {
            Logger.getLogger(ParameterMasterServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            session.getTransaction().rollback();
            throw new ParameterProviderException(ex);
        }finally{
            if(session!=null){
               ReportHibernateUtil.closeSession();
            }
        }

       
    }

    public Parameter getParameter(Long parameterId) throws ParameterProviderException {
       ParameterDao dao= null;

        Session session = ReportHibernateUtil.getSession();

        dao = getDao();

        session.beginTransaction();
        try {
            Parameter parameter =dao.getParameter(parameterId);

            session.getTransaction().commit();
            return parameter;
        } catch (ParameterProviderException ex) {
            Logger.getLogger(ParameterMasterServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            session.getTransaction().rollback();
            throw ex;
        }finally{
            if(session!=null){
               ReportHibernateUtil.closeSession();
            }
        }
    }

    public Parameter getParameterByName(String parameterName) throws ParameterProviderException {
        throw new UnsupportedOperationException("Not supported yet.");
    }




    public void deleteParameter(Long parameterId) throws ParameterProviderException {


        Session session = ReportHibernateUtil.getSession();
         try {
         session.beginTransaction();
         ParameterDao dao = getDao();

         dao.deleteParameter(parameterId);

         session.getTransaction().commit();
         }catch(HibernateException ex){
              Logger.getLogger(ParameterMasterServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            session.getTransaction().rollback();

         }finally{

               ReportHibernateUtil.closeSession();
           
        }


    }

    private ParameterDao getDao() {
        try {
            return DaoFactory.getDao(ParameterDao.class);
        } catch (Exception ex) {
            Logger.getLogger(ParameterMasterServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw  new RuntimeException(ex);
        }
    }

     private ChartConfigDao getChartConfigDao() {
        try {
            return DaoFactory.getDao(ChartConfigDao.class);
        } catch (Exception ex) {
            Logger.getLogger(ParameterMasterServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw  new RuntimeException(ex);
        }
    }





}
