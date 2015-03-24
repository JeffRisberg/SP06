package com.incra.controllers.dto;

import java.sql.Date;
import java.util.ArrayList;
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
    protected int axisIndex;
    protected String key;
    protected Date fromDate;
    protected Date toDate;
    protected List<Map> dataPoints;

    public ReportData(int axisIndex, String key, Date fromDate, Date toDate) {
        this.axisIndex = axisIndex;
        this.key = key;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.dataPoints = new ArrayList<Map>();
    }

    public int getAxisIndex() {
        return axisIndex;
    }

    public String getKey() {
        return key;
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
