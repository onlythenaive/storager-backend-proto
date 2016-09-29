package ru.spb.iac.storager.server.security;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import ru.spb.iac.storager.server.errors.NotAuthorizedException;

@Component
@Scope(scopeName = "request", proxyMode = ScopedProxyMode.INTERFACES)
public class SecurityContextDefault implements SecurityContextConfigurer {

    private AuthorizedUser authorizedUser;

    @Override
    public AuthorizedUser getAuthorizedUser() {
        return authorizedUser;
    }

    @Override
    public AuthorizedUser userAuthorizedWith(String... roles) {
        if (authorizedUser == null) {
            throw NotAuthorizedException.noAuthorizedUser();
        }
        if (authorizedUser.isRoot()) {
            return authorizedUser;
        }
        if (roles == null || roles.length == 0) {
            return authorizedUser;
        }
        for (String role : roles) {
            if (authorizedUser.getRoles().contains(role)) {
                return authorizedUser;
            }
        }
        throw NotAuthorizedException.insufficientPermissions(authorizedUser.getLogin(), roles);
    }

    @Override
    public void setAuthorizedUser(AuthorizedUser authorizedUser) {
        this.authorizedUser = authorizedUser;
    }
}
