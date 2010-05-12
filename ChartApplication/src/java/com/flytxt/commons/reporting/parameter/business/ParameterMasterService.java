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
package com.flytxt.commons.reporting.parameter.business;

import com.flytxt.commons.facade.BusinessService;
import com.flytxt.commons.facade.ServiceImpl;
import com.flytxt.commons.reporting.parameter.ParameterProviderException;
import com.flytxt.commons.reporting.parameter.objects.Parameter;


/**
 *
 * @author Merrill George (merrill.george@gmail.com)
 */
@ServiceImpl("com.flytxt.commons.reporting.parameter.business.impl.ParameterMasterServiceImpl")
public interface ParameterMasterService extends BusinessService {

     Parameter insertParameter(Parameter parameter)
            throws ParameterProviderException;


    Parameter getParameter(Long parameterId) throws ParameterProviderException;

    Parameter getParameterByName(String parameterName)
            throws ParameterProviderException;

    void deleteParameter(Long parameterId)
            throws ParameterProviderException;


    


    

}
