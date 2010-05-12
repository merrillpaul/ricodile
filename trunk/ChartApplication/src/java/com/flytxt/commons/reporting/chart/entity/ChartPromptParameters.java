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



import com.flytxt.commons.reporting.parameter.objects.Parameter;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Merrill George (merrill.george@gmail.com)
 */
@Entity
@Table(name = "REP_FWK_CHRT_PROMPT_PARAMETERS")
@NamedQueries(
    {
        @NamedQuery(
            name = "ChartPromptParameters.findAll",
            query = "SELECT c FROM ChartPromptParameters c"),

        @NamedQuery(
            name = "ChartPromptParameters.findByChartId",
            query = "SELECT c FROM ChartPromptParameters c WHERE c.chartPromptParametersPK.chartId = :chartId order by c.order"),

        @NamedQuery(
            name = "ChartPromptParameters.findByParameterId",
            query = "SELECT c FROM ChartPromptParameters c WHERE c.chartPromptParametersPK.parameterId = :parameterId"),
            
        @NamedQuery(
            name = "ChartPromptParameters.findByOrder",
            query = "SELECT c FROM ChartPromptParameters c WHERE c.order = :order")
})
public class ChartPromptParameters implements Serializable {
    private static final long serialVersionUID = 1L;


    protected ChartPromptParametersPK chartPromptParametersPK;

   
    private short order;


    private ChartConfig chartConfig;


    private Parameter parameter;

    public ChartPromptParameters() {
        chartPromptParametersPK = new ChartPromptParametersPK();
    }

    public ChartPromptParameters(ChartPromptParametersPK chartPromptParametersPK) {
        this.chartPromptParametersPK = chartPromptParametersPK;
    }

    public ChartPromptParameters(ChartPromptParametersPK chartPromptParametersPK, short order) {
        this.chartPromptParametersPK = chartPromptParametersPK;
        this.order = order;
    }

    public ChartPromptParameters(long chartId, long parameterId) {
        this.chartPromptParametersPK = new ChartPromptParametersPK(chartId, parameterId);
    }

    @EmbeddedId
    public ChartPromptParametersPK getChartPromptParametersPK() {
        return chartPromptParametersPK;
    }

    public void setChartPromptParametersPK(ChartPromptParametersPK chartPromptParametersPK) {
        this.chartPromptParametersPK = chartPromptParametersPK;
    }

    @Basic(optional = false)
    @Column(name = "PARAMETER_ORDER")
    public short getOrder() {
        return order;
    }

    public void setOrder(short order) {
        this.order = order;
    }

    @JoinColumn(name = "CHART_ID", referencedColumnName = "CHART_ID", insertable = false, updatable = false)
    @ManyToOne(optional = false,fetch=FetchType.LAZY)
    public ChartConfig getChartConfig() {
        return chartConfig;

    }

    public void setChartConfig(ChartConfig chartConfig) {
        this.chartConfig = chartConfig;
         if(this.chartConfig!=null)
            this.chartPromptParametersPK.setChartId(this.chartConfig.getChartId());
    }

    @JoinColumn(name = "PARAMETER_ID", referencedColumnName = "PARAMETER_ID", insertable = false, updatable = false)
    @ManyToOne(optional = false,fetch=FetchType.LAZY)
    public Parameter getParameter() {
        return parameter;
    }

    public void setParameter(Parameter parameter) {
        this.parameter = parameter;
         if(this.parameter!=null)
            this.chartPromptParametersPK.setParameterId(this.parameter.getId());
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (chartPromptParametersPK != null ? chartPromptParametersPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ChartPromptParameters)) {
            return false;
        }
        ChartPromptParameters other = (ChartPromptParameters) object;
        if ((this.chartPromptParametersPK == null && other.chartPromptParametersPK != null) || (this.chartPromptParametersPK != null && !this.chartPromptParametersPK.equals(other.chartPromptParametersPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.flytxt.commons.reporting.chart.entity.ChartPromptParameters[chartPromptParametersPK=" + chartPromptParametersPK + "]";
    }

}
