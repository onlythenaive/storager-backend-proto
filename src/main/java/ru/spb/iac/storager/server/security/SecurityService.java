package ru.spb.iac.storager.server.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ru.spb.iac.storager.server.domain.users.User;
import ru.spb.iac.storager.server.domain.users.UserRepository;
import ru.spb.iac.storager.server.errors.security.GuestUnavailableException;
import ru.spb.iac.storager.server.errors.security.InvalidCredentialsException;
import ru.spb.iac.storager.server.errors.security.InvalidTokenException;
import ru.spb.iac.storager.server.errors.security.MissingLoginException;
import ru.spb.iac.storager.server.errors.security.MissingTokenException;

@Service
public class SecurityService {

    private final String guestLogin = "guest";

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserTokenService userTokenService;

    public UserAuthentication authenticateAsGuest() {
        final User guest = userRepository.findByLogin(guestLogin);
        if (notEnabled(guest)) {
            throw new GuestUnavailableException();
        }
        return new UserAuthentication(guest);
    }

    public UserAuthentication authenticateByCredentials(final UserCredentialData credentials) {
        if (credentials == null || credentials.getLogin() == null) {
            throw new MissingLoginException();
        }
        final String login = credentials.getLogin();
        final User user = userRepository.findByLogin(login);
        if (notEnabled(user) || notLegal(user, credentials.getSecret())) {
            throw new InvalidCredentialsException(login);
        }
        final UserToken token = userTokenService.generate(login);
        return new UserAuthentication(user, token);
    }

    public UserAuthentication authenticateByToken(final String tokenId) {
        if (tokenId == null) {
            throw new MissingTokenException();
        }
        final UserToken token = userTokenService.get(tokenId);
        if (token == null) {
            throw new InvalidTokenException(tokenId);
        }
        final User user = userRepository.findByLogin(token.getLogin());
        if (user == null || !user.getEnabled()) {
            throw new InvalidTokenException(tokenId);
        }
        return new UserAuthentication(user, token);
    }

    public void deauthenticateByLogin(final String login) {
        userTokenService.removeByLogin(login);
    }

    public void deauthenticateByToken(final String tokenId) {
        userTokenService.remove(tokenId);
    }

    private boolean notEnabled(final User user) {
        return user == null || !user.getEnabled();
    }

    private boolean notLegal(final User user, final String secret) {
        return user == null || user.getLogin().equals(guestLogin) || !user.getSecret().equals(secret);
    }
}
