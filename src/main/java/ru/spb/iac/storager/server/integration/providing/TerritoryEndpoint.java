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
public class TerritoryEndpoint extends BaseProvidingEndpoint {

    @Autowired
    private TerritoryService service;

    @ResponsePayload
    @PayloadRoot(namespace = NAMESPACE, localPart = "getTerritoryRequest")
    public GetTerritoryResponse getTerritory(@RequestPayload GetTerritoryRequest request) {
        providerAuthenticated(request);
        final GetTerritoryResponse response = new GetTerritoryResponse();
        final HierarchicItemInfo info = service.getByCode(request.getCode());
        final HierarchicInfoType territory = new HierarchicInfoType();
        territory.setCode(info.getCode());
        territory.setTitle(info.getTitle());
        territory.setAscendantCode(info.getAscendantCode());
        territory.setTerminal(info.getTerminal());
        response.setTerritory(territory);
        return response;
    }

    @ResponsePayload
    @PayloadRoot(namespace = NAMESPACE, localPart = "getTerritoryPageRequest")
    public GetTerritoryPageResponse getTerritoryPage(@RequestPayload GetTerritoryPageRequest request) {
        providerAuthenticated(request);
        final GetTerritoryPageResponse response = new GetTerritoryPageResponse();
        final PagedResult<HierarchicItemInfo> paged = service.getPage(
                request.getCodePattern(),
                request.getAscendantCode(),
                request.getTitlePattern(),
                request.getPage().intValue(),
                request.getSize().intValue()
        );
        paged.getItems().forEach(info -> {
            final HierarchicInfoType territory = new HierarchicInfoType();
            territory.setCode(info.getCode());
            territory.setTitle(info.getTitle());
            territory.setAscendantCode(info.getAscendantCode());
            territory.setTerminal(info.getTerminal());
            response.getItems().add(territory);
        });
        response.setPage(BigInteger.valueOf(paged.getPage()));
        response.setTotal(BigInteger.valueOf(paged.getTotal()));
        return response;
    }
}
