package ru.spb.iac.storager.server.domain.patches;

import java.io.Serializable;
import java.util.List;

import ru.spb.iac.storager.server.domain.points.PointInvoice;

public class PatchInvoice implements Serializable {

    private String comment;
    private String providerToken;
    private List<PointInvoice> points;

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

    public List<PointInvoice> getPoints() {
        return points;
    }

    public void setPoints(List<PointInvoice> points) {
        this.points = points;
    }
}
