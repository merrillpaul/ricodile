 Ext.ns('Flytxt.charts');
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 *  Document   : chart
    Created on : May 18, 2010, 3:41:06 PM
    Author     : vishnu.sankar
 */

//TODO Class that intiate the FusionPanel and fire the fusion Panel ,
 
  Flytxt.charts.ChartInitiator= function(chartName){
        this.chartName = chartName;
        Flytxt.charts.ChartInitiator.superclass.constructor.call(this, { });
        this.prepareChart();
}
Ext.extend(Flytxt.charts.ChartInitiator, Ext.util.Observable, {

   prepareChart:function(){
                Ext.Ajax.request({
			   url: '../initChartConfig.do',
			   success:this.success ,
                           failure: this.failure,
                           scope:this,                           
			   params: { dispatch:'initChart',chartName:this.chartName ,xy:new Date()}
			});

    },

    success:function(response,options){
        var result = Ext.util.JSON.decode(response.responseText);
        
        if(result.validChart){




           var  fusionChart = new Flytxt.charts.ActualFusionPanel(result,{region:'center'});

           var promptablePanel =  new Ext.Panel(
            {
                region:'east',
                title:'Parameters',
                collapsed:true,
                width:150,
                collapsible:true,
                html:'<i>Promps</i>'
                
            }
        );
           var targetPanel = new Ext.Panel({

              layout:'border',
               autoWidth:true,
               height:fusionChart.chartHeight,
               items:[
                   fusionChart,
                    promptablePanel
               ]

           });

           targetPanel.on('startrefresh',fusionChart.startRefresh,fusionChart);
           
            this.fireEvent('chartready',targetPanel);
        }else{
          alert('Invalid Charts !Please check the configurations');

        }   
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
