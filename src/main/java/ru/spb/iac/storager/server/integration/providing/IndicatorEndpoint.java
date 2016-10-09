package ru.spb.iac.storager.server.integration.providing;

import java.math.BigInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import ru.spb.iac.storager.server.domain.indicators.IndicatorService;
import ru.spb.iac.storager.server.domain.shared.PagedResult;
import ru.spb.iac.storager.server.domain.shared.hierarchic.HierarchicItemInfo;

@Endpoint
public class IndicatorEndpoint extends BaseProvidingEndpoint {

    @Autowired
    private IndicatorService service;

    @ResponsePayload
    @PayloadRoot(namespace = NAMESPACE, localPart = "getIndicatorRequest")
    public GetIndicatorResponse getIndicator(@RequestPayload GetIndicatorRequest request) {
        providerAuthenticated(request);
        final GetIndicatorResponse response = new GetIndicatorResponse();
        final HierarchicItemInfo info = service.getByCode(request.getCode());
        response.setCode(info.getCode());
        response.setTitle(info.getTitle());
        response.setAscendantCode(info.getAscendantCode());
        response.setTerminal(info.getTerminal());
        return response;
    }

    @ResponsePayload
    @PayloadRoot(namespace = NAMESPACE, localPart = "getIndicatorPageRequest")
    public GetIndicatorPageResponse getIndicatorPage(@RequestPayload GetIndicatorPageRequest request) {
        providerAuthenticated(request);
        final GetIndicatorPageResponse response = new GetIndicatorPageResponse();
        final PagedResult<HierarchicItemInfo> paged = service.getPage(
                request.getCodePattern(),
                request.getAscendantCode(),
                request.getTitlePattern(),
                request.getPage().intValue(),
                request.getSize().intValue()
        );
        paged.getItems().forEach(info -> {
            final HierarchicItemInfoStruct indicator = new HierarchicItemInfoStruct();
            indicator.setCode(info.getCode());
            indicator.setTitle(info.getTitle());
            indicator.setAscendantCode(info.getAscendantCode());
            indicator.setTerminal(info.getTerminal());
            response.getItems().add(indicator);
        });
        response.setPage(BigInteger.valueOf(paged.getPage()));
        response.setTotal(BigInteger.valueOf(paged.getTotal()));
        return response;
    }
}
