package ru.spb.iac.storager.server.domain.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepository userRepository;

    UserInfo getByLogin(String login) {
        return UserInfo.fromUser(userRepository.findByLogin(login));
    }
}
