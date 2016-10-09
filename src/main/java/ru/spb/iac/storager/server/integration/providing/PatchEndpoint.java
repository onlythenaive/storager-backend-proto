package ru.spb.iac.storager.server.integration.providing;

import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import ru.spb.iac.storager.server.domain.patches.PatchInfo;
import ru.spb.iac.storager.server.domain.patches.PatchProperties;
import ru.spb.iac.storager.server.domain.patches.PatchService;
import ru.spb.iac.storager.server.domain.points.PointProperties;
import ru.spb.iac.storager.server.domain.shared.PagedResult;

@Endpoint
public class PatchEndpoint extends BaseProvidingEndpoint {

    @Autowired
    private PatchService service;

    @ResponsePayload
    @PayloadRoot(namespace = NAMESPACE, localPart = "createPatchRequest")
    public CreatePatchResponse createPatch(@RequestPayload CreatePatchRequest request) {
        providerAuthenticated(request);
        final PatchProperties properties = new PatchProperties();
        final List<PointProperties> points = request.getPoints()
                .stream()
                .map(struct -> {
                    final PointProperties point = new PointProperties();
                    point.setIndicatorCode(struct.getIndicatorCode());
                    point.setPeriodCode(struct.getPeriodCode());
                    point.setTerritoryCode(struct.getTerritoryCode());
                    point.setDate(struct.getDate());
                    point.setReal(struct.getReal());
                    point.setPlan(struct.getPlan());
                    return point;
                })
                .collect(Collectors.toList());
        properties.setComment(request.getComment());
        properties.setPoints(points);
        final PatchInfo info = service.create(properties);
        return intoStruct(info, new CreatePatchResponse());
    }

    @ResponsePayload
    @PayloadRoot(namespace = NAMESPACE, localPart = "createPatchInSandboxRequest")
    public CreatePatchInSandboxResponse createPatch(@RequestPayload CreatePatchInSandboxRequest request) {
        providerAuthenticated(request);
        final PatchProperties properties = new PatchProperties();
        final List<PointProperties> points = request.getPoints()
                .stream()
                .map(struct -> {
                    final PointProperties point = new PointProperties();
                    point.setIndicatorCode(struct.getIndicatorCode());
                    point.setPeriodCode(struct.getPeriodCode());
                    point.setTerritoryCode(struct.getTerritoryCode());
                    point.setDate(struct.getDate());
                    point.setReal(struct.getReal());
                    point.setPlan(struct.getPlan());
                    return point;
                })
                .collect(Collectors.toList());
        properties.setComment(request.getComment());
        properties.setPoints(points);
        final PatchInfo info = service.create(properties);
        return intoStruct(info, new CreatePatchInSandboxResponse());
    }

    @ResponsePayload
    @PayloadRoot(namespace = NAMESPACE, localPart = "getPatchRequest")
    public GetPatchResponse getPatch(@RequestPayload GetPatchRequest request) {
        providerAuthenticated(request);
        final GetPatchResponse response = new GetPatchResponse();
        final PatchInfo info = service.getById(request.getId().intValue());
        return intoStruct(info, response);
    }

    @ResponsePayload
    @PayloadRoot(namespace = NAMESPACE, localPart = "getPatchPageRequest")
    public GetPatchPageResponse getPatchPage(@RequestPayload GetPatchPageRequest request) {
        providerAuthenticated(request);
        final GetPatchPageResponse response = new GetPatchPageResponse();
        final PagedResult<PatchInfo> paged = service.getPage(
                request.getProviderTitlePattern(),
                request.getStatus(),
                request.getCreatedSince(),
                request.getCreatedUntil(),
                request.getPage().intValue(),
                request.getSize().intValue()
        );
        paged.getItems().forEach(info -> {
            final PatchInfoStruct struct = new PatchInfoStruct();
            response.getItems().add(intoStruct(info, struct));
        });
        response.setPage(BigInteger.valueOf(paged.getPage()));
        response.setTotal(BigInteger.valueOf(paged.getTotal()));
        return response;
    }

    private <T extends PatchInfoStruct> T intoStruct(final PatchInfo info, final T struct) {
        struct.setId(BigInteger.valueOf(info.getId()));
        struct.setProviderId(BigInteger.valueOf(info.getProviderId()));
        struct.setComment(info.getComment());
        struct.setStatus(info.getStatus());
        struct.setCreatedAt(info.getCreatedAt());
        info.getIndicatorInfos().forEach(patchIndicatorInfo -> {
            final PatchIndicatorInfoStruct indicatorInfoStruct = new PatchIndicatorInfoStruct();
            indicatorInfoStruct.setIndicatorCode(patchIndicatorInfo.getIndicatorCode());
            indicatorInfoStruct.setTotalPoints(BigInteger.valueOf(patchIndicatorInfo.getTotalPoints()));
            struct.getIndicatorInfos().add(indicatorInfoStruct);
        });
        return struct;
    }
}
