package ru.spb.iac.storager.server.data.patches;

import java.io.Serializable;

public class PatchIndicatorInfo implements Serializable {

  private String indicatorCode;
  private Integer totalPoints;

  public PatchIndicatorInfo () {

  }

  public PatchIndicatorInfo (final String indicatorCode, final Integer totalPoints) {
    this.indicatorCode = indicatorCode;
    this.totalPoints = totalPoints;
  }

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
