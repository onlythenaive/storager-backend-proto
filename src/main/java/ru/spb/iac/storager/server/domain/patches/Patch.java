package ru.spb.iac.storager.server.domain.patches;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import ru.spb.iac.storager.server.domain.points.Point;
import ru.spb.iac.storager.server.domain.providers.Provider;
import ru.spb.iac.storager.server.domain.shared.JpaConstructor;

@Entity
@Table(name = "patches")
public class Patch {

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false, unique = true, insertable = false, updatable = false)
    private Integer id;

    @Column(name = "comment", nullable = false, updatable = false)
    private String comment;

    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    @Column(name = "status", nullable = false, updatable = false)
    private String status;

    @OneToMany(mappedBy = "patch", cascade = CascadeType.ALL)
    private List<Point> points;

    @ManyToOne
    @JoinColumn(name = "provider_id", nullable = false, updatable = false)
    private Provider provider;

    @JpaConstructor
    protected Patch() {

    }

    public Patch(final String comment, final String status, final Provider provider) {
        this.comment = comment;
        this.status = status;
        this.points = new ArrayList<>();
        this.provider = provider;
    }

    public Integer getId () {
        return id;
    }

    public String getComment () {
        return comment;
    }

    public Instant getCreatedAt () {
        return createdAt;
    }

    public String getStatus () {
        return status;
    }

    public List<Point> getPoints () {
        return points;
    }

    public Provider getProvider () {
        return provider;
    }

    @PrePersist
    private void onPersist() {
        createdAt = Instant.now();
    }}
