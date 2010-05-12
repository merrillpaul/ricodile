package com.flytxt.commons.reporting.chart.dataset.base.pie;

import com.flytxt.commons.reporting.chart.dataset.base.Set; 

public class PieSet extends Set {

    public PieSet () {
    }

    private String label;
    private String value;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    

}

