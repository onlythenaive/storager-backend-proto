package ru.spb.iac.storager.server.data.users;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;

    @Column(name = "login")
    private String login;

    @Column(name = "email")
    private String email;

    @Column(name = "fullname")
    private String fullname;

    @Column(name = "registered_at")
    private Instant registeredAt;

    @Column(name = "enabled")
    private Boolean enabled;

    @Column(name = "root")
    private Boolean root;

    @Column(name = "roles")
    private String roles;

    public User () {

    }

    public User (String login, String email, String fullname, boolean enabled, boolean root, String roles) {
        this.login = login;
        this.email = email;
        this.fullname = fullname;
        this.registeredAt = Instant.now();
        this.enabled = enabled;
        this.root = root;
        this.roles = roles;
    }

    public Integer getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getEmail() {
        return email;
    }

    public String getFullname() {
        return fullname;
    }

    public Instant getRegisteredAt() {
        return registeredAt;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public Boolean getRoot() {
        return root;
    }

    public String getRoles() {
        return roles;
    }
}
