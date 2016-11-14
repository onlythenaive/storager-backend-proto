package ru.spb.iac.storager.server.domain.users;

import java.time.Instant;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import ru.spb.iac.storager.server.domain.shared.JpaConstructor;
import ru.spb.iac.storager.server.domain.shared.MapperConstructor;

@Entity
@Table(name = "SRV_USER_INFO", schema="ANALITICA3")
public class User {

    @Id
    @GeneratedValue
    @Column(name = "ID_USI", nullable = false, unique = true, insertable = false, updatable = false)
    private Integer id;

    @Column(name = "USER_LOGIN", nullable = false, unique = true, updatable = false)
    private String login;

    @Column(name = "USER_PASSWORD", nullable = false, updatable = false)
    private String secret;

    @Column(name = "EMAIL", nullable = false, unique = true, updatable = false)
    private String email;

    @Column(name = "FULL_NAME", nullable = false, updatable = false)
    private String fullname;

    @Column(name = "D_IN", nullable = false, updatable = false)
    private Instant registeredAt;

    @Column(name = "IS_ENABLED", nullable = false, updatable = false)
    private Integer enabled;

    @Column(name = "IS_ROOT", nullable = false, updatable = false)
    private Integer root;

    @Column(name = "USER_ROLE", updatable = false)
    private String roles;

    @JpaConstructor
    protected User() {

    }

    @MapperConstructor
    protected User(final String login,
                   final String secret,
                   final String email,
                   final String fullname,
                   final boolean enabled,
                   final boolean root,
                   final String roles) {
        this.login = login;
        this.secret = secret;
        this.email = email;
        this.fullname = fullname;
        this.enabled = enabled?1:0;
        this.root = root?1:0;
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
        return enabled==1?true:false;
    }

    public Boolean getRoot() {
        return root==1?true:false;
    }

    public String getRoles() {
        return roles;
    }

    public Set<String> getRolesParsed() {
        return roles != null ? new HashSet<>(Arrays.asList(roles.split(" "))) : new HashSet<>();
    }

    @PrePersist
    private void onPersist() {
        registeredAt = Instant.now();
    }
}
