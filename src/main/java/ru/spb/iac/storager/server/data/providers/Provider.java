package ru.spb.iac.storager.server.data.providers;

import javax.persistence.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import ru.spb.iac.storager.server.data.grants.Grant;

@Entity
@Table(name = "providers")
public class Provider {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;

    @Column(name = "registered_at")
    private Instant registeredAt;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "token")
    private String token;

    @OneToMany(mappedBy = "provider")
    private List<Grant> grants;

    public Provider () {

    }

    public Provider (String title, String description) {
        this.title = title;
        this.description = description;
        this.token = UUID.randomUUID().toString();
        this.registeredAt = Instant.now();
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

    public void setGrants(List<Grant> grants) {
        this.grants = grants;
    }
}
