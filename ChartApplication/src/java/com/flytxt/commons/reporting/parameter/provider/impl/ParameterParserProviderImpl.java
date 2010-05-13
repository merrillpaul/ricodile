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
package com.flytxt.commons.reporting.parameter.provider.impl;

import com.flytxt.commons.reporting.parameter.ParameterProviderException;
import com.flytxt.commons.reporting.parameter.objects.Parameter;
import com.flytxt.commons.reporting.parameter.objects.ParameterValue;
import com.flytxt.commons.reporting.parameter.provider.ParameterParserProvider;
import java.util.Collection;
import java.util.Map;

/**
 *
 * @author Merrill George (merrill.george@gmail.com)
 */
public class ParameterParserProviderImpl implements ParameterParserProvider{

    public Collection<ParameterValue> getParameterValues(
            String parameterName, Map<String, Object> filledUpParams)
            throws ParameterProviderException {
        
        throw new UnsupportedOperationException("Not supported yet.");
        
    }

    public Map<String, Object> toMapFromFilledParameter(Parameter parameter) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
