package ru.spb.iac.storager.server.security;

import java.time.Duration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

@Service
public class UserTokenService {

    private final Duration deactivation = Duration.ofMinutes(30);
    private final Duration expiration = Duration.ofDays(1);
    private final Map<String, UserToken> tokens = new ConcurrentHashMap<>();

    public UserToken generate(final String login) {
        final UserToken token = new UserToken(login, deactivation, expiration);
        tokens.put(token.getId(), token);
        return token;
    }

    public UserToken get(final String id) {
        final UserToken token = tokens.get(id);
        if (token == null || token.isDeactivated() || token.isExpired()) {
            tokens.remove(id);
            return null;
        }
        token.update();
        return token;
    }

    public void remove(final String id) {
        tokens.remove(id);
    }

    public void removeByLogin(final String login) {
        tokens.forEach((id, token) -> {
            if (token.getLogin().equals(login)) {
                tokens.remove(id);
            }
        });
    }
}
