package ru.spb.iac.storager.server.domain.points;

public final class PointProperties {

    private Double real;
    private Double plan;
    private String time;
    private String indicatorCode;
    private String periodCode;
    private String territoryCode;

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

    public String getTime() {
        return time;
    }

    public void setTime(final String time) {
        this.time = time;
    }

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
}
