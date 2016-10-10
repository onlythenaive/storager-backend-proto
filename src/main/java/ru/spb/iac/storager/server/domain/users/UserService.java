package ru.spb.iac.storager.server.domain.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.spb.iac.storager.server.errors.domain.ItemValidationHelper;
import ru.spb.iac.storager.server.security.SecurityContext;

@Service
public class UserService {

    @Autowired
    private SecurityContext securityContext;

    @Autowired
    private ItemValidationHelper itemValidation;

    @Autowired
    private UserMapper mapper;

    @Autowired
    private UserRepository repository;

    @Transactional
    UserInfo bootstrap(final UserProperties properties) {
        return mapper.intoInfo(repository.save(mapper.intoEntity(properties)));
    }

    @Transactional(readOnly = true)
    UserInfo getAuthenticated() {
        final String login = securityContext.userAuthenticated().getLogin();
        final User user = itemValidation.required(repository.findByLogin(login), "login", login);
        return mapper.intoInfo(user);
    }
}
