package ru.spb.iac.storager.server.domain.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserRepository userRepository;

    UserInfo getByLogin(final String login) {
        if (login == null) {
            // TODO: throw reasonable exception for missing login
            throw new RuntimeException();
        }
        final User user = userRepository.findByLogin(login);
        if (user == null) {
            // TODO: throw reasonable exception for missing user
            throw new RuntimeException();
        }
        return userMapper.intoInfo(user);
    }
}
