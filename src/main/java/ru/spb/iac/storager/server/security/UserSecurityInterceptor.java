package ru.spb.iac.storager.server.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Component
public class UserSecurityInterceptor extends HandlerInterceptorAdapter {

    private final String authTokenHeader = "X-Auth-Token";

    @Autowired
    private SecurityContextConfigurer securityContext;

    @Autowired
    private SecurityService securityService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        final String tokenId = request.getHeader(authTokenHeader);
        securityContext.setUserAuthentication(authenticate(tokenId));
        return true;
    }

    private UserAuthentication authenticate(final String tokenId) {
        return tokenId != null ? securityService.authenticateByToken(tokenId) : securityService.authenticateAsGuest();
    }
}
