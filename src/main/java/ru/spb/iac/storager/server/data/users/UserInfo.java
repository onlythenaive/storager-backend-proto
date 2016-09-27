package ru.spb.iac.storager.server.data.users;

import java.io.Serializable;
import java.util.Set;

public class UserInfo implements Serializable {

    public static UserInfo fromUser(User user) {
        UserInfo result = new UserInfo();
        result.setLogin(user.getLogin());
        result.setEmail(user.getEmail());
        result.setFullname(user.getFullname());
        result.setRegisteredAt(user.getRegisteredAt().toString());
        result.setEnabled(user.getEnabled());
        result.setRoot(user.getRoot());
        result.setRoles(User.parseRoles(user.getRoles()));
        return result;
    }

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

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getRegisteredAt() {
        return registeredAt;
    }

    public void setRegisteredAt(String registeredAt) {
        this.registeredAt = registeredAt;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Boolean getRoot() {
        return root;
    }

    public void setRoot(Boolean root) {
        this.root = root;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }
}
