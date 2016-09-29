package ru.spb.iac.storager.server.domain.patches;

import java.io.Serializable;

public class PatchIndicatorInfo implements Serializable {

    public static PatchIndicatorInfo of(String indicatorCode, Integer totalPoints) {
        PatchIndicatorInfo info = new PatchIndicatorInfo();
        info.indicatorCode = indicatorCode;
        info.totalPoints = totalPoints;
        return info;
    }

    private String indicatorCode;
    private Integer totalPoints;

    public String getIndicatorCode () {
        return indicatorCode;
    }

    public void setIndicatorCode (final String indicatorCode) {
        this.indicatorCode = indicatorCode;
    }

    public Integer getTotalPoints () {
        return totalPoints;
    }

    public void setTotalPoints (final Integer totalPoints) {
        this.totalPoints = totalPoints;
    }
}
