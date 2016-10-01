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

    UserData getByLogin(final String login) {
        return userMapper.intoData(userRepository.findByLogin(login));
    }
}
