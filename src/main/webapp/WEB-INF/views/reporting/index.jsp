<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<style>
  .list { margin: 15px 0px; }
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
        width: 600px;
    }
</style>

<select id="direction" name="direction">
    <option>Vertical</option>
    <option>Horizontal</option>
</select>

<input type="checkbox" id="includeZero">Include Zero

<div id="chart1">
    <svg></svg>
</div>

<script type="text/javascript">
    $(document).ready(function () {

        var direction = "Vertical";
        var includeZero = false;
        var accountsList = [];

        $("#direction").on("change", function (e) {
            direction = $('#direction').val();

            if (accountsList.length > 0) {
                redraw();
            }
        });

        $("#includeZero").on("change", function (e) {
            includeZero = $('#includeZero').is(':checked');

            if (accountsList.length > 0) {
                redraw();
            }
        });

        d3.json("resources/accounts.json", function (error, data) {

            accountsList = data;
            redraw();
        });

        function redraw() {
            var minValue = Number.MAX_VALUE;
            var maxValue = Number.MIN_VALUE;

            accountsList.forEach(function (account) {
                account.values.forEach(function (valuePair) {
                    var value = valuePair.y;

                    minValue = Math.min(value, minValue);
                    maxValue = Math.max(value, maxValue);
                })
            });

            nv.addGraph(function () {
                var chart;

                if (direction == "Vertical") {
                    chart = nv.models.multiBarChart()
                            .showControls(false)   //Don't let user switch between 'Grouped' and 'Stacked' mode.
                            .reduceXTicks(false);   //If 'false', every single x-axis tick label will be rendered.
                }
                else {
                    chart = nv.models.multiBarHorizontalChart()
                            .showControls(false)   //Don't let user switch between 'Grouped' and 'Stacked' mode.
                            .showValues(true);
                }

                if (includeZero) {
                    chart.forceY([0.0]);
                }
                else {
                    chart.forceY([minValue * 0.97, maxValue]);
                }

                chart.yAxis
                        .tickFormat(d3.format(',.1f'));

                $('#chart1 svg').empty();

                d3.select('#chart1 svg')
                        .datum(accountsList)
                        .transition().duration(500)
                        .call(chart);

                nv.utils.windowResize(chart.update);

                return chart;
            });
        }
    });
</script>