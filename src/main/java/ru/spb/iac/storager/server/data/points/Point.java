package ru.spb.iac.storager.server.data.points;

import ru.spb.iac.storager.server.data.indicators.Indicator;
import ru.spb.iac.storager.server.data.patches.Patch;
import ru.spb.iac.storager.server.data.periods.Period;
import ru.spb.iac.storager.server.data.territories.Territory;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "points")
public class Point {

    private Integer id;

    private Double real;

    private Double plan;

    private Indicator indicator;

    private Patch patch;

    private Period period;

    private Territory territory;
}
