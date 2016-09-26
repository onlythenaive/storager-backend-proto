package ru.spb.iac.storager.server.data.periods;

import java.io.Serializable;

public class PeriodInfo implements Serializable {

    public static PeriodInfo fromPeriod(Period period) {
        PeriodInfo result = new PeriodInfo();
        result.setCode(period.getCode());
        result.setTitle(period.getTitle());
        return result;
    }

    private String code;
    private String title;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
