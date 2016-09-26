package ru.spb.iac.storager.server.data.grants;

import java.time.Instant;
import javax.persistence.*;

import ru.spb.iac.storager.server.data.indicators.Indicator;
import ru.spb.iac.storager.server.data.providers.Provider;

@Entity
@Table(name = "grants")
public class Grant {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;

    @Column(name = "created_at")
    private Instant createdAt;

    @ManyToOne
    @JoinColumn(name = "indicator_id")
    private Indicator indicator;

    @ManyToOne
    @JoinColumn(name = "provider_id")
    private Provider provider;

    public Grant() {

    }

    public Grant(Indicator indicator, Provider provider) {
        this.createdAt = Instant.now();
        this.indicator = indicator;
        this.provider = provider;
    }

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
}
