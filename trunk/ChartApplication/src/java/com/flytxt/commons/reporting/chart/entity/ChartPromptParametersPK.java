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
package com.flytxt.commons.reporting.chart.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Merrill George (merrill.george@gmail.com)
 */
@Embeddable
public class ChartPromptParametersPK implements Serializable {
   
    private long chartId;
  
    private long parameterId;

    public ChartPromptParametersPK() {
    }

    public ChartPromptParametersPK(long chartId, long parameterId) {
        this.chartId = chartId;
        this.parameterId = parameterId;
    }

    @Basic(optional = false)
    @Column(name = "CHART_ID")
    public long getChartId() {
        return chartId;
    }

    public void setChartId(long chartId) {
        this.chartId = chartId;
    }

     @Basic(optional = false)
    @Column(name = "PARAMETER_ID")
    public long getParameterId() {
        return parameterId;
    }

    public void setParameterId(long parameterId) {
        this.parameterId = parameterId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) chartId;
        hash += (int) parameterId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ChartPromptParametersPK)) {
            return false;
        }
        ChartPromptParametersPK other = (ChartPromptParametersPK) object;
        if (this.chartId != other.chartId) {
            return false;
        }
        if (this.parameterId != other.parameterId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.flytxt.commons.reporting.chart.entity.ChartPromptParametersPK[chartId=" + chartId + ", parameterId=" + parameterId + "]";
    }

}
