package ru.spb.iac.storager.server.domain.territories;

import java.io.Serializable;

public final class TerritoryData implements Serializable {

    private String code;
    private String ascendantCode;
    private String title;
    private Boolean terminal;

    public String getCode() {
        return code;
    }

    public void setCode(final String code) {
        this.code = code;
    }

    public String getAscendantCode() {
        return ascendantCode;
    }

    public void setAscendantCode(final String ascendantCode) {
        this.ascendantCode = ascendantCode;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public Boolean getTerminal() {
        return terminal;
    }

    public void setTerminal(final Boolean terminal) {
        this.terminal = terminal;
    }
}
