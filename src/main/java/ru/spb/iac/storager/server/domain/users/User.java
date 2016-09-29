package ru.spb.iac.storager.server.domain.users;

import java.time.Instant;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.PostLoad;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Transient;

import ru.spb.iac.storager.server.domain.shared.JpaConstructor;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false, unique = true, insertable = false, updatable = false)
    private Integer id;

    @Column(name = "login", nullable = false, unique = true, updatable = false)
    private String login;

    @Column(name = "secret", nullable = false, updatable = false)
    private String secret;

    @Column(name = "email", nullable = false, unique = true, updatable = false)
    private String email;

    @Column(name = "fullname", nullable = false, updatable = false)
    private String fullname;

    @Column(name = "registered_at", nullable = false, updatable = false)
    private Instant registeredAt;

    @Column(name = "enabled", nullable = false, updatable = false)
    private Boolean enabled;

    @Column(name = "root", nullable = false, updatable = false)
    private Boolean root;

    @Column(name = "roles", nullable = false, updatable = false)
    private String roles;

    @Transient
    private Set<String> rolesParsed;

    @JpaConstructor
    protected User() {

    }

    protected User(final String login, final String secret, final String email, final String fullname,
                   final boolean enabled, final boolean root, final String roles) {
        this.login = login;
        this.secret = secret;
        this.email = email;
        this.fullname = fullname;
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

    public String getSecret() {
        return secret;
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

    public Set<String> getRolesParsed() {
        return new HashSet<>(rolesParsed);
    }

    @PostLoad
    private void onLoad() {
        rolesParsed = new HashSet<>(Arrays.asList(roles.split(" ")));
    }

    @PrePersist
    private void onPersist() {
        registeredAt = Instant.now();
    }
}
