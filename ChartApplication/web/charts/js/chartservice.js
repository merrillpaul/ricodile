/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 *  Document   : chart
    Created on : May 18, 2010, 3:41:06 PM
    Author     : vishnu.sankar
 */

 chartservice = function(){



 chartservice.superclass.constructor.call(this, {
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

    getChart:function(){

        	 Ext.Ajax.request({
			   url: '../initChartConfig.do?',
			   success:this.success ,
                           failure: this.failure,
                           scope:this,
			   params: { dispatch:'initChart',chartName:this.configpanel.chartName.getValue() }
			});
    },
    
    success:function(response,options){
        result = Ext.util.JSON.decode(response.responseText);

        var fusionPanel  = new chartconstructorpanel(result);
        this.fireEvent('insertChartPanel',fusionPanel);

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
