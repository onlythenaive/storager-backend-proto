package ru.spb.iac.storager.server.integration.providing;

import org.springframework.beans.factory.annotation.Autowired;

import ru.spb.iac.storager.server.security.ProviderAuthentication;
import ru.spb.iac.storager.server.security.ProviderSecurityService;
import ru.spb.iac.storager.server.security.SecurityContextConfigurer;

public abstract class BaseProvidingEndpoint {

    public static final String NAMESPACE = "http://iac.spb.ru/storager/server/integration/providing";

    @Autowired
    private SecurityContextConfigurer securityContext;

    @Autowired
    private ProviderSecurityService securityService;

    protected ProviderAuthentication authenticate(final SecureRequestStruct request) {
        final String token = request.getProviderSecurityToken();
        final ProviderAuthentication authentication = securityService.authenticateByToken(token);
        securityContext.setProviderAuthentication(authentication);
        return authentication;
    }
}
