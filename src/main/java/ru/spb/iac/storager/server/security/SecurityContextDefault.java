package ru.spb.iac.storager.server.security;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Component
@Scope(scopeName = "request", proxyMode = ScopedProxyMode.INTERFACES)
public class SecurityContextDefault implements SecurityContextConfigurer {

    private AuthorizedUser authorizedUser;

    @Override
    public AuthorizedUser getAuthorizedUser() {
        return authorizedUser;
    }

    @Override
    public AuthorizedUser userAuthorizedWith(String... roles) {
        return authorizedUser;
    }

    @Override
    public void setAuthorizedUser(AuthorizedUser authorizedUser) {
        this.authorizedUser = authorizedUser;
    }
}
