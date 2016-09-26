package ru.spb.iac.storager.server.data.indicators;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "indicators")
public class Indicator {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;

    @Column(name = "code")
    private String code;

    @Column(name = "title")
    private String title;

    @ManyToOne
    @JoinColumn(name = "ascendant_id")
    private Indicator ascendant;

    @OneToMany(mappedBy = "ascendant")
    private List<Indicator> descendants;

    public Integer getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Indicator getAscendant() {
        return ascendant;
    }

    public void setAscendant(Indicator ascendant) {
        this.ascendant = ascendant;
    }

    public List<Indicator> getDescendants() {
        return descendants;
    }

    public void setDescendants(List<Indicator> descendants) {
        this.descendants = descendants;
    }
}
