package ru.spb.iac.storager.server.domain.patches;

import java.util.ArrayList;
import java.util.List;

public final class PatchInfo {

    private final Integer id;
    private final String comment;
    private final Integer providerId;
    private final String createdAt;
    private final String status;
    private final String reason;
    private final List<PatchIndicatorInfo> indicatorInfos;

    public PatchInfo(final Integer id,
                     final String comment,
                     final Integer providerId,
                     final String createdAt,
                     final String status,
                     final String reason,
                     final List<PatchIndicatorInfo> indicatorInfos) {
        this.id = id;
        this.comment = comment;
        this.providerId = providerId;
        this.createdAt = createdAt;
        this.status = status;
        this.reason = reason;
        this.indicatorInfos = new ArrayList<>(indicatorInfos);
    }

    public Integer getId() {
        return id;
    }

    public String getComment() {
        return comment;
    }

    public Integer getProviderId() {
        return providerId;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getStatus() {
        return status;
    }

    public String getReason() {
        return reason;
    }

    public List<PatchIndicatorInfo> getIndicatorInfos() {
        return new ArrayList<>(indicatorInfos);
    }
}
