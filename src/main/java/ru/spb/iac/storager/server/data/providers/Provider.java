package ru.spb.iac.storager.server.data.providers;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import ru.spb.iac.storager.server.data.grants.Grant;

@Entity
@Table(name = "providers")
public class Provider {

    public static Provider of(String title, String description, String token) {
        Provider provider = new Provider();
        provider.title = title;
        provider.description = description;
        provider.token = token;
        provider.registeredAt = Instant.now();
        provider.grants = new ArrayList<>();
        return provider;
    }

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false, unique = true, insertable = false, updatable = false)
    private Integer id;

    @Column(name = "registered_at", nullable = false, updatable = false)
    private Instant registeredAt;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "token", nullable = false, unique = true)
    private String token;

    @OneToMany(mappedBy = "provider")
    private List<Grant> grants;

    public Integer getId() {
        return id;
    }

    public Instant getRegisteredAt() {
        return registeredAt;
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

    public List<Grant> getGrants() {
        return grants;
    }
}
