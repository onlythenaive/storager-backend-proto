package ru.spb.iac.storager.server.security;

public interface SecurityContext {

    ProviderAuthentication getProviderAuthentication();

    UserAuthentication getUserAuthentication();

    ProviderAuthentication providerAuthenticated();

    UserAuthentication userAuthenticated();

    UserAuthentication userAuthorizedWithAny(String... roles);
}
