package ru.spb.iac.storager.server.security;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Component
@Scope(scopeName = "request", proxyMode = ScopedProxyMode.INTERFACES)
public class SecurityContextDefault implements SecurityContext {

    @Override
    public Object userAuthorizedWith(String... roles) {
        return new Object();
    }
}
