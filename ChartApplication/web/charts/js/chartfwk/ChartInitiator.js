 Ext.ns('Flytxt.charts');
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 *  Document   : chart
    Created on : May 18, 2010, 3:41:06 PM
    Author     : vishnu.sankar
 */

//TODO Class that intiate the FusionPanel and fire the fusion Panel ,
/**
 * @param chartName
 * @param initParams
 * <code>
 * As an array
 * 	[
 * 		{
 * 			name:'APPLICATION_INSTANCE_ID_PARAM',
 * 			type:'i' // look into com.flytxt.commons.reporting.parameter.ParameterConstants.ValueClassType
 * 			value:'23423423'
 * 		},
 * 		{
 * 			name:'SOME_OTHER_INIT_PARAM',
 * 			type:'s',
 * 			value:'SOme String'
 * 		}
 * 	]
 *
 * or
 *
 * As an object
 * {
 * 			name:'APPLICATION_INSTANCE_ID_PARAM',
 * 			type:'i' // look into com.flytxt.commons.reporting.parameter.ParameterConstants.ValueClassType
 * 			value:'23423423'
 * }
 * </code>
 */
Flytxt.charts.ChartInitiator= function(chartName,initParams, holderPanel/*optional*/){
        this.chartName = chartName;
        this.initParams = initParams===undefined?null:initParams;
        this.holderPanel = holderPanel;
        Flytxt.charts.ChartInitiator.superclass.constructor.call(this, { });
        this.prepareChart();
}
Ext.extend(Flytxt.charts.ChartInitiator, Ext.util.Observable, {




	prepareChart:function(){

			var params = {
					dispatch:'initChart',chartName:this.chartName ,xy:new Date().getTime()
			};

			if( this.initParams!=null){
				var _initParams = this.initParams;
				if(Ext.isArray(_initParams)){
					for(var t=0,len=_initParams.length;t<len;t++){
						var initPrefix = "initParams["+t+"].";
						params[initPrefix+"name"]=_initParams[t].name;
						params[initPrefix+"type"]=_initParams[t].type;
						params[initPrefix+"value"]=_initParams[t].value;
					}
				}else{
					params['initParams[0].name'] = _initParams.name;
					params['initParams[0].type'] = _initParams.type;
					params['initParams[0].value'] = _initParams.value;
				}
			}
			Ext.Ajax.request({
			   url: '../initChartConfig.do',
			   method:'POST',
			   success:this.success ,
               failure: this.failure,
               scope:this,
			   params: params
			});

    },

    success:function(response,options){

    	try{
        var result = Ext.util.JSON.decode(response.responseText);

        if(result.validChart){




           var  fusionChart = new Flytxt.charts.ActualFusionPanel(result,{});

		   var chartItems=[fusionChart];
		   if(result.promptParameters!==undefined && result.promptParameters.length>0){

			 var promptablePanel =  new Ext.Panel(
            {
                region:'east',
                title:'Parameters',
                collapsed:true,
                width:150,
                collapsible:true,
                html:'<i>Prompts</i>'

            }
        	);
			chartItems.push(promptablePanel);
		   }

          this.zoomCotrl =  new Ext.Slider({

				        width: 100,
						disabled:true,
				        minValue: -50,
						increment:10,
						value:0,
				        maxValue: 150,
				        plugins: new Ext.ux.SliderTip(),
						listeners:{
							changecomplete:this.onZoom.createDelegate(this,[fusionChart],true),
							scope:this
						}
				    });

		   var targetPanleParams = {

               autoWidth:true,
			   border:true,

			   autoScroll:true,
               height:result.chartInfo.height,
               items:chartItems,
			   tbar:[
		              {
		                  text:'Refresh Chart',
					       iconCls:'refresh_now',
		                  handler:fusionChart.refreshFlyChart,
		                  scope:fusionChart
		              },
					  ('-'),
					  ('Zoom:'),
					 this.zoomCotrl
		          ]


			   };


		   fusionChart.on('nodatatodisplay',this.onNodatatoDisplay,this);
		   fusionChart.on('dataloaderror',this.onNodataLoadEror,this);
		   fusionChart.on('dataloaded',this.onNodataLoaded,this);




           this.targetPanel = new Ext.Panel(targetPanleParams);

           this.targetPanel.on('startrefresh',fusionChart.startRefresh,fusionChart);

            this.fireEvent('chartready',this.targetPanel);

			if(typeof this.holderPanel == "string"){
				this.holderPanel = Ext.getCmp(this.holderPanel);
			}

            if(this.holderPanel!==undefined && this.holderPanel!=null){
            	this.holderPanel.add(this.targetPanel);
            	this.holderPanel.doLayout();



            }
        }else{

        	  this.fireEvent('invalidchart');
        	  if(this.holderPanel!==undefined && this.holderPanel!=null){
             	this.holderPanel.add(new Ext.Panel({ autoWidth:true,border:false,bodyStyle:'margin-left:5px;margin-top:5px;',html:'<span style="color:maroon;font-weight:bold"><i>Invalid chart provided. Please check configuration or contact system administrator</i></span>'}));
             	this.holderPanel.doLayout();
             }
        }
     }catch (e) {

            //FlySessionTimeoutHandler(response);
           // return;
        }

    },

	onNodatatoDisplay:function(){
		this.zoomCotrl.disable();
	},

	onNodataLoadEror:function(){
		this.zoomCotrl.disable();
	},

	onNodataLoaded:function(){
		 this.zoomCotrl.enable();
	},

    failure:function(response, options){
        if (response.status == -176464) {
	            Ext.Message.show({
	                title: 'Communication Failure',
	                msg: 'Sorry, web server is not responding. try again!.',
	                buttons: Ext.MessageBox.OK,
	                icon: Ext.MessageBox.ERROR
	            });
	            return;
	        }
    },

	onZoom:function(sliderCtrl,sliderValue,fusionChart){


		var factor=100;

		factor=(factor+sliderValue)/100;
		fusionChart.setWidth(fusionChart.chartWidth*factor);
		fusionChart.setHeight(fusionChart.chartHeight*factor);



	}

});
