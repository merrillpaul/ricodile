/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 *  Document   : chart
    Created on : May 18, 2010, 3:41:06 PM
    Author     : vishnu.sankar
 */
Ext.onReady(function(){
var button = Ext.get('show-btn');
var  basePanel = new  windowpanel();
    button.on('click', function(){
        var win = new Ext.Window({
            title: 'Report Winodw',
            closable:true,
            width:800,
            height:650,
            plain:true,
	    layout:'fit',
	    modal:true,
            items: [basePanel]
        });
        win.show(this);
    });
});