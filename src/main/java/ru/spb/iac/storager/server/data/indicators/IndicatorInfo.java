package ru.spb.iac.storager.server.data.indicators;

import java.io.Serializable;

public class IndicatorInfo implements Serializable {

    public static IndicatorInfo fromIndicator(Indicator indicator) {
        IndicatorInfo result = new IndicatorInfo();
        result.setCode(indicator.getCode());
        result.setAscendantCode(indicator.getAscendant() != null ? indicator.getAscendant().getCode() : null);
        result.setTitle(indicator.getTitle());
        result.setTerminal(indicator.getDescendants() == null || indicator.getDescendants().size() == 0);
        return result;
    }

    private String code;
    private String ascendantCode;
    private String title;
    private Boolean terminal;

    public IndicatorInfo() {

    }

    public IndicatorInfo(String code, String ascendantCode, String title) {
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
