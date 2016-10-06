package ru.spb.iac.storager.server.security;

import java.util.HashSet;
import java.util.Set;

public final class ProviderAuthentication {

    private final Integer id;
    private final Set<String> grants;

    public ProviderAuthentication(final Integer id, final Set<String> grants) {
        this.id = id;
        this.grants = new HashSet<>(grants);
    }

    public Integer getId() {
        return id;
    }

    public Set<String> getGrants() {
        return new HashSet<>(grants);
    }
}
