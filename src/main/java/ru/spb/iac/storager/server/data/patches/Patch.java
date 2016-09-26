package ru.spb.iac.storager.server.data.patches;

import ru.spb.iac.storager.server.data.points.Point;
import ru.spb.iac.storager.server.data.providers.Provider;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.Instant;
import java.util.List;

@Entity
@Table(name = "patches")
public class Patch {

    private Integer id;

    private Provider provider;

    private String comment;

    private Instant createdAt;

    private String status;

    private List<Point> points;
}
