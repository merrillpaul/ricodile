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
package com.flytxt.commons.reporting.context;

import java.io.Serializable;

/**
 *
 * @author Merrill George (merrill.george@gmail.com)
 */
public final class ReportUser implements Serializable {
    private Integer userId;

    private Integer partnerId;

    public ReportUser(Integer userId, Integer partnerId, String userName) {
        this.userId = userId;
        this.partnerId = partnerId;
        this.userName = userName;
    }

    private String userName;

    public Integer getPartnerId() {
        return partnerId;
    }

    public Integer getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }
}
