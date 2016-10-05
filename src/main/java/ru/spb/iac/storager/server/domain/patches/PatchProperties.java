package ru.spb.iac.storager.server.domain.patches;

import java.util.List;

import ru.spb.iac.storager.server.domain.points.PointProperties;

public final class PatchProperties {

    private String comment;
    private String providerToken;
    private List<PointProperties> points;

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getProviderToken() {
        return providerToken;
    }

    public void setProviderToken(String providerToken) {
        this.providerToken = providerToken;
    }

    public List<PointProperties> getPoints() {
        return points;
    }

    public void setPoints(List<PointProperties> points) {
        this.points = points;
    }
}
