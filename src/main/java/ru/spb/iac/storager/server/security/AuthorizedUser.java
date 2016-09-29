package ru.spb.iac.storager.server.security;

import java.io.Serializable;
import java.util.Set;

import ru.spb.iac.storager.server.domain.users.User;

public class AuthorizedUser implements Serializable {

    public static AuthorizedUser fromUserAndToken(User user, UserToken token) {
        AuthorizedUser authorized = new AuthorizedUser();
        authorized.login = user.getLogin();
        authorized.roles = User.parseRoles(user.getRoles());
        authorized.root = user.getRoot();
        authorized.token = token;
        return authorized;
    }

    private String login;
    private Set<String> roles;
    private boolean root;
    private UserToken token;

    public String getLogin() {
        return login;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public boolean isRoot() {
        return root;
    }

    public UserToken getToken() {
        return token;
    }
}
