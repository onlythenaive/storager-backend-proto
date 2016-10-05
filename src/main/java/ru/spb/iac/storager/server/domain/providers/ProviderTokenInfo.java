package ru.spb.iac.storager.server.domain.providers;

public final class ProviderTokenInfo {

    private final Integer id;
    private final String token;

    public ProviderTokenInfo(final Integer id, final String token) {
        this.id = id;
        this.token = token;
    }

    public Integer getId() {
        return id;
    }

    public String getToken() {
        return token;
    }
}
