package ru.spb.iac.storager.server.security;

public interface SecurityContext {

    Object userAuthorizedWith(String... roles);

    default Object userAuthorizedWithAnyRole() {
        return userAuthorizedWith();
    }
}
