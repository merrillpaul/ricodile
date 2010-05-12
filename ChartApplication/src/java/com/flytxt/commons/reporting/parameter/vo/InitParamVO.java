/*
 * @(#) $Id:
 * Copyright Flytxt Technologies Pvt Limited. All Rights Reserved.
 *
 * This Software is the proprietary information of Flytxt technologies Pvt Limited.
 * Use is subject to License terms.
 *
 */
package com.flytxt.commons.reporting.parameter.vo;

import com.flytxt.commons.reporting.parameter.ParameterConstants;

/**
 * This enacpsulates the initial set of parameters provided when a chart or
 * report is requested to be rendered in the screen
 * @author Merrill George (merrill.george@gmail.com)
 */
public class InitParamVO {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    private String value;
    private String type=ParameterConstants.ValueClassType.STRING.getKey();

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    
}
