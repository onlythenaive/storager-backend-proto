package ru.spb.iac.storager.server;

public interface SecurityContext {

    Object userAuthorizedWith(String... roles);

    default Object userAuthorizedWithAnyRole() {
        return userAuthorizedWith();
    }
}
