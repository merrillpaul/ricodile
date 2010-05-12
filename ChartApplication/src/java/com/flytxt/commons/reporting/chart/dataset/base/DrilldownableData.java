package com.flytxt.commons.reporting.chart.dataset.base;

import com.flytxt.commons.reporting.chart.parameters.ChartDrillDownParameter; 
import com.flytxt.commons.reporting.chart.parameters.ReportDrilldownParameter; 

public abstract class DrilldownableData {

    private ChartDrillDownParameter chartDrillDownParameter;

    private ReportDrilldownParameter reportDrilldowParameter;

    public ChartDrillDownParameter getChartDrillDownParameter () {
        return chartDrillDownParameter;
    }

    public void setChartDrillDownParameter (ChartDrillDownParameter val) {
        this.chartDrillDownParameter = val;
    }

    public ReportDrilldownParameter getReportDrilldowParameter () {
        return reportDrilldowParameter;
    }

    public void setReportDrilldowParameter (ReportDrilldownParameter val) {
        this.reportDrilldowParameter = val;
    }

}

