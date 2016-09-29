package ru.spb.iac.storager.server.domain.periods;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import ru.spb.iac.storager.server.domain.shared.JpaConstructor;

@Entity
@Table(name = "periods")
public class Period {

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false, unique = true, insertable = false, updatable = false)
    private Integer id;

    @Column(name = "code", nullable = false, unique = true)
    private String code;

    @Column(name = "title", nullable = false)
    private String title;

    @JpaConstructor
    protected Period() {

    }

    protected Period(final String code, final String title) {
        this.code = code;
        this.title = title;
    }

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
