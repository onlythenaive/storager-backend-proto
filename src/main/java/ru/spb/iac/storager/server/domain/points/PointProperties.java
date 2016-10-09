package ru.spb.iac.storager.server.domain.points;

public final class PointProperties {

    private String indicatorCode;
    private String periodCode;
    private String territoryCode;
    private String date;
    private Double real;
    private Double plan;

    public String getIndicatorCode() {
        return indicatorCode;
    }

    public void setIndicatorCode(final String indicatorCode) {
        this.indicatorCode = indicatorCode;
    }

    public String getPeriodCode() {
        return periodCode;
    }

    public void setPeriodCode(final String periodCode) {
        this.periodCode = periodCode;
    }

    public String getTerritoryCode() {
        return territoryCode;
    }

    public void setTerritoryCode(final String territoryCode) {
        this.territoryCode = territoryCode;
    }

    public String getDate() {
        return date;
    }

    public void setDate(final String date) {
        this.date = date;
    }

    public Double getReal() {
        return real;
    }

    public void setReal(final Double real) {
        this.real = real;
    }

    public Double getPlan() {
        return plan;
    }

    public void setPlan(final Double plan) {
        this.plan = plan;
    }
}
