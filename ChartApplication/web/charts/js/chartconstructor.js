/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 *  Document   : chart
    Created on : May 18, 2010, 3:41:06 PM
    Author     : vishnu.sankar
 */


 chartconstructor = function(){

     chartconstructor.superclass.constructor.call(this, {
        width:500,
        height:100,
        modal:true,
        border: false,
        bodyStyle:'padding:5px',
        layout:'form',
        html: []

      });
}
Ext.extend(chartconstructor, Ext.Panel, {

   prepareChart:function(chartName, hldItem){


                Ext.Ajax.request({
			   url: '../initChartConfig.do',
			   success:this.success ,
                           failure: this.failure,
                           scope:this,
                           hldItem: hldItem,
			   params: { dispatch:'initChart',chartName:chartName ,xy:new Date()}
			});

    },

    success:function(response,options){
        var result = Ext.util.JSON.decode(response.responseText);
        var fusionChart = null;

        if(result.validChart){
            this.removeAll();

            fusionChart = new fusionpanelcreator(result);            
            
            options.hldItem.removeAll();
            options.hldItem.add(fusionChart);
            options.hldItem.doLayout();
        }else{
           
             options.hldItem.removeAll();
             options.hldItem.add({html:'Invalid Chart Params', border: false});
             options.hldItem.doLayout();
        }
        this.fireEvent('closeWindow');

    },

    failure:function(response, options){
        if (response.status == -176464) {
	            FltMessage.show({
	                title: 'Communication Failure',
	                msg: 'Sorry, web server is not responding. try again!.',
	                buttons: Ext.MessageBox.OK,
	                icon: Ext.MessageBox.ERROR
	            });
	            return;
	        }
    }

});
