/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 *  Document   : chart
    Created on : May 18, 2010, 3:41:06 PM
    Author     : vishnu.sankar
 */

 windowpanel = function(){

  this.openChartWindow = new chartinitiator(this);
 //this.openChartWindow.on('insertChartPanel',this.insertChartPanel,this);

 windowpanel.superclass.constructor.call(this, {
  autoWidth:true,
  autoHeight:true,
  border:false,
  hideBorders:true,
  autoScroll:true,
  tbar:[             
                    new Ext.Toolbar.SplitButton({
                        text:'blah',
                        menu : {
                            items:[
                                {
                                text: '<b>Create Chart</b>',
                                handler:this.openChartInitWindow,
                                scope:this
                                },
                                 {
                                text: '<b>blah</b>'
                                }
                            ]
                        }
                    })
                    
                
            ]
            
  });
}
Ext.extend(windowpanel, Ext.Panel, {

    openChartInitWindow :function(){
       this.openChartWindow.show();
    }
//    insertChartPanel:function(fusionPanel){
//        this.openChartWindow.close();
//        this.add(fusionPanel);
//        this.doLayout();
//    }


});

