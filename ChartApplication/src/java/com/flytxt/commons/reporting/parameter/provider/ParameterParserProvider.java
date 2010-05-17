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
package com.flytxt.commons.reporting.parameter.provider;

import com.flytxt.commons.reporting.parameter.ParameterProviderException;
import com.flytxt.commons.reporting.parameter.objects.Parameter;
import com.flytxt.commons.reporting.parameter.objects.ParameterValue;
import java.util.Collection;
import java.util.Map;

/**
 *
 * @author Merrill George (merrill.george@gmail.com)
 */
public interface ParameterParserProvider {

    /**
     * Should populate Parameter values from a query or static list or a
     * simple single value .
     * If the parameter passed is a query type, then the already filled
     *
     * @param parameterName
     * @param filledUpParams
     * @return
     */
    Collection<ParameterValue> getParameterValues(String parameterName,
            Map<String,Object> filledUpParams) throws ParameterProviderException;

    Map<String,Object> toMapFromFilledParameter(Parameter parameter)
            throws ParameterProviderException;

    Collection<String> getQueryInlineProvidedParameterNames(String queryString);

}
