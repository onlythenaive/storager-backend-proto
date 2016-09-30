package ru.spb.iac.storager.server.integration.providing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import ru.spb.iac.storager.server.domain.periods.PeriodService;

@Endpoint
public class PeriodEndpoint {

    private static final String NAMESPACE = "http://iac.spb.ru/storager/server/integration/providing";

    @Autowired
    private PeriodService periodService;

    @ResponsePayload
    @PayloadRoot(namespace = NAMESPACE, localPart = "getPeriodDictionaryRequest")
    public GetPeriodDictionaryResponse getPeriodDictionary(@RequestPayload GetPeriodDictionaryRequest request) {
        final GetPeriodDictionaryResponse response = new GetPeriodDictionaryResponse();
        periodService
                .getAll()
                .forEach(period -> {
                    PeriodData data = new PeriodData();
                    data.setCode(period.getCode());
                    data.setTitle(period.getTitle());
                    response.getPeriods().add(data);
                });
        return response;
    }
}
