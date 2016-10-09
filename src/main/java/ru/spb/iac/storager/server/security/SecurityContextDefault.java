package ru.spb.iac.storager.server.security;

import java.util.Arrays;
import java.util.HashSet;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import ru.spb.iac.storager.server.errors.security.NotAuthenticatedException;
import ru.spb.iac.storager.server.errors.security.NotAuthorizedException;

@Component
@Scope(scopeName = "request", proxyMode = ScopedProxyMode.INTERFACES)
public class SecurityContextDefault implements SecurityContextConfigurer {

    private ProviderAuthentication providerAuthentication;
    private UserAuthentication userAuthentication;

    @Override
    public ProviderAuthentication getProviderAuthentication() {
        return providerAuthentication;
    }

    @Override
    public void setProviderAuthentication(final ProviderAuthentication providerAuthentication) {
        this.providerAuthentication = providerAuthentication;
    }

    public UserAuthentication getUserAuthentication() {
        return userAuthentication;
    }

    public void setUserAuthentication(final UserAuthentication userAuthentication) {
        this.userAuthentication = userAuthentication;
    }

    @Override
    public ProviderAuthentication providerAuthenticated() {
        if (providerAuthentication == null) {
            throw new NotAuthenticatedException();
        }
        return providerAuthentication;
    }

    @Override
    public UserAuthentication userAuthenticated() {
        if (userAuthentication == null) {
            throw new NotAuthenticatedException();
        }
        return userAuthentication;
    }

    @Override
    public UserAuthentication userAuthorizedWithAny(final String... roles) {
        if (userAuthentication == null) {
            throw new NotAuthenticatedException();
        }
        if (userAuthentication.isRoot()) {
            return userAuthentication;
        }
        if (roles == null || roles.length == 0) {
            return userAuthentication;
        }
        for (final String role : roles) {
            if (userAuthentication.getRoles().contains(role)) {
                return userAuthentication;
            }
        }
        throw new NotAuthorizedException(userAuthentication.getLogin(), new HashSet<>(Arrays.asList(roles)));
    }
}
