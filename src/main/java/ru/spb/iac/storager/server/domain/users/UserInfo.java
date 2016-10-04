package ru.spb.iac.storager.server.domain.users;

import java.util.HashSet;
import java.util.Set;

import ru.spb.iac.storager.server.domain.shared.MapperConstructor;

public final class UserInfo {

    private final String login;
    private final String email;
    private final String fullname;
    private final String registeredAt;
    private final Boolean enabled;
    private final Boolean root;
    private final Set<String> roles;

    @MapperConstructor
    protected UserInfo(final String login,
                       final String email,
                       final String fullname,
                       final String registeredAt,
                       final Boolean enabled,
                       final Boolean root,
                       final Set<String> roles) {
        this.login = login;
        this.email = email;
        this.fullname = fullname;
        this.registeredAt = registeredAt;
        this.enabled = enabled;
        this.root = root;
        this.roles = new HashSet<>(roles);
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

    public String getRegisteredAt() {
        return registeredAt;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public Boolean getRoot() {
        return root;
    }

    public Set<String> getRoles() {
        return new HashSet<>(roles);
    }
}
