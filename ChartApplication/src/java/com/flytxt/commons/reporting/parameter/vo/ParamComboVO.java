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
package com.flytxt.commons.reporting.parameter.vo;

import java.io.Serializable;

/**
 *
 * @author Merrill George (merrill.george@gmail.com)
 */
public class ParamComboVO implements Serializable{
    private Long id;
    private String name;

    public ParamComboVO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public ParamComboVO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    

}
