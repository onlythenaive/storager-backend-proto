package ru.spb.iac.storager.server.data.patches;

import java.time.Instant;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import ru.spb.iac.storager.server.data.points.Point;
import ru.spb.iac.storager.server.data.providers.Provider;

@Entity
@Table(name = "patches")
public class Patch {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;

    @Column(name = "comment")
    private String comment;

    @Column(name = "created_at")
    private Instant createdAt;

    @Column(name = "status")
    private String status;

    @OneToMany(mappedBy = "patch")
    private List<Point> points;

    @ManyToOne
    @JoinColumn(name = "provider_id")
    private Provider provider;

    public Integer getId () {
        return id;
    }

    public String getComment () {
        return comment;
    }

    public void setComment (final String comment) {
        this.comment = comment;
    }

    public Instant getCreatedAt () {
        return createdAt;
    }

    public String getStatus () {
        return status;
    }

    public List<Point> getPoints () {
        return points;
    }

    public Provider getProvider () {
        return provider;
    }

    public void setProvider (final Provider provider) {
        this.provider = provider;
    }
}
