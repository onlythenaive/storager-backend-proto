package ru.spb.iac.storager.server.data.patches;

import ru.spb.iac.storager.server.data.points.Point;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

public class PatchInfo implements Serializable {

    public static PatchInfo fromPatch(Patch patch) {
        PatchInfo result = new PatchInfo();
        result.setId(patch.getId());
        result.setComment(patch.getComment());
        result.setCreatedAt(patch.getCreatedAt().toString());
        result.setStatus(patch.getStatus());
        result.setProviderId(patch.getProvider().getId());
        result.setIndicatorInfos(patch.getPoints()
                .stream()
                .collect(Collectors.groupingBy(point -> point.getIndicator().getCode()))
                .entrySet()
                .stream()
                .map(e -> new PatchIndicatorInfo(e.getKey(), e.getValue().size()))
                .collect(Collectors.toList()));
        return result;
    }

    private Integer id;
    private String comment;
    private Integer providerId;
    private String createdAt;
    private String status;
    private List<PatchIndicatorInfo> indicatorInfos;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Integer getProviderId() {
        return providerId;
    }

    public void setProviderId(Integer providerId) {
        this.providerId = providerId;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<PatchIndicatorInfo> getIndicatorInfos() {
        return indicatorInfos;
    }

    public void setIndicatorInfos(List<PatchIndicatorInfo> indicatorInfos) {
        this.indicatorInfos = indicatorInfos;
    }
}
