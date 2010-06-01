/*
 * @(#) $Id: 
 * Copyright Flytxt Technologies Pvt Limited. All Rights Reserved.
 *
 * This Software is the proprietary information of Flytxt technologies Pvt Limited.
 * Use is subject to License terms.
 *
 */
package com.flytxt.commons.reporting.constants;

/**
 * Contains constants for use in chart creation, generation and rendering
 * @author Merrill George (merrill.george@gmail.com)
 */
public final class ChartConstants {

    /**
     *
     * Enumerates the different charts supported in Flytxt
     */
    public enum ChartType {

        /**
         *  <p style="margin-top: 0">
         *        Typically bar charts which have just a single series. Bars grow 
         *        vertically
         *      </p>
         */
        COLUMN("Simple Vertical Column Chart"),
        /**
         *  <p style="margin-top: 0">
         *        Pie chart with pieces
         *      </p>
         */
        PIE("Pie Chart"),
        /**
         *  <p style="margin-top: 0">
         *        Horizontaly flowing column charts. Single Series
         *      </p>
         */
        BAR("Simple Horizontal Bar Chart"),
        /**
         *  <p style="margin-top: 0">
         *        Line represent a line with corodinate points
         *      </p>
         */
        LINE("Line Chart"),
        
        /**
         * doughnut type of chart which uses the same config for a pie chart
         */
        RING("Ring/Doughnut Chart"),
        /**
         *  <p style="margin-top: 0">
         *        SHows an area spread
         *      </p>
         */
        AREA("Simple Area Chart"),
        /**
         *  <p style="margin-top: 0">
         *        Multiple series ( multiple columns for a single category)
         *      </p>
         */
        MULTI_SERIES_COLUMN("Multi Series Vertical Bar Chart"),
        /**
         *  <p style="margin-top: 0">
         *        Multiple series of line for same cartegroeies
         *      </p>
         */
        MULTI_SERIES_LINE("Multi Series Line Chart"),
        /**
         *  <p style="margin-top: 0">
         *        Mutliple series of horizontal bar for same set of categories
         *      </p>
         */
        MULTI_SERIES_BAR("Multi Series Horizontal Bar Chart"),
        /**
         *  <p style="margin-top: 0">
         *        Mutlplie series area chart for same set of categories
         *      </p>
         */
        MULTI_SERIES_AREA("Multi Series Area Chart"),
        /**
         *  <p style="margin-top: 0">
         *        Multiple Series for  same set of categories stacked on top of each other 
         *        in a vertical column/bar chart layout
         *      </p>
         */
        STACKED_SERIES_COLUMN("Stacked Vertical Bar Chart"),
        /**
         *  <p style="margin-top: 0">
         *        Multiple Series for same set of categories stacked on top of each other 
         *        in an area chart
         *      </p>
         */
        STACKED_SERIES_AREA("Stacked Area Chart"),
        /**
         *  <p style="margin-top: 0">
         *        Multiple Series for same set of categories stacked on top of each other 
         *        in a horizontal column/bar chart layout
         *      </p>
         */
        STACKED_SERIES_BAR("Stacked Horizontal Bar Chart"),
        /**
         * scatter chart
         */
        XY_SCATTER("Scatter Chart"),
        /**
         * bubble charts
         */
        BUBBLE("Bubble Chart"),
        /**
         *  <p style="margin-top: 0">
         *        Refer http://www.fusioncharts.com/docs/Contents/ChartSS/Col3DLine.html
         *      </p>
         */
        COLUMN_LINE_SINGLE_Y_COMBO("Column and Line with Single Y"),
        /**
         *  <p style="margin-top: 0">
         *        Refer 
         *        http://www.fusioncharts.com/docs/Contents/ChartSS/MSCol3DLineDY.html
         *      </p>
         */
        COLUMN_LINE_DUAL_Y_COMBO("Column and Line with Dual Y"),
        /**
         *  <p style="margin-top: 0">
         *        Refer 
         *        http://www.fusioncharts.com/docs/Contents/ChartSS/StCol3DLineDY.html
         *      </p>
         */
        STACKED_COLUMN_DUAL_Y_COMBO("Stacked Dual Column Chart"),
        /**
         * any combo chart TODO
         */
        SINGLE_Y_ANY_COMBO("Single Y Any Chart");

        private String description;

        private ChartType(String description){
            this.description =  description;
        }

        public String getDescription(){
            return this.description;
        }
    }

    /**
     *  <p style="margin-top: 0">
     *        Represents the target UI widget to execute for a drilldown request from 
     *        user
     *      </p>
     */
    public enum DrilldownTarget {

        /**
         *  <p style="margin-top: 0">
         *        Shows a Chart
         *      </p>
         */
        CHART,
        /**
         *  <p style="margin-top: 0">
         *        Control to a report screen
         *      </p>
         */
        REPORT,
        /**
         *  <p style="margin-top: 0">
         *        Shows a Scree. Maybe an Ext Screen or Web page etc
         *      </p>
         */
        SCREEN,
        /**
         *  <p style="margin-top: 0">
         *        Initiates a server action maybe done asynchnronously or else
         *      </p>
         */
        SERVER_ACTION;
    }

    /**
     *  <p style="margin-top: 0">
     *        Represents the drill down target mode. Ie. in case of a screen where it 
     *        should come and incase of a server action, should the UI wait for the 
     *        response or not
     *      </p>
     */
    public enum DrilldownTargetMode {

        /**
         *  <p style="margin-top: 0">
         *        In the same page(awt/swing panel) the target is shown in another  panel
         *      </p>
         */
        INLINE,
        /**
         *  <p style="margin-top: 0">
         *        Opens into a new browser or (any awt/swing) window
         *      </p>
         */
        NEW_BROWSER_WINDOW,
        /**
         *  <p style="margin-top: 0">
         *        Renders the drilldown target into the component ID
         *      </p>
         */
        COMPONENT_ID,
        /**
         * asynchronous server request
         */
        ASYNC,
        /**
         * synchronous server request. waits for request and if required
         * shows message or status
         */
        WAIT;
    }

    /**
     *  <p style="margin-top: 0">
     *        Represents the orientation of a chart. Typically used for Bar/Column 
     *        Type charts
     *      </p>
     * @deprecated
     */
    public enum Orientation {

        /**
         *
         */
        VERTICAL,
        /**
         *
         */
        HORIZONTAL;
    }

    /**
     *  <p style="margin-top: 0">
     *        Basically enumerates the different renderers available. Those renderers 
     *        render the same flytxt chart definition differently
     *      </p>
     */
    public enum ChartRendererType {

        /**
         * <p style="margin-top: 0">
         * Fusion chart'able' charts while rendering would return an XML 
         * as defined in http://www.fusioncharts.com. Typiically acted in a
         * web UI , where the chart is loaded as a Flash panel in the webbrowser
         * </p>
         */
        FUSION("text/xml","com.flytxt.commons.reporting.chart.generator.fusioncharts.FusionChartGeneratorFactory"),
        /**
         * <p style="margin-top: 0">
         * This would make use of JFREE chart libraries. Typically for server side 
         * chart generation into an encoded image. This can also me used as an 
         * image in the web ui layer with image maps. But this pales in comparison 
         * to the look and feel of the chart with Fusion or OFC chart API. This can 
         * be used in AWT components also. Hwoever typically the use of this in 
         * Flytxt NEON would be restricted only for server side generation of chart 
         * images
         * </p>
         */
        JFREE("image/png","com.flytxt.commons.reporting.chart.generator.jfree.JFreeChartGeneratorFactory");

        private ChartRendererType(String contentType,String generatorClassName){
            this.contentType = contentType;
            this.generatorClassName = generatorClassName;
        }

        private String contentType;
        private String generatorClassName;

        public String getGeneratorClassName() {
            return generatorClassName;
        }

        public String getContentType() {
            return contentType;
        }
        


    }


    public interface ChartJsonConstants{

        String CHART_RUN_SESSION_ID = "chartRunSessionId";
        String CHART_ID = "chartId";
        String CHART_FRAMEWORK_ATTRS = "frameworkAttrs";
        String CHART_INFO = "chartInfo";
        String CHART_FOUND="validChart" ;
    }


    public enum ChartResultSetConstants{
        SERIESNAME,
        SERIESVALUE,
        DRILLDOWN;
    }
}

