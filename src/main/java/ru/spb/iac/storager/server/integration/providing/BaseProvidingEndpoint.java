package ru.spb.iac.storager.server.integration.providing;

import org.springframework.beans.factory.annotation.Autowired;

import ru.spb.iac.storager.server.security.ProviderAuthentication;
import ru.spb.iac.storager.server.security.ProviderSecurityService;

public abstract class BaseProvidingEndpoint {

    public static final String NAMESPACE = "http://iac.spb.ru/storager/server/integration/providing";

    @Autowired
    private ProviderSecurityService securityService;

    protected ProviderAuthentication providerAuthenticated(final SecureRequestStruct request) {
        return securityService.authenticateByToken(request.getProviderSecurityToken());
    }
}
