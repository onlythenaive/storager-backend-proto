package ru.spb.iac.storager.server.domain.grants;

import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import ru.spb.iac.storager.server.domain.indicators.Indicator;
import ru.spb.iac.storager.server.domain.providers.Provider;

@Entity
@Table(name = "grants", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"indicator_id", "provider_id"})
})
public class Grant {

    public static Grant of(Indicator indicator, Provider provider) {
        Grant grant = new Grant();
        grant.indicator = indicator;
        grant.provider = provider;
        return grant;
    }

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false, unique = true, insertable = false, updatable = false)
    private Integer id;

    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    @ManyToOne
    @JoinColumn(name = "indicator_id", nullable = false, updatable = false)
    private Indicator indicator;

    @ManyToOne
    @JoinColumn(name = "provider_id", nullable = false, updatable = false)
    private Provider provider;

    public Integer getId() {
        return id;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Indicator getIndicator() {
        return indicator;
    }

    public Provider getProvider() {
        return provider;
    }

    @PrePersist
    private void onPersist() {
        createdAt = Instant.now();
    }
}
