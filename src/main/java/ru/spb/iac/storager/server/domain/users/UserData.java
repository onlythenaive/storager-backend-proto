package ru.spb.iac.storager.server.domain.users;

import java.io.Serializable;
import java.util.Set;

public final class UserData implements Serializable {

    private String login;
    private String email;
    private String fullname;
    private String registeredAt;
    private Boolean enabled;
    private Boolean root;
    private Set<String> roles;

    public String getLogin() {
        return login;
    }

    public void setLogin(final String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(final String fullname) {
        this.fullname = fullname;
    }

    public String getRegisteredAt() {
        return registeredAt;
    }

    public void setRegisteredAt(final String registeredAt) {
        this.registeredAt = registeredAt;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(final Boolean enabled) {
        this.enabled = enabled;
    }

    public Boolean getRoot() {
        return root;
    }

    public void setRoot(final Boolean root) {
        this.root = root;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(final Set<String> roles) {
        this.roles = roles;
    }
}
