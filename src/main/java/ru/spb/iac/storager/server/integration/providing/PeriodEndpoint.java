package ru.spb.iac.storager.server.integration.providing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import ru.spb.iac.storager.server.domain.periods.PeriodService;

@Endpoint
public class PeriodEndpoint extends BaseProvidingEndpoint {

    @Autowired
    private PeriodService periodService;

    @ResponsePayload
    @PayloadRoot(namespace = NAMESPACE, localPart = "getPeriodsRequest")
    public GetPeriodsResponse getPeriods(@RequestPayload GetPeriodsRequest request) {
        authenticate(request);
        final GetPeriodsResponse response = new GetPeriodsResponse();
        periodService
                .getAllOnProviderBehalf()
                .forEach(info -> {
                    PeriodInfoStruct period = new PeriodInfoStruct();
                    period.setCode(info.getCode());
                    period.setTitle(info.getTitle());
                    response.getItems().add(period);
                });
        return response;
    }
}
