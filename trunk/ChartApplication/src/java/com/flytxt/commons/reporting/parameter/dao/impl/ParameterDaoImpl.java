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
package com.flytxt.commons.reporting.parameter.dao.impl;


import com.flytxt.commons.reporting.ReportHibernateUtil;
import com.flytxt.commons.reporting.parameter.ParameterProviderException;
import com.flytxt.commons.reporting.parameter.dao.ParameterDao;
import com.flytxt.commons.reporting.parameter.objects.Parameter;
import com.flytxt.commons.reporting.parameter.vo.ParamComboVO;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Merrill George (merrill.george@gmail.com)
 */
public class ParameterDaoImpl implements ParameterDao {

    private Session session;

    public ParameterDaoImpl() {
         this.session = ReportHibernateUtil.getSession();
    }
   

    public Parameter insertParameter(Parameter parameter) throws ParameterProviderException {
        try{
       
       
        session.persist(parameter);
       
        }catch(HibernateException he){
            
            throw new ParameterProviderException(he);
        }
        return parameter;
    }

    public Parameter getParameter(Long parameterId) throws ParameterProviderException {
      return  (Parameter) session.get(Parameter.class, parameterId);
    }

    public Parameter getParameterByName(String parameterName) throws ParameterProviderException {
        return
               (Parameter)
               this.session.getNamedQuery("Parameter.findByParameterName")
               .setString("parameterName", parameterName)
               .uniqueResult();

    }

    public Collection<ParamComboVO> getParametersAfterExlusion(Collection<Long> exludedParameters) {

        Collection<ParamComboVO> result  =
                new ArrayList<ParamComboVO>();

        Query q = null;
        if(exludedParameters==null || exludedParameters.isEmpty()){
            q = this.session.getNamedQuery("Parameter.findAll");
        }else{
            q = this.
                    session
                    .createQuery(
                    "SELECT p FROM Parameter p WHERE p.id not in (:parameterIds)");
            q.setParameterList("parameterIds", exludedParameters);
        }
        List<Parameter> list=  q.list();
        if(list!=null)
            for(Parameter param:list){
                ParamComboVO vo = new ParamComboVO(param.getId(),param.getParameterName());
                result.add(vo);
            }

        return result;
    }

    public void deleteParameter(Long parameterId) {


        Query q1 = this.session.createQuery(
                " delete from ChartPromptParameters cp " +
                " where cp.parameter.id = :parameterId");
        q1.setParameter("parameterId", parameterId);
        q1.executeUpdate();

        Query q = this.session.createQuery(
                "delete from Parameter where id= :parameterId");
        q.setParameter("parameterId", parameterId);

        q.executeUpdate();
    }

}
