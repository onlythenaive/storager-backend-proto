package ru.spb.iac.storager.server.integration.providing;

public abstract class AbstractSecureEndpoint {

    protected Object providerAuthenticated(final SecureRequestType request) {
        final String token = request.getProviderSecurityToken();
        if (token == null || token.isEmpty()) {
            throw new RuntimeException("security token is missing");
        }
        return new Object();
    }
}
