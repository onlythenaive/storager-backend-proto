package ru.spb.iac.storager.server.data.users;

import java.time.Instant;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User {

    public static User of(String login, String secret, String email, String fullname, boolean enabled, boolean root, String roles) {
        User user = new User();
        user.login = login;
        user.secret = secret;
        user.email = email;
        user.fullname = fullname;
        user.registeredAt = Instant.now();
        user.enabled = enabled;
        user.root = root;
        user.roles = roles;
        return user;
    }

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
}
