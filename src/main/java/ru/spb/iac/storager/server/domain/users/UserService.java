package ru.spb.iac.storager.server.domain.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.spb.iac.storager.server.errors.domain.InputValidationHelper;
import ru.spb.iac.storager.server.errors.domain.ItemValidationHelper;

@Service
@Transactional(readOnly = true)
public class UserService {

    @Autowired
    private InputValidationHelper inputValidation;

    @Autowired
    private ItemValidationHelper itemValidation;

    @Autowired
    private UserMapper mapper;

    @Autowired
    private UserRepository repository;

    UserInfo getByLogin(final String login) {
        inputValidation.required(login, "login");
        final User user = itemValidation.required(repository.findByLogin(login), "login", login);
        return mapper.intoInfo(user);
    }
}
