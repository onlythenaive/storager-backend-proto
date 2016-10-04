package ru.spb.iac.storager.server.security;

public interface SecurityContext {

    UserAuthentication getUserAuthentication();

    UserAuthentication userAuthenticated();

    UserAuthentication userAuthorizedWithAny(String... roles);
}
