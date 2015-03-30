package com.incra.models.session;

import com.incra.models.Dimension;
import com.incra.models.Measure;
import com.incra.models.enums.ReportType;

import java.io.Serializable;

/**
 * Holds the parameters for a reporting view.
 *
 * @author Jeff Risberg
 * @since August 2014
 */
public class ReportingSession implements Serializable {
    protected ReportType reportType;
    protected Dimension dimension;
    protected Measure measure;
    // Filtering information should go here

    public ReportingSession(ReportType reportType, Dimension dimension, Measure measure) {
        this.reportType = reportType;
        this.dimension = dimension;
        this.measure = measure;
    }

    public ReportType getReportType() {
        return reportType;
    }

    public void setReportType(ReportType reportType) {

        this.reportType = reportType;
        System.out.println("new report type " + reportType);
    }

    public Dimension getDimension() {
        return dimension;
    }

    public void setDimension(Dimension dimension) {
        this.dimension = dimension;
    }

    public Measure getMeasure() {
        return measure;
    }

    public void setMeasure(Measure measure) {
        this.measure = measure;
    }

    @Override
    public String toString() {
        return "ReportingSession{" +
                "reportType=" + reportType +
                ", dimension=" + dimension +
                ", measure=" + measure +
                '}';
    }
}
