/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 *  Document   : chart
    Created on : May 18, 2010, 3:41:06 PM
    Author     : vishnu.sankar
 */

 chartconfigpanel = function(){
  
  this.chartName = new Ext.form.TextField({
        fieldLabel:'<b>Chart Name</b>',
        anchor:'98%'
   });


 chartconfigpanel.superclass.constructor.call(this, {
      border:false,
      frame:true,
      hideBorders:true,
      layout:'form',
      items:[ this.chartName]

  });
}
Ext.extend(chartconfigpanel, Ext.Panel, {




});

