package ru.spb.iac.storager.server.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import ru.spb.iac.storager.server.domain.users.User;
import ru.spb.iac.storager.server.domain.users.UserRepository;

@Component
public class UserSecurityInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private SecurityContextConfigurer securityContext;

    @Autowired
    private UserTokenService userTokenService;

    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        String tokenId = request.getHeader("X-Auth-Token");
        if (tokenId == null) {
            authorizeAsGuest();
            return true;
        }
        UserToken token = userTokenService.get(tokenId);
        if (token == null) {
            authorizeAsGuest();
            return true;
        }
        User user = userRepository.findByLogin(token.getLogin());
        if (user == null || !user.getEnabled()) {
            authorizeAsGuest();
            return true;
        }
        authorize(user, token);
        return true;
    }

    private void authorize(User user, UserToken token) {
        securityContext.setAuthorizedUser(AuthorizedUser.fromUserAndToken(user, token));
    }

    private void authorizeAsGuest() {
        User guest = userRepository.findByLogin(User.GUEST_LOGIN);
        if (guest != null && guest.getEnabled()) {
            authorize(guest, UserToken.generate(guest.getLogin()));
        }
    }
}
