package ru.spb.iac.storager.server.security;

import java.util.Arrays;
import java.util.HashSet;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import ru.spb.iac.storager.server.security.errors.NotAuthenticatedException;
import ru.spb.iac.storager.server.security.errors.NotAuthorizedException;

@Component
@Scope(scopeName = "request", proxyMode = ScopedProxyMode.INTERFACES)
public class SecurityContextDefault implements SecurityContextConfigurer {

    private UserAuthentication userAuthentication;

    public UserAuthentication getUserAuthentication() {
        return userAuthentication;
    }

    @Override
    public UserAuthentication userAuthorizedWith(final String... roles) {
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

    public void setUserAuthentication(final UserAuthentication userAuthentication) {
        this.userAuthentication = userAuthentication;
    }
}
