package com.flytxt.commons.reporting.parameter.objects;

 

import com.flytxt.commons.reporting.parameter.ParameterConstants.Type;
import com.flytxt.commons.reporting.parameter.ParameterConstants.ValueClassType;
import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity
@Table(name = "REP_FWK_PARAMATER_MST")
@NamedQueries(
    {
        @NamedQuery(name = "Parameter.findAll",
                    query = "SELECT p FROM Parameter p"),
        @NamedQuery(name = "Parameter.findByParameterId",
                    query = "SELECT p FROM Parameter p WHERE p.id = :parameterId"),
        @NamedQuery(name = "Parameter.findByParameterName",
                    query = "SELECT p FROM Parameter p WHERE p.parameterName = :parameterName"),
        @NamedQuery(name = "Parameter.findByParameterType",
                    query = "SELECT p FROM Parameter p WHERE p.parameterType = :parameterType"),
        @NamedQuery(name = "Parameter.findByClassType",
                    query = "SELECT p FROM Parameter p WHERE p.classType = :classType")

})

public class Parameter implements Serializable {

    private transient Collection<ParameterValue> parameterValues;

   

   private static final long serialVersionUID = 1L;

   
    private Long id;


  
    private String parameterName;


 
    private String parameterType;


  
    private String classType;

   
    private String data;

   
    private String description;

   
    private boolean multiselect;

    
    private String defaultValue;


    @Column(name = "CLASS_TYPE", nullable = false, length = 2)
    public String getClassType() {
        return classType;
    }

    public void setClassType(String classType) {
        this.classType = classType;
    }

    @Column(name = "DATA", length = 4000)
    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Column(name = "DEFAULT_VALUE", length = 4000)
    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    @Column(name = "DESCRIPTION", length = 4000)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Id
    @SequenceGenerator(name = "REP_PARAMETER_SEQ_GEN", sequenceName = "REP_PARAMETER_SEQ",allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE,generator="REP_PARAMETER_SEQ_GEN")
    //@GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "PARAMETER_ID", nullable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    @Column(name = "MULTISELECT")
    public boolean isMultiselect() {
        return multiselect;
    }

    public void setMultiselect(boolean multiselect) {
        this.multiselect = multiselect;
    }

    @Column(name = "PARAMETER_NAME", nullable = false, length = 500, unique=true)
    public String getParameterName() {
        return parameterName;
    }

    public void setParameterName(String parameterName) {
        this.parameterName = parameterName;
    }

    @Column(name = "PARAMETER_TYPE", nullable = false, length = 2)
    public String getParameterType() {
        return parameterType;
    }

    public void setParameterType(String parameterType) {
        this.parameterType = parameterType;
    }

   

    

    public Parameter () {
    }

   


    public Parameter(Long parameterId) {
        this.id = parameterId;
    }

    public Parameter(Long parameterId, String parameterName, String parameterType, String classType) {
        this.id = parameterId;
        this.parameterName = parameterName;
        this.parameterType = parameterType;
        this.classType = classType;
    }
   
   

    /**
     *  <p style="margin-top: 0">
     *        Parameter values are set after they are calculated based on a report 
     *        context or chart context. 
     *  <br>
     *      </p>
     */
    @Transient
    public Collection<ParameterValue> getParameterValues () {
        return parameterValues;
    }

    
    public void setParameterValues (Collection<ParameterValue> val) {
        this.parameterValues = val;
    }

    @Transient
     public ValueClassType getValueClassType() {
        return ValueClassType.findByKey(this.classType);
    }

   
    public void setValueClassType(ValueClassType classType) {
        this.classType = classType.getKey();
    }

    @Transient
    public Type getType() {
        return Type.findByKey(this.parameterType);
    }

   
    public void setType(Type type) {
        this.parameterType = type.getKey();
    }




     @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Parameter)) {
            return false;
        }
        Parameter other = (Parameter) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return
                "com.flytxt.commons.reporting.parameter.objects.Parameter[id="
                + id + "]";
    }

    public static Parameter cloneAll(Parameter p){
       
        Parameter p1 = new Parameter();
        p1.setClassType(p.getClassType());
        p1.setData(p.getData());
        p1.setDefaultValue(p.getDefaultValue());
        p1.setDescription(p.getDescription());
        p1.setId(p.getId());
        p1.setMultiselect(p.isMultiselect());
        p1.setParameterName(p.getParameterName());
        p1.setParameterType(p.getParameterType());
        return p1;
    }

}

