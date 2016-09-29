package ru.spb.iac.storager.server.security;

import java.io.Serializable;

public class UserCredentialData implements Serializable {

    private String login;
    private String secret;

    public String getLogin() {
        return login;
    }

    public void setLogin(final String login) {
        this.login = login;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(final String secret) {
        this.secret = secret;
    }
}
