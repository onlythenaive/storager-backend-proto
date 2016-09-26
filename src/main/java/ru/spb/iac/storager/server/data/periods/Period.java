package ru.spb.iac.storager.server.data.periods;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import ru.spb.iac.storager.server.data.points.Point;

@Entity
@Table(name = "periods")
public class Period {

    public static Period of(String code, String title) {
        Period period = new Period();
        period.code = code;
        period.title = title;
        return period;
    }

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false, unique = true, insertable = false, updatable = false)
    private Integer id;

    @Column(name = "code", nullable = false, unique = true)
    private String code;

    @Column(name = "title", nullable = false, unique = true)
    private String title;

    @OneToMany(mappedBy = "period")
    private List<Point> points;

    public Integer getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public String getTitle() {
        return title;
    }

    public List<Point> getPoints() {
        return points;
    }
}
