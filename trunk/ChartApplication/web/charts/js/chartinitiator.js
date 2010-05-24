/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 *  Document   : chart
    Created on : May 18, 2010, 3:41:06 PM
    Author     : vishnu.sankar
 */

 chartinitiator = function(parent){

  this.parent = parent;

  this.configpanel = new  chartconfigpanel();
  this.chartConstructor =  new chartconstructor();
  this.chartConstructor.on('closeWindow',this.closeWind,this);

  chartinitiator.superclass.constructor.call(this, {
        width:500,
        height:150,
        modal:true,
        frame:true,
        border:false,
        bodyStyle:'padding:5px',
        items:[this.configpanel],
        buttons:[
                 {
                    text: 'Blah',
                    handler:this.getChart,
                    scope:this
                }
               ]
  
  });
}
Ext.extend(chartinitiator, Ext.Window, {

    getChart: function(){      
       this.chartConstructor.prepareChart(this.configpanel.chartName.getValue(), this.parent);
    },
    closeWind:function(){
        this.close();
    }
   
});
