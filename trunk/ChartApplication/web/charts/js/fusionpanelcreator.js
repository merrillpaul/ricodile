/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */



 fusionpanelcreator  = function(result){


     this.swfType = result.frameworkAttrs.fusionSWFFile;
     this.chartTitle = result.chartInfo.title;
     this.chartHeight = result.chartInfo.height;
     this.chartWidth = result.chartInfo.width;
     this.chartName = result.chartInfo.chartName;
     this.chartRunId = result.chartRunSessionId;

     var url="../chartData.do?dispatch=run&chartRunId="+this.chartRunId;
     url =  encodeURI(url);
                     

 fusionpanelcreator.superclass.constructor.call(this, {
        modal:true,
        bodyStyle:'padding:5px',
        chartCfg   :{
                                id   : Ext.id()
                         },
          chartURL   : '../ext/fusionMedia/swf/'+this.swfType+'.swf',
          dataURL   : url,
          autoMask : true,
          width:this.chartWidth,
          height: this.chartHeight

  });
}


Ext.extend(fusionpanelcreator, Ext.ux.Chart.Fusion.Panel, {

   

});
