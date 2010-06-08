 Ext.ns('Flytxt.charts');
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 *  Document   : chart
    Created on : May 18, 2010, 3:41:06 PM
    Author     : vishnu.sankar
 */

//TODO Pop Window through which we send the parameter to the constructor of teh FUSION Panel
 Flytxt.charts.ChartCreaterWindow= function(parent){
  this.parent = parent;
  this.configpanel = new  Flytxt.charts.ChartParameterConfigPanel();
  this.chartConstructor = null;
  Flytxt.charts.ChartCreaterWindow.superclass.constructor.call(this, {
        width:500,
        height:150,
        modal:true,
        frame:true,
        border:false,
        bodyStyle:'padding:5px',
        items:[this.configpanel],
        buttons:[
                 {
                    text: 'Get Chart',
                    handler:this.getChart,
                    scope:this
                }
               ]
  
  });
}
Ext.extend(Flytxt.charts.ChartCreaterWindow, Ext.Window, {
    getChart: function(){        
     new Flytxt.charts.ChartInitiator(this.configpanel.chartName.getValue(),{name:'APPLICATION_INSTANCE_PARAM_ID',value:'16522',type:'i'}).on('chartready',this.addFusionchart,this);


    },
    addFusionchart:function(panel){
        this.parent.add(panel);
        this.parent.doLayout();
        panel.fireEvent('startrefresh',[20])
        this.close();

    }
   
});
