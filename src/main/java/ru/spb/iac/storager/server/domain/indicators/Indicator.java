package ru.spb.iac.storager.server.domain.indicators;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import ru.spb.iac.storager.server.domain.shared.HierarchicLoopException;
import ru.spb.iac.storager.server.domain.shared.JpaConstructor;

@Entity
@Table(name = "indicators")
public class Indicator {

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false, unique = true, insertable = false, updatable = false)
    private Integer id;

    @Column(name = "code", nullable = false, unique = true)
    private String code;

    @Column(name = "title", nullable = false)
    private String title;

    @ManyToOne
    @JoinColumn(name = "ascendant_id")
    private Indicator ascendant;

    @OneToMany(mappedBy = "ascendant")
    private List<Indicator> descendants;

    @JpaConstructor
    protected Indicator() {

    }

    public Indicator (final String code, final String title, final Indicator ascendant) {
        this.code = code;
        this.title = title;
        this.ascendant = ascendant;
        this.descendants = new ArrayList<>();
    }

    public Integer getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(final String code) {
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public Indicator getAscendant() {
        return ascendant;
    }

    public void setAscendant(final Indicator ascendant) {
        this.ascendant = ascendant;
    }

    public List<Indicator> getDescendants() {
        return descendants;
    }

    @PrePersist
    @PreUpdate
    private void onPersistAndUpdate() {
        if (ascendant != null && ascendant.getCode().equals(code)) {
            throw new HierarchicLoopException(code);
        }
    }
}
