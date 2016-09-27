package ru.spb.iac.storager.server;

public interface SecurityContext {

    Object userAuthorizedWith(String... roles);
}
