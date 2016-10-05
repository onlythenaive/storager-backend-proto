package ru.spb.iac.storager.server.domain.providers;

import java.util.HashSet;
import java.util.Set;

public final class ProviderInfo {

    public ProviderInfo(final Integer id,
                        final String title,
                        final String description,
                        final String registeredAt,
                        final Set<String> grants) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.registeredAt = registeredAt;
        this.grants = new HashSet<>(grants);
    }

    private final Integer id;
    private final String title;
    private final String description;
    private final String registeredAt;
    private final Set<String> grants;

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getRegisteredAt() {
        return registeredAt;
    }

    public Set<String> getGrants() {
        return new HashSet<>(grants);
    }
}
