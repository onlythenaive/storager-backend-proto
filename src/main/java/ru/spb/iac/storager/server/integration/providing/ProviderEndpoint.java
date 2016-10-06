package ru.spb.iac.storager.server.integration.providing;

import java.math.BigInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import ru.spb.iac.storager.server.domain.providers.ProviderInfo;
import ru.spb.iac.storager.server.domain.providers.ProviderService;
import ru.spb.iac.storager.server.security.ProviderAuthentication;

@Endpoint
public class ProviderEndpoint extends BaseProvidingEndpoint {

    @Autowired
    private ProviderService service;

    @ResponsePayload
    @PayloadRoot(namespace = NAMESPACE, localPart = "getProviderRequest")
    public GetProviderResponse getProvider(@RequestPayload GetProviderRequest request) {
        final ProviderAuthentication authentication = providerAuthenticated(request);
        final ProviderInfo info = service.getById(authentication.getId());
        final GetProviderResponse response = new GetProviderResponse();
        response.setId(BigInteger.valueOf(info.getId()));
        response.setTitle(info.getTitle());
        response.setDescription(info.getDescription());
        response.setRegisteredAt(info.getRegisteredAt());
        response.getGrants().addAll(info.getGrants());
        return response;
    }
}
