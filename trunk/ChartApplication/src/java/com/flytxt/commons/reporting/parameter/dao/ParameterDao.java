/*
 * @(#) $Id:
 * Copyright Flytxt Technologies Pvt Limited. All Rights Reserved.
 *
 * This Software is the proprietary information of Flytxt technologies Pvt Limited.
 * Use is subject to License terms.
 *
 */
package com.flytxt.commons.reporting.parameter.dao;


import com.flytxt.commons.dao.Dao;
import com.flytxt.commons.dao.DaoImpl;
import com.flytxt.commons.reporting.parameter.ParameterProviderException;
import com.flytxt.commons.reporting.parameter.objects.Parameter;
import com.flytxt.commons.reporting.parameter.vo.ParamComboVO;
import java.util.Collection;

/**
 *
 * @author Merrill George (merrill.george@gmail.com)
 */
@DaoImpl("com.flytxt.commons.reporting.parameter.dao.impl.ParameterDaoImpl")
public interface ParameterDao extends Dao  {

    Parameter insertParameter(Parameter parameter)
            throws ParameterProviderException;


    Parameter getParameter(Long parameterId) throws ParameterProviderException;

    Parameter getParameterByName(String parameterName)
            throws ParameterProviderException;


    Collection<ParamComboVO>
            getParametersAfterExlusion(Collection<Long> exludedParameters);

    void deleteParameter(Long parameterId);
}
