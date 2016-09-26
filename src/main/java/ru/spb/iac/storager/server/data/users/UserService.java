package ru.spb.iac.storager.server.data.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepository userRepository;

    List<UserInfo> getAll() {
        return userRepository
                .findAll()
                .stream()
                .map(UserInfo::fromUser)
                .collect(Collectors.toList());
    }

    UserInfo getByLogin(String login) {
        return UserInfo.fromUser(userRepository.findByLogin(login));
    }
}
