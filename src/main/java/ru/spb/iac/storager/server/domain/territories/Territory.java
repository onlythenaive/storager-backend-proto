package ru.spb.iac.storager.server.domain.territories;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import ru.spb.iac.storager.server.domain.shared.JpaConstructor;
import ru.spb.iac.storager.server.domain.shared.MapperConstructor;
import ru.spb.iac.storager.server.domain.shared.hierarchic.HierarchicItem;

@Entity
@Table(name = "SRV_TERR", schema="ANALITICA3")
public class Territory implements HierarchicItem<Territory> {

    //TODO add sequence
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_territory")
    @SequenceGenerator(name = "seq_territory", sequenceName = "SQ_SRV_ID_TERR", schema="ANALITICA3", allocationSize = 1)
    @Column(name = "ID_TERR", nullable = false, unique = true, insertable = false, updatable = false)
    private Integer id;
//    @Id
//    @GeneratedValue
//    @Column(name = "ID_TERR", nullable = false, unique = true, insertable = false, updatable = false)
//    private Integer id;

    @NotNull
    @Column(name = "TERR_CODE", nullable = false, unique = true)
    private String code;

    @NotNull
    @Column(name = "NAME_FULL", nullable = false)
    private String title;

    @Column(name = "NAME_SHORT")
    private String description;
    
    @Column(name = "D_IN", nullable = false, updatable = false)
    private Instant createdAt;

    @ManyToOne
    @JoinColumn(name = "ID_TERR_PARENT")
    private Territory ascendant;

    @OneToMany(mappedBy = "ascendant")
    private List<Territory> descendants;

    @JpaConstructor
    @MapperConstructor
    protected Territory() {
        this.descendants = new ArrayList<>();
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public void setCode(final String code) {
        this.code = code;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public void setTitle(final String title) {
        this.title = title;
    }

    @Override
    public String getDescription() {
        return description;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }   

    @Override
    public void setDescription(final String description) {
        this.description = description;
    }

    @Override
    public Territory getAscendant() {
        return ascendant;
    }

    @Override
    public void setAscendant(final Territory ascendant) {
        this.ascendant = ascendant;
    }

    @Override
    public List<Territory> getDescendants() {
        return descendants;
    }    
    
    @PrePersist
    public void onPersist() {
        createdAt = Instant.now();
    }
}
