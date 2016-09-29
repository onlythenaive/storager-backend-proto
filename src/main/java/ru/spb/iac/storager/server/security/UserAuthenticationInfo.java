package ru.spb.iac.storager.server.security;

import java.util.HashSet;
import java.util.Set;

public final class UserAuthenticationInfo {

    private final String login;
    private final String logon;
    private final Set<String> roles;
    private final String token;

    public UserAuthenticationInfo (final UserAuthentication authentication) {
        this.login = authentication.getLogin();
        this.logon = authentication.getCreation().toString();
        this.roles = authentication.getRoles();
        this.token = authentication.hasToken() ? authentication.getToken().getId() : null;
    }

    public String getLogin() {
        return login;
    }

    public String getLogon() {
        return logon;
    }

    public Set<String> getRoles() {
        return new HashSet<>(roles);
    }

    public String getToken() {
        return token;
    }
}
