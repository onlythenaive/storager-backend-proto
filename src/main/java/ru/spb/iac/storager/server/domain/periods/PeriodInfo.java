package ru.spb.iac.storager.server.domain.periods;

public final class PeriodInfo {

    private final String code;
    private final String title;

    public PeriodInfo (final String code, final String title) {
        this.code = code;
        this.title = title;
    }

    public String getCode() {
        return code;
    }

    public String getTitle() {
        return title;
    }
}
