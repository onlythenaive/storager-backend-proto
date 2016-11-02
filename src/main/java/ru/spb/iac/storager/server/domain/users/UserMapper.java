package ru.spb.iac.storager.server.domain.users;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserMapper {

    @Transactional(readOnly = true)
    public User intoEntity(final UserProperties properties) {
        final StringBuilder roles = new StringBuilder();
        if (properties.getRoles() != null) {
            properties.getRoles().forEach(role -> roles.append(role).append(" "));
        }
        return new User(
                properties.getLogin(),
                properties.getSecret(),
                properties.getEmail(),
                properties.getFullname(),
                properties.getEnabled() != null ? properties.getEnabled() : true,
                properties.getRoot() != null ? properties.getRoot() : false,
                roles.toString()
        );
    }

    @Transactional(readOnly = true)
    public UserInfo intoInfo(final User entity) {
        return new UserInfo(
                entity.getLogin(),
                entity.getEmail(),
                entity.getFullname(),
                entity.getRegisteredAt().toString(),
                entity.getEnabled(),
                entity.getRoot(),
                entity.getRolesParsed()
        );
    }
}
