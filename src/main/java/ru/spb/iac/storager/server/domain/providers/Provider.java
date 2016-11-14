package ru.spb.iac.storager.server.domain.providers;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import ru.spb.iac.storager.server.domain.grants.Grant;
import ru.spb.iac.storager.server.domain.shared.JpaConstructor;
import ru.spb.iac.storager.server.domain.shared.MapperConstructor;

@Entity
@Table(name = "SRV_APP" , schema="ANALITICA3")
public class Provider implements Serializable {

    //todo should add sequence
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_provider")
    @SequenceGenerator(name = "seq_provider", sequenceName = "SQ_SRV_ID_APP", schema="ANALITICA3",  allocationSize = 1)
    @Column(name = "ID_APP", nullable = false, unique = true, insertable = false, updatable = false)
    private Integer id;

//    @Id
//    @GeneratedValue
//    @Column(name = "ID_APP", nullable = false, unique = true, insertable = false, updatable = false)
//    private Integer id;

    @Column(name = "D_IN", nullable = false, updatable = false)
    private Instant registeredAt;

    @Column(name = "NAME_FULL", nullable = false)
    private String title;

    @Column(name = "NAME_SHORT")
    private String description;

    @Column(name = "APP_TOKEN", nullable = false, unique = true)
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
