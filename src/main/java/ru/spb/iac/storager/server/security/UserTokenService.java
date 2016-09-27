package ru.spb.iac.storager.server.security;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

@Service
public class UserTokenService {

    private Map<String, UserToken> tokens = new ConcurrentHashMap<>();

    public UserToken create(String login) {
        UserToken token = UserToken.generate(login);
        tokens.put(token.getId(), token);
        return token;
    }

    public UserToken get(String id) {
        return tokens.get(id);
    }

    public void remove(String id) {
        tokens.remove(id);
    }

    public void removeByLogin(String login) {
        tokens.forEach((id, token) -> {
            if (token.getLogin().equals(login)) {
                tokens.remove(id);
            }
        });
    }
}
