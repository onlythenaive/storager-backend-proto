package ru.spb.iac.storager.server.security;

public interface SecurityContext {

    UserAuthentication getUserAuthentication();

    UserAuthentication userAuthorizedWith(String... roles);

    default UserAuthentication userAuthorizedWithAnyRole() {
        return userAuthorizedWith();
    }
}
