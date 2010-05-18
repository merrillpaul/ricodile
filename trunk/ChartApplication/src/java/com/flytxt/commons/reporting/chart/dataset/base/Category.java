package com.flytxt.commons.reporting.chart.dataset.base;


/**
 *  <p style="margin-top: 0">
 *        Base class for all category classes. support for setting label and 
 *        tooltip is by default
 *      </p>
 */
public abstract class Category extends DrilldownableData {

    protected String label;

    private String tooltipText;

    public String getLabel () {
        return label;
    }

    public void setLabel (String val) {
        this.label = val;
    }

    public String getTooltipText () {
        return tooltipText;
    }

    public void setTooltipText (String val) {
        this.tooltipText = val;
    }

}

