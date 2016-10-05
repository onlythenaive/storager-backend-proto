package ru.spb.iac.storager.server.domain.patches;

public final class PatchIndicatorInfo {

    private final String indicatorCode;
    private final Integer totalPoints;

    public PatchIndicatorInfo(final String indicatorCode,
                              final Integer totalPoints) {
        this.indicatorCode = indicatorCode;
        this.totalPoints = totalPoints;
    }

    public String getIndicatorCode () {
        return indicatorCode;
    }

    public Integer getTotalPoints () {
        return totalPoints;
    }
}
