Ext.ns('Flytxt.charts');
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 *  Document   : chart
    Created on : May 18, 2010, 3:41:06 PM
    Author     : vishnu.sankar
 */
//TODO Creating actual Fusion Panel for  which can embed anywhere 
 Flytxt.charts.ActualFusionPanel  = function(result,extraConfig){
     this.swfType = result.frameworkAttrs.fusionSWFFile;
     this.chartTitle = result.chartInfo.title;
     this.chartHeight = result.chartInfo.height;
     this.chartWidth = result.chartInfo.width;
     this.chartName = result.chartInfo.chartName;
     this.chartRunId = result.chartRunSessionId;
     var url="../chartData.do?dispatch=run&chartRunId="+this.chartRunId;
     url =  encodeURI(url);
     this.flytxtChartUrl = url;


     var initConfig = { bodyStyle:'padding:5px',
        chartCfg   :{
                                id   : Ext.id()/*,
                                chartWidth  :this.chartWidth+10,
                                chartHeight : this.chartHeight+10*/

                         },
          chartURL   : '../ext/fusionMedia/swf/'+this.swfType+'.swf',
          dataURL   : this.flytxtChartUrl ,
          autoMask : true,
          width:this.chartWidth,
          height: this.chartHeight,
          tbar:[
              {
                  text:'Refresh',
                  handler:this.refreshFlyChart,
                  scope:this
              }
          ]
     };
    Ext.apply(initConfig, extraConfig);
    Flytxt.charts.ActualFusionPanel.superclass.constructor.call(this, initConfig);
    this.on('beforedestroy', this.cleanUpChart, this);


}
Ext.extend(Flytxt.charts.ActualFusionPanel, Ext.ux.Chart.Fusion.Panel, {
    
    refreshFlyChart:function(){
       // this.setChartDataURL(this.flytxtChartUrl);
        Ext.Ajax.request({
			   url: this.flytxtChartUrl,
			   params: { xy:new Date().getTime() },
                           success:this.onChartXMLSuccess,
                           scope:this
			});

    },

    onChartXMLSuccess: function(response){
       this.setChartData(response.responseText,true);
    },

    startRefresh:function(refreshPeriod/* seconds*/){
      //   this.stopRefresh();
      //    if(refreshPeriod ===undefined){
      //       refreshPeriod = 30;
     //    }
     //    this.timerDelayer =   new Ext.util.DelayedTask(this.startTimerThread.createDelegate(this,[refreshPeriod],false),this);
	// this.timerDelayer.delay(1000*refreshPeriod);


       
        
    },


    startTimerThread:function(refreshPeriod){
    //     this.timeThreadRunner = new Ext.util.TaskRunner();

       //  this.timeThread =  this.timeThreadRunner.start({
               //     run : this.refreshFlyChart ,
               //     interval : refreshPeriod*1000,
               //     scope: this
      //   });
    },


    stopRefresh: function(){
        if (this.timeThread != null) {
		this.timeThreadRunner.stop(this.timeThread);
	}
	this.timeThread = null;
        if (this.timerDelayer != null) {
				this.timerDelayer.cancel();
	}
	
    },

    cleanUpChart: function(){
        this.stopRefresh();
        Ext.Ajax.request({
			   url: '../cleanupChart.do',
			   params: { dispatch:'clean',chartRunId:this.chartRunId }
			});
        }
});
