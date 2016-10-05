package ru.spb.iac.storager.server.integration.providing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import ru.spb.iac.storager.server.domain.indicators.IndicatorInfo;
import ru.spb.iac.storager.server.domain.indicators.IndicatorService;

@Endpoint
public class IndicatorEndpoint {

    private static final String NAMESPACE = "http://iac.spb.ru/storager/server/integration/providing";

    @Autowired
    private IndicatorService indicatorService;

    @ResponsePayload
    @PayloadRoot(namespace = NAMESPACE, localPart = "getIndicatorRequest")
    public GetIndicatorResponse getIndicator(@RequestPayload GetIndicatorRequest request) {
        final GetIndicatorResponse response = new GetIndicatorResponse();
        final IndicatorInfo info = indicatorService.getByCode(request.getCode());
        final IndicatorType indicator = new IndicatorType();
        indicator.setCode(info.getCode());
        indicator.setTitle(info.getTitle());
        indicator.setAscendantCode(info.getAscendantCode());
        indicator.setTerminal(info.getTerminal());
        response.setIndicator(indicator);
        return response;
    }

    @ResponsePayload
    @PayloadRoot(namespace = NAMESPACE, localPart = "getIndicatorDescendantsRequest")
    public GetIndicatorDescendantsResponse getIndicatorDescendants(@RequestPayload GetIndicatorDescendantsRequest request) {
        final GetIndicatorDescendantsResponse response = new GetIndicatorDescendantsResponse();
        indicatorService
                .getDescendants(request.getAscendantCode())
                .forEach(data -> {
                    final IndicatorType indicator = new IndicatorType();
                    indicator.setCode(data.getCode());
                    indicator.setTitle(data.getTitle());
                    indicator.setAscendantCode(data.getAscendantCode());
                    indicator.setTerminal(data.getTerminal());
                    response.getDescendants().add(indicator);
                });
        return response;
    }

    @ResponsePayload
    @PayloadRoot(namespace = NAMESPACE, localPart = "getIndicatorRootsRequest")
    public GetIndicatorRootsResponse getIndicatorRoots(@RequestPayload GetIndicatorRootsRequest request) {
        final GetIndicatorRootsResponse response = new GetIndicatorRootsResponse();
        indicatorService
                .getRoots()
                .forEach(data -> {
                    final IndicatorType indicator = new IndicatorType();
                    indicator.setCode(data.getCode());
                    indicator.setTitle(data.getTitle());
                    indicator.setAscendantCode(data.getAscendantCode());
                    indicator.setTerminal(data.getTerminal());
                    response.getIndicators().add(indicator);
                });
        return response;
    }
}
