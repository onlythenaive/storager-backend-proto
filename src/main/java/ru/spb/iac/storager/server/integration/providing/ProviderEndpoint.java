package ru.spb.iac.storager.server.integration.providing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import ru.spb.iac.storager.server.domain.providers.ProviderInfo;
import ru.spb.iac.storager.server.domain.providers.ProviderService;

@Endpoint
public class ProviderEndpoint extends BaseProvidingEndpoint {

    @Autowired
    private ProviderService service;

    @ResponsePayload
    @PayloadRoot(namespace = NAMESPACE, localPart = "getProviderRequest")
    public GetProviderResponse getProvider(@RequestPayload GetProviderRequest request) {
        authenticate(request);
        final ProviderInfo info = service.getAuthenticated();
        final GetProviderResponse response = new GetProviderResponse();
        response.setTitle(info.getTitle());
        response.setDescription(info.getDescription());
        response.setRegisteredAt(info.getRegisteredAt());
        response.getGrants().addAll(info.getGrants());
        return response;
    }
}
