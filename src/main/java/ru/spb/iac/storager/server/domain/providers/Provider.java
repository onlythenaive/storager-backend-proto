package ru.spb.iac.storager.server.domain.providers;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import ru.spb.iac.storager.server.domain.grants.Grant;

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

    @OneToMany(mappedBy = "provider")
    private List<Grant> grants;

    protected Provider() {

    }

    public Provider(String title, String description, String token) {
        this.title = title;
        this.description = description;
        this.token = token;
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

    @PrePersist
    private void onPrePersist() {
        registeredAt = Instant.now();
    }
}
