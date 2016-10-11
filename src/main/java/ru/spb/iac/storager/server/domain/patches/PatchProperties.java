package ru.spb.iac.storager.server.domain.patches;

import java.util.List;

import ru.spb.iac.storager.server.domain.points.PointProperties;

public final class PatchProperties {

    private String comment;
    private List<PointProperties> points;

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public List<PointProperties> getPoints() {
        return points;
    }

    public void setPoints(List<PointProperties> points) {
        this.points = points;
    }
}
