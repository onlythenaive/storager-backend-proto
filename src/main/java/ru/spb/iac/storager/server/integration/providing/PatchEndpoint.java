package ru.spb.iac.storager.server.integration.providing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;

import ru.spb.iac.storager.server.domain.patches.PatchService;

@Endpoint
public class PatchEndpoint {

    private static final String NAMESPACE = "http://iac.spb.ru/storager/server/integration/providing";

    @Autowired
    private PatchService patchService;

    public CreatePatchResponse create(CreatePatchRequest request) {
        throw new UnsupportedOperationException();
    }

    public GetPatchResponse get(GetPatchRequest request) {
        throw new UnsupportedOperationException();
    }
}
