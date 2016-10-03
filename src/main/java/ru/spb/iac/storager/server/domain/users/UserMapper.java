package ru.spb.iac.storager.server.domain.users;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserMapper {

    public UserData intoData(final User entity) {
        final UserData data = new UserData();
        data.setLogin(entity.getLogin());
        data.setEmail(entity.getEmail());
        data.setFullname(entity.getFullname());
        data.setRegisteredAt(entity.getRegisteredAt().toString());
        data.setEnabled(entity.getEnabled());
        data.setRoot(entity.getRoot());
        data.setRoles(entity.getRolesParsed());
        return data;
    }
}
