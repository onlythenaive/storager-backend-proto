package ru.spb.iac.storager.server.domain.providers;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import ru.spb.iac.storager.server.domain.grants.Grant;
import ru.spb.iac.storager.server.domain.shared.JpaConstructor;
import ru.spb.iac.storager.server.domain.shared.MapperConstructor;

@Entity
@Table(name = "providers")
public class Provider {

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

    @OneToMany(mappedBy = "provider", cascade = CascadeType.ALL)
    private List<Grant> grants;

    @JpaConstructor
    @MapperConstructor
    protected Provider() {
        this.grants = new ArrayList<>();
    }

    public Integer getId() {
        return id;
    }

    public Instant getRegisteredAt() {
        return registeredAt;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public String getToken() {
        return token;
    }

    public void setToken(final String token) {
        this.token = token;
    }

    public List<Grant> getGrants() {
        return grants;
    }

    @PrePersist
    private void onPersist() {
        registeredAt = Instant.now();
    }
}
