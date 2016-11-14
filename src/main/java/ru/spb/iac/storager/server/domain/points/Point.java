package ru.spb.iac.storager.server.domain.points;

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

import ru.spb.iac.storager.server.domain.indicators.Indicator;
import ru.spb.iac.storager.server.domain.patches.Patch;
import ru.spb.iac.storager.server.domain.periods.Period;
import ru.spb.iac.storager.server.domain.shared.JpaConstructor;
import ru.spb.iac.storager.server.domain.shared.MapperConstructor;
import ru.spb.iac.storager.server.domain.territories.Territory;

@Entity
@Table(name = "SRV_VALUES", schema="ANALITICA3")
public class Point {

    //TODO add sequence
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_point")
    @SequenceGenerator(name = "seq_point", sequenceName = "SQ_SRV_ID_VAL", schema="ANALITICA3", allocationSize = 1)
    @Column(name = "ID_VAL", nullable = false, unique = true, insertable = false, updatable = false)
    private Integer id;
//    @Id
//    @GeneratedValue
//    @Column(name = "ID_VAL", nullable = false, unique = true, insertable = false, updatable = false)
//    private Integer id;

    @Column(name = "REAL_VAL", nullable = false, updatable = false)
    private Double real;

    @Column(name = "PLAN_VAL", updatable = false)
    private Double plan;

    @Column(name = "ID_CLNDR", updatable = false)
    private String date;
    
    @Column(name = "D_IN", nullable = false, updatable = false)
    private Instant createdAt;

    @ManyToOne
    @JoinColumn(name = "ID_IND", nullable = false, updatable = false)
    private Indicator indicator;

    @ManyToOne
    @JoinColumn(name = "ID_AUDIT", nullable = false, updatable = false)
    private Patch patch;

    @ManyToOne
    @JoinColumn(name = "ID_PERIOD", nullable = false, updatable = false)
    private Period period;

    @ManyToOne
    @JoinColumn(name = "ID_TERR", nullable = false, updatable = false)
    private Territory territory;

    @JpaConstructor
    protected Point() {

    }

    @MapperConstructor
    public Point (final Double real, final Double plan, final String date, final Indicator indicator,
                  final Patch patch, final Period period, final Territory territory) {
        this.real = real;
        this.plan = plan;
        this.date = date;
        this.indicator = indicator;
        this.patch = patch;
        this.period = period;
        this.territory = territory;
    }

    public Integer getId () {
        return id;
    }

    public Double getReal () {
        return real;
    }

    public Double getPlan () {
        return plan;
    }

    public String getDate() {
        return date;
    }
    
    public Instant getCreatedAt () {
        return createdAt;
    }

    public Indicator getIndicator () {
        return indicator;
    }

    public Patch getPatch () {
        return patch;
    }

    public Period getPeriod () {
        return period;
    }

    public Territory getTerritory () {
        return territory;
    }
    
    @PrePersist
    private void onPersist() {
        createdAt = Instant.now();
    }
}
