package ru.spb.iac.storager.server.security;

import java.time.Duration;
import java.time.Instant;
import java.util.UUID;

public final class UserToken {

    private final String id;
    private final String login;
    private final Instant creation;
    private final Duration deactivation;
    private final Instant expiration;

    private Instant lastUpdate;

    public UserToken(final String login,
                     final Duration deactivation,
                     final Duration expiration) {
        this.id = UUID.randomUUID().toString();
        this.login = login;
        this.creation = Instant.now();
        this.deactivation = deactivation;
        this.expiration = this.creation.plus(expiration);
        this.lastUpdate = this.creation;
    }

    public String getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public Instant getCreation() {
        return creation;
    }

    public Duration getDeactivation() {
        return deactivation;
    }

    public Instant getExpiration() {
        return expiration;
    }

    public Instant getLastUpdate() {
        return lastUpdate;
    }

    public boolean isDeactivated() {
        return Instant.now().minus(deactivation).isAfter(lastUpdate);
    }

    public boolean isExpired() {
        return Instant.now().isAfter(expiration);
    }

    public void update() {
        this.lastUpdate = Instant.now();
    }
}
