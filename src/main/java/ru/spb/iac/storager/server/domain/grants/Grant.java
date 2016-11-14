package ru.spb.iac.storager.server.domain.grants;

import java.io.Serializable;
import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import ru.spb.iac.storager.server.domain.indicators.Indicator;
import ru.spb.iac.storager.server.domain.providers.Provider;
import ru.spb.iac.storager.server.domain.shared.JpaConstructor;
import ru.spb.iac.storager.server.domain.shared.MapperConstructor;

@Entity
@Table(name = "SRV_APP_IND", schema="ANALITICA3", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"ID_IND", "ID_APP"})
})
public class Grant implements Serializable {

     @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_provider")
    @SequenceGenerator(name = "seq_provider", sequenceName = "SQ_SRV_ID_APP_IND", schema="ANALITICA3",  allocationSize = 1)
    @Column(name = "ID_APP_IND", nullable = false, unique = true, insertable = false, updatable = false)
    private Integer id;


    @Column(name = "D_IN", nullable = false, updatable = false)
    private Instant createdAt;


    @ManyToOne
    @JoinColumn(name = "ID_IND", nullable = false, updatable = false)
    private Indicator indicator;
    

    @ManyToOne
    @JoinColumn(name = "ID_APP", nullable = false, updatable = false)
    private Provider provider;

    @JpaConstructor
    protected Grant() {

    }

    @MapperConstructor
    public Grant(final Indicator indicator, final Provider provider) {
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

    @PrePersist
    private void onPersist() {
        createdAt = Instant.now();
    }
}
