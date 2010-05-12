package com.flytxt.commons.reporting.chart.dataset.base;


public abstract class Set extends DrilldownableData {

    private String tooltipText;

    public String getTooltipText () {
        return tooltipText;
    }

    public void setTooltipText (String val) {
        this.tooltipText = val;
    }

}

