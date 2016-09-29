package ru.spb.iac.storager.server.security;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import ru.spb.iac.storager.server.domain.users.User;

public final class UserAuthentication {

    private final Instant creation;
    private final String login;
    private final Set<String> roles;
    private final boolean root;
    private final UserToken token;

    public UserAuthentication (final User user) {
        this(user, null);
    }

    public UserAuthentication (final User user, final UserToken token) {
        this.creation = Instant.now();
        this.login = user.getLogin();
        this.roles = new HashSet<>(user.getRolesParsed());
        this.root = user.getRoot();
        this.token = token;
    }

    public Instant getCreation() {
        return creation;
    }

    public String getLogin() {
        return login;
    }

    public Set<String> getRoles() {
        return new HashSet<>(roles);
    }

    public boolean isRoot() {
        return root;
    }

    public UserToken getToken() {
        return token;
    }

    public boolean hasToken() {
        return token != null;
    }

    public UserAuthenticationInfo toAuthenticationInfo() {
        return new UserAuthenticationInfo(this);
    }
}
