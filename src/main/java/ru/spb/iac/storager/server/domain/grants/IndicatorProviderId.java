package ru.spb.iac.storager.server.domain.grants;

import java.io.Serializable;
import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Embeddable;
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
import ru.spb.iac.storager.server.domain.shared.JpaConstructor;
import ru.spb.iac.storager.server.domain.shared.MapperConstructor;

@Embeddable
public class IndicatorProviderId implements Serializable {



    @Column(name = "ID_IND", nullable = false, updatable = false)
    private Integer indicator_id;
    
 
    
    @Column(name = "ID_APP", nullable = false, updatable = false)
    private Integer provider_id;

    public Integer getIndicator_id() {
        return indicator_id;
    }

    public void setIndicator_id(Integer indicator_id) {
        this.indicator_id = indicator_id;
    }

    public Integer getProvider_id() {
        return provider_id;
    }

    public void setProvider_id(Integer provider_id) {
        this.provider_id = provider_id;
    }






}
