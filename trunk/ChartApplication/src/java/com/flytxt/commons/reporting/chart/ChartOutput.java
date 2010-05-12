package com.flytxt.commons.reporting.chart;


public class ChartOutput  {

    /**
     *  <p style="margin-top: 0">
     *        Contains the content of the chart , may it be in JSOn, or XML format ( 
     *        as Fusion charts) or byte[] data of PNG or JPEg
     *      </p>
     */
    private byte[] content;

    private String contentType;

    public ChartOutput () {
    }

    public byte[] getContent () {
        return content;
    }

    public void setContent (byte[] val) {
        this.content = val;
    }

    public String getContentType () {
        return contentType;
    }

    public void setContentType (String val) {
        this.contentType = val;
    }

}

