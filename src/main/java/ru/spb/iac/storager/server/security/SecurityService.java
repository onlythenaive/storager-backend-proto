package ru.spb.iac.storager.server.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ru.spb.iac.storager.server.domain.users.User;
import ru.spb.iac.storager.server.domain.users.UserRepository;
import ru.spb.iac.storager.server.errors.NotAuthorizedException;

@Service
public class SecurityService {

    @Autowired
    private UserTokenService userTokenService;

    @Autowired
    private UserRepository userRepository;

    public UserToken logon(UserCredentials credentials) {
        User user = userRepository.findByLogin(credentials.getLogin());
        if (user == null || user.getLogin().equals(User.GUEST_LOGIN) || !user.getEnabled() || !user.getSecret().equals(credentials.getSecret())) {
            throw NotAuthorizedException.invalidCredentials(credentials.getLogin());
        }
        return userTokenService.create(user.getLogin());
    }

    public void logout(String tokenId) {
        userTokenService.remove(tokenId);
    }
}
