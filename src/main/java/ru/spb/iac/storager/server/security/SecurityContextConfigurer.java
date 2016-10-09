package ru.spb.iac.storager.server.security;

public interface SecurityContextConfigurer extends SecurityContext {

    void setProviderAuthentication(ProviderAuthentication providerAuthentication);

    void setUserAuthentication(UserAuthentication userAuthentication);
}
