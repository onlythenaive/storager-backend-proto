package ru.spb.iac.storager.server.integration.providing;

import org.springframework.beans.factory.annotation.Autowired;

import ru.spb.iac.storager.server.security.ProviderSecurityService;

public abstract class AbstractSecureEndpoint {

    @Autowired
    private ProviderSecurityService securityService;

    protected Object providerAuthenticated(final SecureRequestType request) {
        return securityService.authenticateByToken(request.getProviderSecurityToken());
    }
}
