/*
 * @(#) $Id:
 * Copyright Flytxt Technologies Pvt Limited. All Rights Reserved.
 *
 * This Software is the proprietary information of Flytxt technologies Pvt Limited.
 * Use is subject to License terms.
 *
 */
package com.flytxt.commons.reporting.context;

import com.flytxt.commons.reporting.parameter.objects.Parameter;
import java.io.Serializable;
import java.util.Collection;

/**
 *
 * @author Merrill George (merrill.george@gmail.com)
 */
public abstract class BaseContext implements Serializable{

    protected ReportUser user;

     /**
     * encapsulates the initial set of parameters when the chart is initialized
     * or requested
     */
    protected Collection<Parameter> initialParameters;

    public int getPartnerId() {
        return user.getPartnerId();
    }

   

    public int getUserId() {
        return user.getUserId();
    }

 

    public String getUserName() {
        return user.getUserName();
    }

   

     public Collection<Parameter> getInitialParameters() {
        return initialParameters;
    }

    public void setInitialParameters(Collection<Parameter> initialParameters) {
        this.initialParameters = initialParameters;
    }

    public void setUser(ReportUser user) {
       this.user=  user;
    }

}
