package ru.spb.iac.storager.server.integration.providing;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class AxisEndpoint extends BaseProvidingEndpoint {

    @ResponsePayload
    @PayloadRoot(namespace = NAMESPACE, localPart = "getAxesRequest")
    public GetAxesResponse getAxes(@RequestPayload GetAxesRequest request) {
        authenticate(request);
        final GetAxesResponse response = new GetAxesResponse();
        response.getAxes().add("territory");
        return response;
    }
}
