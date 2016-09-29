package ru.spb.iac.storager.server.security;

public interface SecurityContextConfigurer extends SecurityContext {

    void setUserAuthentication(UserAuthentication userAuthentication);
}
