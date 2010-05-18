<%-- 
    Document   : chartConfigTest
    Created on : May 4, 2010, 9:10:02 AM
    Author     : merrill.paul
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>


<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <html:form action="initChartConfig">
            <html:hidden property="dispatch" value="initChart"/>
            <table>
                <tr>
                    <td>Chart Id</td>
                    <td><input type="text" name="chartName"/></td>
                </tr>
                
                 <tr>
                    <td>Chart Init Params</td>
                    <td>
                       
                        <table>
                            <tr>

                                <td>Property Name</td>
                                <td><input type="text" name="initParams[0].name" value="empName"/></td>


                                  <td>Property Type</td>
                                <td><input type="text" name="initParams[0].type"/></td>

                                <td>Property Value</td>
                                <td><input type="text" name="initParams[0].value"/></td>


                            </tr>


                           
                        </table>
                        
                    </td>
                </tr>
                
            </table>
            <html:submit />
        </html:form>

    </body>
</html>
