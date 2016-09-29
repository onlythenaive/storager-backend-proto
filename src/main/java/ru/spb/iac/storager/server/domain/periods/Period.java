package ru.spb.iac.storager.server.domain.periods;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

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

    @Column(name = "title", nullable = false)
    private String title;

    public Integer getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public String getTitle() {
        return title;
    }
}
