/*
 * @(#) $Id:
 * Copyright Flytxt Technologies Pvt Limited. All Rights Reserved.
 *
 * This Software is the proprietary information of Flytxt technologies Pvt Limited.
 * Use is subject to License terms.
 *
 */
package com.flytxt.commons.reporting.parameter.provider;

import com.flytxt.commons.reporting.parameter.objects.Parameter;
import com.flytxt.commons.reporting.parameter.vo.InitParamVO;

/**
 *
 * @author Merrill George (merrill.george@gmail.com)
 */
public interface InitialParameterProvider {

    Parameter prepareParameter(InitParamVO initParam);
}
