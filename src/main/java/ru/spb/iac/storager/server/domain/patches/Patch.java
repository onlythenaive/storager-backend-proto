package ru.spb.iac.storager.server.domain.patches;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import ru.spb.iac.storager.server.domain.points.Point;
import ru.spb.iac.storager.server.domain.providers.Provider;

@Entity
@Table(name = "patches")
public class Patch {

    public static Patch of(String comment, String status, Provider provider) {
        Patch patch = new Patch();
        patch.comment = comment;
        patch.createdAt = Instant.now();
        patch.status = status;
        patch.points = new ArrayList<>();
        patch.provider = provider;
        return patch;
    }

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

    @OneToMany(mappedBy = "patch")
    private List<Point> points;

    @ManyToOne
    @JoinColumn(name = "provider_id", nullable = false, updatable = false)
    private Provider provider;

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
}
