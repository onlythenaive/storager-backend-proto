package ru.spb.iac.storager.server.security;

import java.io.Serializable;
import java.time.Instant;
import java.util.UUID;

public class UserToken implements Serializable {

    public static UserToken generate(String login) {
        UserToken token = new UserToken();
        token.id = UUID.randomUUID().toString();
        token.login = login;
        token.createdAt = Instant.now();
        return token;
    }

    private String id;
    private String login;
    private Instant createdAt;

    public String getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }
}
