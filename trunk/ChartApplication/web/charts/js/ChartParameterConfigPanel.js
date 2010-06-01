 Ext.ns('Flytxt.charts');
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 *  Document   : chart
    Created on : May 18, 2010, 3:41:06 PM
    Author     : vishnu.sankar
 */
 //TODO Parameters that needs to pass to servers for generationg  charts.eg:'Chart Name : MERRIL COLUMN etc'

 Flytxt.charts.ChartParameterConfigPanel = function(){  
  this.chartName = new Ext.form.TextField({
        fieldLabel:'<b>Chart Name</b>',
        anchor:'98%'
   });
 Flytxt.charts.ChartParameterConfigPanel.superclass.constructor.call(this, {
      border:false,
      frame:true,
      hideBorders:true,
      layout:'form',
      items:[ this.chartName]

  });
}
Ext.extend(Flytxt.charts.ChartParameterConfigPanel, Ext.Panel, {
});

