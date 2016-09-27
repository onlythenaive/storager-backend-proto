package ru.spb.iac.storager.server.data.providers;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

public class ProviderInfo implements Serializable {

    public static ProviderInfo fromProvider(Provider provider) {
        ProviderInfo info = new ProviderInfo();
        info.setId(provider.getId());
        info.setTitle(provider.getTitle());
        info.setDescription(provider.getDescription());
        info.setToken(provider.getToken());
        info.setRegisteredAt(provider.getRegisteredAt().toString());
        info.setGrants(provider.getGrants().stream().map(g -> g.getIndicator().getCode()).collect(Collectors.toList()));
        return info;
    }

    public static ProviderInfo of(String title, String description, String token) {
        ProviderInfo info = new ProviderInfo();
        info.title = title;
        info.description = description;
        info.token = token;
        return info;
    }

    private Integer id;
    private String title;
    private String description;
    private String token;
    private String registeredAt;
    private List<String> grants;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getRegisteredAt() {
        return registeredAt;
    }

    public void setRegisteredAt(String registeredAt) {
        this.registeredAt = registeredAt;
    }

    public List<String> getGrants() {
        return grants;
    }

    public void setGrants(List<String> grants) {
        this.grants = grants;
    }
}
