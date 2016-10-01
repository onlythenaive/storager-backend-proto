package ru.spb.iac.storager.server.domain.territories;

public final class TerritoryInfo {

    private final String code;
    private final String ascendantCode;
    private final String title;
    private final String description;
    private final Boolean terminal;

    public TerritoryInfo(final String code,
                         final String ascendantCode,
                         final String title,
                         final String description,
                         final Boolean terminal) {
        this.code = code;
        this.ascendantCode = ascendantCode;
        this.title = title;
        this.description = description;
        this.terminal = terminal;
    }

    public String getCode() {
        return code;
    }

    public String getAscendantCode() {
        return ascendantCode;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Boolean getTerminal() {
        return terminal;
    }
}
