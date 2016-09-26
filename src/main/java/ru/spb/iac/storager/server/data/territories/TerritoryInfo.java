package ru.spb.iac.storager.server.data.territories;

import java.io.Serializable;

public class TerritoryInfo implements Serializable {

    public static TerritoryInfo fromTerritory(Territory territory) {
        TerritoryInfo result = new TerritoryInfo();
        result.setCode(territory.getCode());
        result.setAscendantCode(territory.getAscendant() != null ? territory.getAscendant().getCode() : null);
        result.setTitle(territory.getTitle());
        result.setTerminal(territory.getDescendants() == null || territory.getDescendants().size() == 0);
        return result;
    }

    private String code;
    private String ascendantCode;
    private String title;
    private Boolean terminal;

    public TerritoryInfo() {

    }

    public TerritoryInfo(String code, String ascendantCode, String title) {
        this.code = code;
        this.ascendantCode = ascendantCode;
        this.title = title;
    }

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