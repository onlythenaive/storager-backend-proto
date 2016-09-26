package ru.spb.iac.storager.server.data.territories;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "territories")
public class Territory {

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

    public void setDescendants(List<Territory> descendants) {
        this.descendants = descendants;
    }
}
