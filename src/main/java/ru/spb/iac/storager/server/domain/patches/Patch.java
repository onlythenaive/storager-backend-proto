package ru.spb.iac.storager.server.domain.patches;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import ru.spb.iac.storager.server.domain.points.Point;
import ru.spb.iac.storager.server.domain.providers.Provider;
import ru.spb.iac.storager.server.domain.shared.JpaConstructor;
import ru.spb.iac.storager.server.domain.shared.MapperConstructor;

@Entity
@Table(name = "SRV_AUDIT", schema="ANALITICA3")
public class Patch {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_patch")
    @SequenceGenerator(name = "seq_patch", sequenceName = "SQ_SRV_ID_AUDIT", schema="ANALITICA3", allocationSize = 1)
    @Column(name = "ID_AUDIT", nullable = false, unique = true, insertable = false, updatable = false)
    private Integer id;

    @Column(name = "LD_DESC", nullable = false, updatable = false)
    private String comment;

    @Column(name = "D_IN", nullable = false, updatable = false)
    private Instant createdAt;

    @Column(name = "LD_STATUS", nullable = false, updatable = false)
    private String status;

    @Column(name = "LD_REASON", updatable = false)
    private String reason;

    @OneToMany(mappedBy = "patch", cascade = CascadeType.ALL)
    private List<Point> points;

    @ManyToOne
    @JoinColumn(name = "ID_APP", nullable = false, updatable = false)
    private Provider provider;

    @JpaConstructor
    @MapperConstructor
    protected Patch() {
        this.points = new ArrayList<>();
    }

    @MapperConstructor
    public Patch(final Provider provider,
                 final String comment,
                 final String status,
                 final String reason) {
        this();
        this.provider = provider;
        this.comment = comment;
        this.status = status;
        this.reason = reason;
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

    public String getReason() {
        return reason;
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
    }
}
