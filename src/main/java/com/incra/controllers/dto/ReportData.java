package com.incra.controllers.dto;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The <i>ReportData</i> dto holds all information needed to render a chart on the report screen, and
 * as the basic for conversion into JSON.
 *
 * @author Jeffrey Risberg
 * @since 09/06/14
 */
public class ReportData {
    public Date fromDate;
    public Date toDate;
    public List<Map> dataPoints;

    public ReportData(Date fromDate, Date toDate, List<Map> dataPoints) {
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.dataPoints = dataPoints;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public List<Map> getDataPoints() {
        return dataPoints;
    }

    public void addPoint(String label, Double value) {
        Map map = new HashMap();
        map.put("x", label);
        map.put("y", value);

        dataPoints.add(map);
    }

    public String toString() {
        return "ReportData[" + dataPoints.size() + " points]";
    }
}
