package ru.spb.iac.storager.server.domain.periods;

import java.io.Serializable;

public class PeriodInfo implements Serializable {

    public static PeriodInfo fromPeriod(Period period) {
        PeriodInfo info = new PeriodInfo();
        info.setCode(period.getCode());
        info.setTitle(period.getTitle());
        return info;
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
