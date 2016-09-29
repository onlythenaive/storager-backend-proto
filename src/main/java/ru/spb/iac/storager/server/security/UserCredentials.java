package ru.spb.iac.storager.server.security;

import java.io.Serializable;

import ru.spb.iac.storager.server.domain.users.User;

public class UserCredentials implements Serializable {

    public static UserCredentials fromUser(User user) {
        UserCredentials credentials = new UserCredentials();
        credentials.setLogin(user.getLogin());
        credentials.setSecret(user.getSecret());
        return credentials;
    }

    private String login;
    private String secret;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }
}
