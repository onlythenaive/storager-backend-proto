package ru.spb.iac.storager.server.data.territories;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "territories")
public class Territory {

    public static Territory of(String code, String title, Territory ascendant) {
        Territory territory = new Territory();
        territory.code = code;
        territory.title = title;
        territory.ascendant = ascendant;
        territory.descendants = new ArrayList<>();
        return territory;
    }

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false, unique = true, insertable = false, updatable = false)
    private Integer id;

    @Column(name = "code", nullable = false, unique = true)
    private String code;

    @Column(name = "title", nullable = false, unique = true)
    private String title;

    @ManyToOne
    @JoinColumn(name = "ascendant_id")
    private Territory ascendant;

    @OneToMany(mappedBy = "ascendant")
    private List<Territory> descendants;

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

    public Territory getAscendant() {
        return ascendant;
    }

    public void setAscendant(Territory ascendant) {
        this.ascendant = ascendant;
    }

    public List<Territory> getDescendants() {
        return descendants;
    }
}
