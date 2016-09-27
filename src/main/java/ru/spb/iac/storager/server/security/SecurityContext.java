package ru.spb.iac.storager.server.security;

public interface SecurityContext {

    AuthorizedUser getAuthorizedUser();

    AuthorizedUser userAuthorizedWith(String... roles);

    default AuthorizedUser userAuthorizedWithAnyRole() {
        return userAuthorizedWith();
    }
}
