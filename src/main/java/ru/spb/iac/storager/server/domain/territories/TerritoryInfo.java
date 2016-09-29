package ru.spb.iac.storager.server.domain.territories;

import java.io.Serializable;

public class TerritoryInfo implements Serializable {

    public static TerritoryInfo fromTerritory(Territory territory) {
        TerritoryInfo info = new TerritoryInfo();
        info.setCode(territory.getCode());
        info.setAscendantCode(territory.getAscendant() != null ? territory.getAscendant().getCode() : null);
        info.setTitle(territory.getTitle());
        info.setTerminal(territory.getDescendants().size() == 0);
        return info;
    }

    private String code;
    private String ascendantCode;
    private String title;
    private Boolean terminal;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getAscendantCode() {
        return ascendantCode;
    }

    public void setAscendantCode(String ascendantCode) {
        this.ascendantCode = ascendantCode;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getTerminal() {
        return terminal;
    }

    public void setTerminal(Boolean terminal) {
        this.terminal = terminal;
    }
}
