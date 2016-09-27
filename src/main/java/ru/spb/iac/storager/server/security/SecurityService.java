package ru.spb.iac.storager.server.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ru.spb.iac.storager.server.data.users.User;
import ru.spb.iac.storager.server.data.users.UserRepository;

@Service
public class SecurityService {

    @Autowired
    private UserTokenService userTokenService;

    @Autowired
    private UserRepository userRepository;

    public UserToken logon(UserCredentials credentials) {
        User user = userRepository.findByLogin(credentials.getLogin());
        if (user == null || !user.getEnabled() || !user.getSecret().equals(credentials.getSecret())) {
            throw new RuntimeException("not authorized");
        }
        return userTokenService.create(user.getLogin());
    }

    public void logout(String tokenId) {
        userTokenService.remove(tokenId);
    }
}
