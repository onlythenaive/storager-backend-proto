package ru.spb.iac.storager.server.domain.users;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserMapper {

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
