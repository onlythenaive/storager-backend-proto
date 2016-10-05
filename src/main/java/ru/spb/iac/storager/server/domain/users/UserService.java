package ru.spb.iac.storager.server.domain.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.spb.iac.storager.server.errors.domain.InvalidPropertyException;

@Service
@Transactional(readOnly = true)
public class UserService {

    @Autowired
    private UserMapper mapper;

    @Autowired
    private UserRepository repository;

    UserInfo getByLogin(final String login) {
        if (login == null) {
            InvalidPropertyException.missingProperty("login");
        }
        final User user = repository.findByLogin(login);
        if (user == null) {
            // TODO: throw reasonable exception for missing user
            throw new RuntimeException();
        }
        return mapper.intoInfo(user);
    }
}
