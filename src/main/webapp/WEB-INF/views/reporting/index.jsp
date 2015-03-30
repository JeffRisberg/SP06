<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<style>
    .list {
        margin: 15px 0px;
    }
</style>

<link href="<c:url value="/resources/styles/nv.d3.css" />" rel="stylesheet">

<script type="text/javascript" src="<c:url value="/resources/javascript/d3.js" />" charset="utf-8"></script>
<script type="text/javascript" src="<c:url value="/resources/javascript/nv.d3.js" />" charset="utf-8"></script>

<c:if test="${flashMessage != null}">
    <div class="message">${flashMessage}</div>
</c:if>

<h3>Reporting</h3>

<style>
    #chart1 svg {
        height: 300px;
        width: 800px;
    }
</style>

<div class="row">
    <div class="col-md-2">
        <form:form id="analyticForm">
            <h4>Report Type</h4>
            <form:select path="reportType" id="reportTypeOption">
                <form:options items="${reportTypeList}" itemsValue="value" itemLabel="name"/>
            </form:select>
            <h4>Dimension</h4>
            <form:select path="dimension" id="dimensionOption">
                <form:options items="${dimensionList}" itemValue="id" itemLabel="name"/>
            </form:select>
            <br/>
            <h4>Measure</h4>
            <form:select path="measure" id="measureOption">
                <form:options items="${measureList}" itemValue="id" itemLabel="name"/>
            </form:select>
        </form:form>
    </div>
    <div class="col-md-10" id="chart1">
        <svg></svg>
    </div>
</div>

<script type="text/javascript">
    $(document).ready(function () {
        loadReportData();

        $('#reportTypeOption').bind('change', function () {
            loadReportData();
        });
        $('#dimensionOption').bind('change', function () {
            loadReportData();
        });
        $('#measureOption').bind('change', function () {
            loadReportData();
        });
    });

    var reportData = null;

    function loadReportData() {
        var reportTypeKey = $('#reportTypeOption').val();
        var dimensionId = $('#dimensionOption').val();
        var measureId = $('#measureOption').val();

        console.log(dimensionId);

        $.getJSON("reporting/getData", { id: 1, reportType: reportTypeKey, dimension: dimensionId, measure: measureId }, function (data) {
            reportData = data;
            redraw();
        });
    }

    function redraw() {
        var minValue = Number.MAX_VALUE;
        var maxValue = Number.MIN_VALUE;

        reportData.dataPoints.forEach(function (valuePair) {
            var value = valuePair.y;

            minValue = Math.min(value, minValue);
            maxValue = Math.max(value, maxValue);
        });

        nv.addGraph(function () {
            var chart;

            chart = nv.models.multiBarChart()
                    .showControls(false)   //Don't let user switch between 'Grouped' and 'Stacked' mode.
                    .reduceXTicks(false);   //If 'false', every single x-axis tick label will be rendered.

            chart.yAxis
                    .tickFormat(d3.format(',.1f'));

            $('#chart1 svg').empty();

            var datum = new Array();
            var series = new Array();
            series['yAxis'] = reportData.axisIndex;
            series['key'] = reportData.key;
            series['values'] = reportData.dataPoints;
            datum.push(series);

            d3.select('#chart1 svg')
                    .datum(datum)
                    .transition().duration(500)
                    .call(chart);

            nv.utils.windowResize(chart.update);

            return chart;
        });
    }
</script>