package ru.spb.iac.storager.server.integration.providing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import ru.spb.iac.storager.server.domain.periods.PeriodService;

@Endpoint
public class PeriodEndpoint extends AbstractSecureEndpoint {

    private static final String NAMESPACE = "http://iac.spb.ru/storager/server/integration/providing";

    @Autowired
    private PeriodService periodService;

    @ResponsePayload
    @PayloadRoot(namespace = NAMESPACE, localPart = "getPeriodsRequest")
    public GetPeriodsResponse getPeriods(@RequestPayload GetPeriodsRequest request) {
        final Object provider = providerAuthenticated(request);
        final GetPeriodsResponse response = new GetPeriodsResponse();
        periodService
                .getAll()
                .forEach(info -> {
                    PeriodInfoType period = new PeriodInfoType();
                    period.setCode(info.getCode());
                    period.setTitle(info.getTitle());
                    response.getPeriods().add(period);
                });
        return response;
    }
}
