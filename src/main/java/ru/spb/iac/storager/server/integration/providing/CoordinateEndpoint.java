package ru.spb.iac.storager.server.integration.providing;

import java.math.BigInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import ru.spb.iac.storager.server.domain.shared.PagedResult;
import ru.spb.iac.storager.server.domain.shared.hierarchic.HierarchicItemInfo;
import ru.spb.iac.storager.server.domain.territories.TerritoryService;

@Endpoint
public class CoordinateEndpoint extends BaseProvidingEndpoint {

    @Autowired
    private TerritoryService service;

    @ResponsePayload
    @PayloadRoot(namespace = NAMESPACE, localPart = "getCoordinateRequest")
    public GetCoordinateResponse getCoordinate(@RequestPayload GetCoordinateRequest request) {
        authenticate(request);
        final GetCoordinateResponse response = new GetCoordinateResponse();
        if (!request.getAxis().equals("territory")) {
            throw new IllegalArgumentException(String.format("unknown axis: %s", request.getAxis()));
        }
        final HierarchicItemInfo info = service.getByCodeOnProviderBehalf(request.getCode());
        response.setCode(info.getCode());
        response.setTitle(info.getTitle());
        response.setDescription(info.getDescription());
        return response;
    }

    @ResponsePayload
    @PayloadRoot(namespace = NAMESPACE, localPart = "getCoordinatePageRequest")
    public GetCoordinatePageResponse getCoordinatePage(@RequestPayload GetCoordinatePageRequest request) {
        authenticate(request);
        final GetCoordinatePageResponse response = new GetCoordinatePageResponse();
        if (!request.getAxis().equals("territory")) {
            throw new IllegalArgumentException(String.format("unknown axis: %s", request.getAxis()));
        }
        final PagedResult<HierarchicItemInfo> paged = service.getPageOnProviderBehalf(
                request.getCodePattern(),
                null,
                request.getTitlePattern(),
                request.getPage().intValue(),
                request.getSize().intValue()
        );
        paged.getItems().forEach(info -> {
            final PlainItemInfoStruct territory = new PlainItemInfoStruct();
            territory.setCode(info.getCode());
            territory.setTitle(info.getTitle());
            territory.setDescription(info.getDescription());
            response.getItems().add(territory);
        });
        response.setPage(BigInteger.valueOf(paged.getPage()));
        response.setTotal(BigInteger.valueOf(paged.getTotal()));
        return response;
    }
}
