package ru.spb.iac.storager.server.data.security.users;

import com.google.common.collect.ImmutableList;

import java.io.Serializable;
import java.util.List;

public class UserInfo implements Serializable {

    private String login;
    private String email;
    private String fullname;
    private String registeredAt;
    private Boolean enabled;
    private Boolean root;
    private List<String> roles;

    public static UserInfo fromUser(User user) {
        UserInfo result = new UserInfo();
        result.setLogin(user.getLogin());
        result.setEmail(user.getEmail());
        result.setFullname(user.getFullname());
        result.setRegisteredAt(user.getRegisteredAt().toString());
        result.setEnabled(user.getEnabled());
        result.setRoot(user.getRoot());
        result.setRoles(ImmutableList.copyOf(user.getRoles().split(" ")));
        return result;
    }

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

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}
