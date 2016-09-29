package ru.spb.iac.storager.server.domain.points;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import ru.spb.iac.storager.server.domain.indicators.Indicator;
import ru.spb.iac.storager.server.domain.patches.Patch;
import ru.spb.iac.storager.server.domain.periods.Period;
import ru.spb.iac.storager.server.domain.shared.JpaConstructor;
import ru.spb.iac.storager.server.domain.territories.Territory;

@Entity
@Table(name = "points")
public class Point {

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false, unique = true, insertable = false, updatable = false)
    private Integer id;

    @Column(name = "real", nullable = false, updatable = false)
    private Double real;

    @Column(name = "plan", updatable = false)
    private Double plan;

    @Column(name = "time", updatable = false)
    private String time;

    @ManyToOne
    @JoinColumn(name = "indicator_id", nullable = false, updatable = false)
    private Indicator indicator;

    @ManyToOne
    @JoinColumn(name = "patch_id", nullable = false, updatable = false)
    private Patch patch;

    @ManyToOne
    @JoinColumn(name = "period_id", nullable = false, updatable = false)
    private Period period;

    @ManyToOne
    @JoinColumn(name = "territory_id", nullable = false, updatable = false)
    private Territory territory;

    @JpaConstructor
    protected Point() {

    }

    public Point (final Double real, final Double plan, final String time, final Indicator indicator,
                  final Patch patch, final Period period, final Territory territory) {
        this.real = real;
        this.plan = plan;
        this.time = time;
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

    public String getTime() {
        return time;
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
}
