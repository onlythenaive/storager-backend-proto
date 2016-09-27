package ru.spb.iac.storager.server.security;

public interface SecurityContextConfigurer extends SecurityContext {

    void setAuthorizedUser(AuthorizedUser authorizedUser);
}
