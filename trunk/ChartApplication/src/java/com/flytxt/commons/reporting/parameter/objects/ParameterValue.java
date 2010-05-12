package com.flytxt.commons.reporting.parameter.objects;


public class ParameterValue {

    private String description;

    private Integer id;

    private Object value;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public ParameterValue () {
    }

    public String getDescription () {
        return description;
    }

    public void setDescription (String val) {
        this.description = val;
    }

  

  
}

