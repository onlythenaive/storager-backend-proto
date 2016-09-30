package ru.spb.iac.storager.server.integration.providing;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class PatchEndpoint {

    private static final String NAMESPACE = "http://iac.spb.ru/storager/server/integration/providing";

    @ResponsePayload
    @PayloadRoot(namespace = NAMESPACE, localPart = "createPatchRequest")
    public CreatePatchResponse createPatch(@RequestPayload CreatePatchRequest request) {
        final CreatePatchResponse response = new CreatePatchResponse();
        response.setStatus("SUCCESS");
        return response;
    }
}
