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
    @PayloadRoot(namespace = NAMESPACE, localPart = "createUploadRequest")
    public CreateUploadResponse createUpload(@RequestPayload CreateUploadRequest request) {
        authenticate(request);
        final PatchProperties properties = new PatchProperties();
        final List<PointProperties> points = request.getPoints()
                .stream()
                .map(struct -> {
                    final PointProperties point = new PointProperties();
                    struct.getDimensions().forEach(d -> {
                        if (d.getAxis().equals("territory")) {
                            point.setTerritoryCode(d.getCoordinate());
                        }
                    });
                    point.setIndicatorCode(struct.getIndicator());
                    point.setPeriodCode(struct.getPeriod());
                    point.setDate(struct.getDate());
                    point.setReal(struct.getValue().getReal());
                    point.setPlan(struct.getValue().getPlan());
                    return point;
                })
                .collect(Collectors.toList());
        properties.setComment(request.getComment());
        properties.setPoints(points);
        final PatchInfo info = service.createOnProviderBehalf(properties);
        return intoStruct(info, new CreateUploadResponse());
    }

    @ResponsePayload
    @PayloadRoot(namespace = NAMESPACE, localPart = "getUploadRequest")
    public GetUploadResponse getUpload(@RequestPayload GetUploadRequest request) {
        authenticate(request);
        final GetUploadResponse response = new GetUploadResponse();
        final PatchInfo info = service.getByIdOnProviderBehalf(request.getId().intValue());
        return intoStruct(info, response);
    }

    @ResponsePayload
    @PayloadRoot(namespace = NAMESPACE, localPart = "getUploadPageRequest")
    public GetUploadPageResponse getUploadPage(@RequestPayload GetUploadPageRequest request) {
        authenticate(request);
        final GetUploadPageResponse response = new GetUploadPageResponse();
        final PagedResult<PatchInfo> paged = service.getPageOnProviderBehalf(
                request.getStatus(),
                request.getCreatedSince(),
                request.getCreatedUntil(),
                request.getPage().intValue(),
                request.getSize().intValue()
        );
        paged.getItems().forEach(info -> {
            final UploadInfoStruct struct = new UploadInfoStruct();
            response.getItems().add(intoStruct(info, struct));
        });
        response.setPage(BigInteger.valueOf(paged.getPage()));
        response.setTotal(BigInteger.valueOf(paged.getTotal()));
        return response;
    }

    private <T extends UploadInfoStruct> T intoStruct(final PatchInfo info, final T struct) {
        struct.setId(BigInteger.valueOf(info.getId()));
        struct.setComment(info.getComment());
        struct.setStatus(info.getStatus());
        struct.setReason(info.getReason());
        struct.setCreatedAt(info.getCreatedAt());
        info.getIndicatorInfos().forEach(patchIndicatorInfo -> {
            final UploadIndicatorStatStruct statStruct = new UploadIndicatorStatStruct();
            statStruct.setIndicator(patchIndicatorInfo.getIndicatorCode());
            statStruct.setTotalPoints(BigInteger.valueOf(patchIndicatorInfo.getTotalPoints()));
            struct.getIndicatorStats().add(statStruct);
        });
        return struct;
    }
}
