/*
 * @(#) $Id:
 * Copyright Flytxt Technologies Pvt Limited. All Rights Reserved.
 *
 * This Software is the proprietary information of Flytxt technologies Pvt Limited.
 * Use is subject to License terms.
 *
 */
package com.flytxt.commons.reporting.chart.entity;


import com.flytxt.commons.reporting.constants.ChartConstants.ChartType;
import com.flytxt.commons.reporting.parameter.objects.Parameter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * Entity class which keeps the chart configuration
 * @author Merrill George Paul (merrill.george@gmail.com)
 */
@Entity
@Table(name = "REP_FWK_CHART")
@NamedQueries(
{
    @NamedQuery(
            name = "ChartConfig.findAll",
            query = "SELECT c FROM ChartConfig c"),
    @NamedQuery(
            name = "ChartConfig.findByChartId",
            query = "SELECT c FROM ChartConfig c WHERE c.chartId = :chartId"),
    @NamedQuery(
            name = "ChartConfig.findByName",
            query = "SELECT c FROM ChartConfig c WHERE c.name = :name"),

    @NamedQuery(
            name = "ChartConfig.findByChartType",
            query = "SELECT c FROM ChartConfig c WHERE c.chartType = :chartType")
    })
public class ChartConfig implements Serializable {

    private static final long serialVersionUID = 1L;

    public static ChartConfig cloneForAll(ChartConfig config) {
        if(config==null)
            return null;
       ChartConfig chart = new ChartConfig();
       chart.setChartId(config.getChartId());
       chart.setChartType(config.getChartType());
       chart.setChartClass(config.getChartClass());
       chart.setDescription(config.getDescription());
       chart.setDrilldown(config.isDrilldown());
       chart.setChartQuery(config.getChartQuery());
       chart.setHeight(config.getHeight());
       chart.setWidth(config.getWidth());
       chart.setName(config.getName());
       chart.setShowLegend(config.isShowLegend());
       chart.setShowTitle(config.isShowTitle());
       chart.setXAxisLabel(config.getXAxisLabel());
       chart.setYAxisLabel(config.getYAxisLabel());
       Collection<ChartPromptParameters> promptParams =
               config.getChartPromptParameters();
       if(promptParams!=null){
           Collection<ChartPromptParameters> mainParams =
                   new ArrayList<ChartPromptParameters>();
           for(ChartPromptParameters promptParameter: promptParams){
               ChartPromptParameters eachParameter =  new ChartPromptParameters();
               eachParameter.setChartConfig(chart);
               eachParameter.setOrder(promptParameter.getOrder());
               Parameter p = Parameter.cloneAll(promptParameter.getParameter());

               eachParameter.setParameter(p);
               mainParams.add(eachParameter);
           }
           chart.setChartPromptParameters(mainParams);


       }
       return chart;
    }
  
    private Long chartId;
   
    private String name;
   
    private String description;
   
    private String chartType;
   
    private String chartQuery;
   
    private String chartClass;
   
    private Integer width;
   
    private Integer height;
   
    private String xAxisLabel;
   
    private String yAxisLabel;
   
    private boolean showLegend;
    
    private boolean showTitle;
    
    private boolean drilldown;

    
    private Collection<ChartPromptParameters> chartPromptParameters;

    public ChartConfig() {
    }

    public ChartConfig(Long chartId) {
        this.chartId = chartId;
    }

    @Id
    @Basic(optional = false)
    @SequenceGenerator(name = "REP_FWK_CHART_SEQ_GEN", sequenceName = "REP_FWK_CHART_SEQ",allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE,generator="REP_FWK_CHART_SEQ_GEN")
    //@GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "CHART_ID")
    public Long getChartId() {
        return chartId;
    }

    public void setChartId(Long chartId) {
        this.chartId = chartId;
    }

    @Column(name = "NAME",unique=true)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "DESCRIPTION")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "CHART_TYPE")
    public String getChartType() {
        return chartType;
    }

    public void setChartType(String chartType) {
        this.chartType = chartType;
    }

    @Transient
    public ChartType getChartTypeAsEnum(){
        return ChartType.valueOf(this.chartType);
    }

    public void setChartTypeAsEnum(ChartType chartTypeEnum){
        this.chartType = chartTypeEnum.name();
    }

    @Lob
    @Column(name = "CHART_QUERY")
    public String getChartQuery() {
        return chartQuery;
    }

    public void setChartQuery(String chartQuery) {
        this.chartQuery = chartQuery;
    }

    @Column(name = "CHART_CLASS")
    public String getChartClass() {
        return chartClass;
    }

    public void setChartClass(String chartClass) {
        this.chartClass = chartClass;
    }

    @Column(name = "WIDTH")
    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    @Column(name = "HEIGHT")
    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

     @Column(name = "X_AXIS_LABEL")
    public String getXAxisLabel() {
        return xAxisLabel;
    }

    public void setXAxisLabel(String xAxisLabel) {
        this.xAxisLabel = xAxisLabel;
    }

     @Column(name = "Y_AXIS_LABEL")
    public String getYAxisLabel() {
        return yAxisLabel;
    }

    public void setYAxisLabel(String yAxisLabel) {
        this.yAxisLabel = yAxisLabel;
    }

    @Column(name = "SHOW_LEGEND")
    public boolean isShowLegend() {
        return showLegend;
    }

    public void setShowLegend(boolean showLegend) {
        this.showLegend = showLegend;
    }

    @Column(name = "SHOW_TITLE")
    public boolean isShowTitle() {
        return showTitle;
    }

    public void setShowTitle(boolean showTitle) {
        this.showTitle = showTitle;
    }

    @Column(name = "IS_DRILLDOWN")
    public boolean isDrilldown() {
        return drilldown;
    }

    public void setDrilldown(boolean isDrilldown) {
        this.drilldown = isDrilldown;
    }

    @OneToMany(targetEntity=ChartPromptParameters.class,cascade = CascadeType.ALL, fetch=FetchType.LAZY)
    @JoinColumn(name = "CHART_ID",insertable=false,updatable=false)
    @OrderBy(value="order ASC")
    public Collection<ChartPromptParameters> getChartPromptParameters() {
        return chartPromptParameters;
    }

    public void setChartPromptParameters(Collection<ChartPromptParameters> chartPromptParametersCollection) {
        this.chartPromptParameters = chartPromptParametersCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (chartId != null ? chartId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ChartConfig)) {
            return false;
        }
        ChartConfig other = (ChartConfig) object;
        if ((this.chartId == null && other.chartId != null) || (this.chartId != null && !this.chartId.equals(other.chartId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.flytxt.commons.reporting.chart.entity.ChartConfig[chartId=" + chartId + "]";
    }

}

