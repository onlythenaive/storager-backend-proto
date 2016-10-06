package ru.spb.iac.storager.server.integration.providing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import ru.spb.iac.storager.server.domain.shared.hierarchic.HierarchicItemInfo;
import ru.spb.iac.storager.server.domain.territories.TerritoryService;

@Endpoint
public class TerritoryEndpoint {

    private static final String NAMESPACE = "http://iac.spb.ru/storager/server/integration/providing";
/*
    @Autowired
    private TerritoryService territoryService;

    @ResponsePayload
    @PayloadRoot(namespace = NAMESPACE, localPart = "getTerritoryRequest")
    public GetTerritoryResponse getTerritory(@RequestPayload GetTerritoryRequest request) {
        final GetTerritoryResponse response = new GetTerritoryResponse();
        final HierarchicItemInfo properties = territoryService.getByCode(request.getCode());
        final TerritoryType territory = new TerritoryType();
        territory.setCode(properties.getCode());
        territory.setTitle(properties.getTitle());
        territory.setAscendantCode(properties.getAscendantCode());
        territory.setTerminal(properties.getTerminal());
        response.setTerritory(territory);
        return response;
    }

    @ResponsePayload
    @PayloadRoot(namespace = NAMESPACE, localPart = "getTerritoryDescendantsRequest")
    public GetTerritoryDescendantsResponse getTerritoryDescendants(@RequestPayload GetTerritoryDescendantsRequest request) {
        final GetTerritoryDescendantsResponse response = new GetTerritoryDescendantsResponse();
        territoryService
                .getDescendants(request.getAscendantCode())
                .forEach(data -> {
                    final TerritoryType territory = new TerritoryType();
                    territory.setCode(data.getCode());
                    territory.setTitle(data.getTitle());
                    territory.setAscendantCode(data.getAscendantCode());
                    territory.setTerminal(data.getTerminal());
                    response.getDescendants().add(territory);
                });
        return response;
    }

    @ResponsePayload
    @PayloadRoot(namespace = NAMESPACE, localPart = "getTerritoryRootsRequest")
    public GetTerritoryRootsResponse getTerritoryRoots(@RequestPayload GetTerritoryRootsRequest request) {
        final GetTerritoryRootsResponse response = new GetTerritoryRootsResponse();
        territoryService
                .getRoots()
                .forEach(data -> {
                    final TerritoryType territory = new TerritoryType();
                    territory.setCode(data.getCode());
                    territory.setTitle(data.getTitle());
                    territory.setAscendantCode(data.getAscendantCode());
                    territory.setTerminal(data.getTerminal());
                    response.getTerritories().add(territory);
                });
        return response;
    }
*/
}
