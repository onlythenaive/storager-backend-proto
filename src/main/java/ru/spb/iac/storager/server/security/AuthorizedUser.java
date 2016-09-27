package ru.spb.iac.storager.server.security;

import java.io.Serializable;
import java.util.Set;

public class AuthorizedUser implements Serializable {

    public static AuthorizedUser of(String login, Set<String> roles, UserToken token) {
        AuthorizedUser user = new AuthorizedUser();
        user.login = login;
        user.roles = roles;
        user.token = token;
        return user;
    }

    private String login;
    private Set<String> roles;
    private UserToken token;

    public String getLogin() {
        return login;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public UserToken getToken() {
        return token;
    }
}
