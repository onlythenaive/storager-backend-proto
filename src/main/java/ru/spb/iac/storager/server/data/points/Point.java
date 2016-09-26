package ru.spb.iac.storager.server.data.points;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import ru.spb.iac.storager.server.data.indicators.Indicator;
import ru.spb.iac.storager.server.data.patches.Patch;
import ru.spb.iac.storager.server.data.periods.Period;
import ru.spb.iac.storager.server.data.territories.Territory;

@Entity
@Table(name = "points")
public class Point {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;

    @Column(name = "real")
    private Double real;

    @Column(name = "plan")
    private Double plan;

    @ManyToOne
    @JoinColumn(name = "indicator_id")
    private Indicator indicator;

    @ManyToOne
    @JoinColumn(name = "patch_id")
    private Patch patch;

    @ManyToOne
    @JoinColumn(name = "period_id")
    private Period period;

    @ManyToOne
    @JoinColumn(name = "territory_id")
    private Territory territory;

    public Integer getId () {
        return id;
    }

    public Double getReal () {
        return real;
    }

    public void setReal (final Double real) {
        this.real = real;
    }

    public Double getPlan () {
        return plan;
    }

    public void setPlan (final Double plan) {
        this.plan = plan;
    }

    public Indicator getIndicator () {
        return indicator;
    }

    public void setIndicator (final Indicator indicator) {
        this.indicator = indicator;
    }

    public Patch getPatch () {
        return patch;
    }

    public void setPatch (final Patch patch) {
        this.patch = patch;
    }

    public Period getPeriod () {
        return period;
    }

    public void setPeriod (final Period period) {
        this.period = period;
    }

    public Territory getTerritory () {
        return territory;
    }

    public void setTerritory (final Territory territory) {
        this.territory = territory;
    }
}
